package com.spec.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spec.db.UsersDB;

public class AuthenticationService {

    private AuthenticationService() {
        // pass
    }

    public static String login(String body) {
        JsonObject jsonBody = JsonParser.parseString(body).getAsJsonObject();

        String username = jsonBody.get("username").getAsString();
        String password = jsonBody.get("password").getAsString();

        if (UsersDB.verifyCreds(username, password)) {
            return generateJWTforUser(username);
        }
        return null;

    }

    public static String signUp(String body) {
        JsonObject jsonBody = JsonParser.parseString(body).getAsJsonObject();

        String username = jsonBody.get("username").getAsString();
        String password = jsonBody.get("password").getAsString();

        if (!UsersDB.isUserPresent(username)) {
            UsersDB.addUser(username, password);
            return generateJWTforUser(username);
        }

        return null;

    }

    public static String generateJWTforUser(String username) {
        Algorithm signingAlgorithm = Algorithm.HMAC256("super-secret-key");
        return JWT.create().withClaim("username", username).sign(signingAlgorithm);
    }

}
