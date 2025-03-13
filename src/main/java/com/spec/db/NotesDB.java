package com.spec.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.spec.models.Note;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * The class acts as simple in memory keyvalue database.
 */
@Slf4j
public class NotesDB {

    private NotesDB() {
        // pass
    }

    private static ConcurrentHashMap<String, List<Note>> db = new ConcurrentHashMap<>();

    public static List<Note> getData(String key) {
        return db.get(key);
    }

    public static void putData(String key, Note note) {

        db.computeIfAbsent(key, k -> {
            return new ArrayList<>();
        });
        db.get(key).add(note);

    }

    public static String getDbAsString() {
        return db.toString();
    }

}
