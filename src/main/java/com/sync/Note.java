package com.sync;

import java.util.Objects;

public class Note {
    private final long id;
    private final long timestamp;
    private final String text;

    public Note(long id, long timestamp, String text) {
        this.id = id;
        this.timestamp = timestamp;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", text='" + text + '\'' +
                '}';
    }
} 