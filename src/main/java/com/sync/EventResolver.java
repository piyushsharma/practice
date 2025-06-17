package com.sync;

import java.util.*;
import java.util.stream.Collectors;

public class EventResolver {
    private static final int SORT_THRESHOLD = 1000;
    
    public Event resolveEvents(Event remote, Event local) {
        int totalNotes = remote.getNotes().size() + local.getNotes().size();
        
        if (totalNotes > SORT_THRESHOLD) {
            return resolveWithSorting(remote, local);
        } else {
            return resolveWithHashMap(remote, local, totalNotes);
        }
    }
    
    private Event resolveWithHashMap(Event remote, Event local, int totalNotes) {
        // Resolve the name by combining with a slash
        String resolvedName = resolveName(remote.getName(), local.getName());
        
        // Create a map of notes by ID for quick lookup
        Map<Long, Note> remoteNotes = remote.getNotes().stream()
                .collect(Collectors.toMap(Note::getId, note -> note));
        
        // Process all notes
        List<Note> resolvedNotes = new ArrayList<>(totalNotes);
        
        // Process local notes
        for (Note localNote : local.getNotes()) {
            Note remoteNote = remoteNotes.get(localNote.getId());
            if (remoteNote != null) {
                // Note exists in both events - resolve conflict
                resolvedNotes.add(resolveNote(remoteNote, localNote));
                remoteNotes.remove(localNote.getId());
            } else {
                // Note only exists in local - keep as is
                resolvedNotes.add(localNote);
            }
        }
        
        // Add remaining remote notes
        resolvedNotes.addAll(remoteNotes.values());
        
        // Sort notes by timestamp
        resolvedNotes.sort(Comparator.comparingLong(Note::getTimestamp));
        
        return new Event(resolvedName, resolvedNotes);
    }
    
    private Event resolveWithSorting(Event remote, Event local) {
        // Resolve the name by combining with a slash
        String resolvedName = resolveName(remote.getName(), local.getName());
        
        // Create sorted lists by ID
        List<Note> sortedRemote = new ArrayList<>(remote.getNotes());
        List<Note> sortedLocal = new ArrayList<>(local.getNotes());
        
        sortedRemote.sort(Comparator.comparingLong(Note::getId));
        sortedLocal.sort(Comparator.comparingLong(Note::getId));
        
        // Merge sorted lists
        List<Note> resolvedNotes = new ArrayList<>(sortedRemote.size() + sortedLocal.size());
        int i = 0, j = 0;
        
        while (i < sortedRemote.size() && j < sortedLocal.size()) {
            long remoteId = sortedRemote.get(i).getId();
            long localId = sortedLocal.get(j).getId();
            
            if (remoteId < localId) {
                resolvedNotes.add(sortedRemote.get(i++));
            } else if (remoteId > localId) {
                resolvedNotes.add(sortedLocal.get(j++));
            } else {
                // Conflict resolution
                resolvedNotes.add(resolveNote(sortedRemote.get(i++), sortedLocal.get(j++)));
            }
        }
        
        // Add remaining notes
        while (i < sortedRemote.size()) {
            resolvedNotes.add(sortedRemote.get(i++));
        }
        while (j < sortedLocal.size()) {
            resolvedNotes.add(sortedLocal.get(j++));
        }
        
        // Final sort by timestamp
        resolvedNotes.sort(Comparator.comparingLong(Note::getTimestamp));
        
        return new Event(resolvedName, resolvedNotes);
    }
    
    private String resolveName(String remoteName, String localName) {
        if (remoteName.equals(localName)) {
            return remoteName;
        }
        return localName + " / " + remoteName;
    }
    
    private Note resolveNote(Note remote, Note local) {
        // Use remote timestamp as per requirements
        long resolvedTimestamp = remote.getTimestamp();
        
        // Combine text with a slash if different
        String resolvedText = resolveText(remote.getText(), local.getText());
        
        return new Note(remote.getId(), resolvedTimestamp, resolvedText);
    }
    
    private String resolveText(String remoteText, String localText) {
        if (remoteText.equals(localText)) {
            return remoteText;
        }
        return localText + " / " + remoteText;
    }
} 