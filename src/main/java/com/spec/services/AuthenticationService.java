package com.spec.services;

import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spec.constants.NotesAppConstants;
import com.spec.db.UsersDB;

public class AuthenticationService {

    private AuthenticationService() {
        // pass
    }

    public static String login(String body) {
        JsonObject jsonBody = JsonParser.parseString(body).getAsJsonObject();

        String username = jsonBody.get(NotesAppConstants.AUTH_USERNAME).getAsString();
        String password = jsonBody.get(NotesAppConstants.AUTH_PASSOWRD).getAsString();

        if (UsersDB.verifyCreds(username, password)) {
            return generateJWTforUser(username);
        }
        return null;

    }

    public static String signUp(String body) {
        JsonObject jsonBody = JsonParser.parseString(body).getAsJsonObject();

        String username = jsonBody.get(NotesAppConstants.AUTH_USERNAME).getAsString();
        String password = jsonBody.get(NotesAppConstants.AUTH_PASSOWRD).getAsString();

        if (!UsersDB.isUserPresent(username)) {
            UsersDB.addUser(username, password);
            return generateJWTforUser(username);
        }

        return null;

    }

    public static String generateJWTforUser(String username) {
        Algorithm signingAlgorithm = Algorithm.HMAC256(NotesAppConstants.AUTH_CRED_KEY);
        return JWT.create()
                .withClaim(NotesAppConstants.AUTH_USERNAME, username)
                .withJWTId(UUID.randomUUID().toString())
                .sign(signingAlgorithm);
    }

}
