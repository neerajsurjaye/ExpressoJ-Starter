package com.spec.db;

import java.util.concurrent.ConcurrentHashMap;

/**
 * The class acts as simple in memory keyvalue database.
 */
public class UsersDB {

    private static ConcurrentHashMap<String, String> userDb = new ConcurrentHashMap<>();

    private UsersDB() {
        // pass
    }

    public static void addUser(String userName, String password) {
        userDb.put(userName, password);
    }

    public static boolean verifyCreds(String userName, String password) {
        return userDb.containsKey(userName) && userDb.get(userName).equals(password);
    }

    public static boolean isUserPresent(String userName) {
        return userDb.containsKey(userName);
    }

}
