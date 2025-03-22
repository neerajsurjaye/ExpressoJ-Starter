package com.spec.middleware;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.JsonObject;
import com.spec.constants.NotesAppConstants;
import com.spec.db.UsersDB;
import com.spec.web.expresso.message.HttpRequest;
import com.spec.web.expresso.message.HttpResponse;
import com.spec.web.expresso.middleware.Middleware;
import com.spec.web.expresso.middleware.MiddlewareContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationMiddleware implements Middleware {

    @Override
    public void execute(HttpRequest req, HttpResponse res, MiddlewareContext ctx) {

        log.error("Inside authincation middleware");
        String authHeader = req.getHeader("auth");
        JsonObject jsonResponse = new JsonObject();

        if (authHeader == null) {
            // Doesnt calls next middlewares
            jsonResponse.addProperty("status", "fail");
            jsonResponse.addProperty("message", "Authorization required to access following API");
            res.setStatusCode(401).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                    .writeResponse(jsonResponse.toString());
            return;
        }
        String[] tokenSplit = authHeader.split(" ");

        if (tokenSplit.length < 2) {
            jsonResponse.addProperty("status", "fail");
            jsonResponse.addProperty("message", "Bad auth header");
            res.setStatusCode(401).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                    .writeResponse(jsonResponse.toString());
            return;
        }

        String jwtToken = tokenSplit[1];
        DecodedJWT decodedJWT;

        try {
            decodedJWT = new JWT().decodeJwt(jwtToken);
        } catch (JWTDecodeException exception) {
            jsonResponse.addProperty("status", "fail");
            jsonResponse.addProperty("message", "Error parsing JWT");
            res.setStatusCode(500).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                    .writeResponse(jsonResponse.toString());
            return;
        }

        String userName = decodedJWT.getClaim("username").asString();

        if (!UsersDB.isUserPresent(userName)) {

            jsonResponse.addProperty("status", "fail");
            jsonResponse.addProperty("message", "user doesn't exist");
            res.setStatusCode(500).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                    .writeResponse(jsonResponse.toString());

            return;
        }
        ctx.putState(NotesAppConstants.USER_NAME, userName);
        ctx.executeNextMiddleware();

    }

}
