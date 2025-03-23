package com.spec.db;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import com.spec.models.Note;

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
        List<Note> resp = db.get(key);
        return resp == null ? new ArrayList<>() : resp;
    }

    public static void putData(String key, Note note) {

        db.computeIfAbsent(key, k -> new ArrayList<>());
        db.get(key).add(note);

    }

    public static String getDbAsString() {
        return db.toString();
    }

}
