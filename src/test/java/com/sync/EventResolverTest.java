package com.sync;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class EventResolverTest {
    private final EventResolver resolver = new EventResolver();

    @Test
    void testResolveEventsWithExampleData() {
        // Create local event
        Event local = new Event("Name 1", Arrays.asList(
            new Note(1, 3200, "A"),
            new Note(2, 5600, "C")
        ));

        // Create remote event
        Event remote = new Event("Name 2", Collections.singletonList(
            new Note(1, 2400, "B")
        ));

        // Resolve events
        Event resolved = resolver.resolveEvents(remote, local);

        // Verify results
        assertEquals("Name 1 / Name 2", resolved.getName());
        assertEquals(2, resolved.getNotes().size());
        
        // Verify first note (conflicting)
        Note firstNote = resolved.getNotes().get(0);
        assertEquals(1, firstNote.getId());
        assertEquals(2400, firstNote.getTimestamp()); // Remote timestamp
        assertEquals("A / B", firstNote.getText());
        
        // Verify second note (local only)
        Note secondNote = resolved.getNotes().get(1);
        assertEquals(2, secondNote.getId());
        assertEquals(5600, secondNote.getTimestamp());
        assertEquals("C", secondNote.getText());
    }

    @Test
    void testResolveEventsWithIdenticalEvents() {
        Event event = new Event("Name", Collections.singletonList(
            new Note(1, 1000, "Text")
        ));

        Event resolved = resolver.resolveEvents(event, event);

        assertEquals("Name", resolved.getName());
        assertEquals(1, resolved.getNotes().size());
        assertEquals("Text", resolved.getNotes().get(0).getText());
    }

    @Test
    void testResolveEventsWithNoConflicts() {
        Event local = new Event("Local", Collections.singletonList(
            new Note(1, 1000, "Local Text")
        ));

        Event remote = new Event("Remote", Collections.singletonList(
            new Note(2, 2000, "Remote Text")
        ));

        Event resolved = resolver.resolveEvents(remote, local);

        assertEquals("Local / Remote", resolved.getName());
        assertEquals(2, resolved.getNotes().size());
        assertTrue(resolved.getNotes().stream()
                .anyMatch(note -> note.getId() == 1 && note.getText().equals("Local Text")));
        assertTrue(resolved.getNotes().stream()
                .anyMatch(note -> note.getId() == 2 && note.getText().equals("Remote Text")));
    }

    @Test
    void testResolveEventsWithMultipleConflicts() {
        Event local = new Event("Local", Arrays.asList(
            new Note(1, 1000, "Local 1"),
            new Note(2, 2000, "Local 2")
        ));

        Event remote = new Event("Remote", Arrays.asList(
            new Note(1, 1500, "Remote 1"),
            new Note(2, 2500, "Remote 2")
        ));

        Event resolved = resolver.resolveEvents(remote, local);

        assertEquals("Local / Remote", resolved.getName());
        assertEquals(2, resolved.getNotes().size());
        
        // Verify notes are sorted by timestamp
        assertEquals(1500, resolved.getNotes().get(0).getTimestamp());
        assertEquals(2500, resolved.getNotes().get(1).getTimestamp());
        
        // Verify text combinations
        assertEquals("Local 1 / Remote 1", resolved.getNotes().get(0).getText());
        assertEquals("Local 2 / Remote 2", resolved.getNotes().get(1).getText());
    }

    @Test
    void testResolveEventsWithEmptyNotes() {
        Event local = new Event("Local", Collections.emptyList());
        Event remote = new Event("Remote", Collections.emptyList());

        Event resolved = resolver.resolveEvents(remote, local);

        assertEquals("Local / Remote", resolved.getName());
        assertTrue(resolved.getNotes().isEmpty());
    }
} 