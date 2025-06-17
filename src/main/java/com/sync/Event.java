package com.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Event {
    private final String name;
    private final List<Note> notes;

    public Event(String name, List<Note> notes) {
        this.name = name;
        this.notes = new ArrayList<>(notes);
    }

    public String getName() {
        return name;
    }

    public List<Note> getNotes() {
        return new ArrayList<>(notes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(name, event.name) &&
                Objects.equals(notes, event.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, notes);
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", notes=" + notes +
                '}';
    }
} 