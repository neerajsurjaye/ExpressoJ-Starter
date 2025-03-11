package com.spec.db;

import java.util.concurrent.ConcurrentHashMap;

/**
 * The class acts as simple in memory keyvalue database.
 */
public class Store {

    private Store() {
        // pass
    }

    private static ConcurrentHashMap<String, Object> db = new ConcurrentHashMap<>();

    public static Object getData(String key) {
        return db.get(key);
    }

    public static void putData(String key, Object value) {
        db.put(key, value);
    }

}
