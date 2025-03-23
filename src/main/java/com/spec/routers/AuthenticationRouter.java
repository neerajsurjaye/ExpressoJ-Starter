package com.spec.routers;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spec.constants.NotesAppConstants;
import com.spec.services.AuthenticationService;
import com.spec.web.expresso.router.PathRouter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationRouter {

    public static PathRouter getAuthRouter() {

        PathRouter router = new PathRouter();

        router.post("/login", (req, res, ctx) -> {
            JsonObject jsonResponse = new JsonObject();

            try {
                String body = req.body();
                String jwt = AuthenticationService.login(body);

                res.setContentTypeHeader(NotesAppConstants.MIME_JSON);

                if (jwt != null) {
                    jsonResponse.addProperty("status", "success");
                    jsonResponse.add("jwt", JsonParser.parseString(jwt));
                    res.setStatusCode(200).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                            .writeResponse(jsonResponse.toString());
                } else {
                    jsonResponse.addProperty("status", "fail");
                    jsonResponse.addProperty("message", "Invalid username/password");
                    res.setStatusCode(500).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                            .writeResponse(jsonResponse.toString());
                }

            } catch (IOException e) {
                log.error("Got exception : {}", e);
                jsonResponse.addProperty("status", "fail");
                res.setStatusCode(500).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                        .writeResponse(jsonResponse.toString());
            }

        });

        router.post("/signup", (req, res, ctx) -> {
            JsonObject jsonResponse = new JsonObject();

            try {
                String body = req.body();
                String jwt = AuthenticationService.signUp(body);

                if (jwt != null) {
                    jsonResponse.addProperty("status", "success");
                    jsonResponse.add("jwt", JsonParser.parseString(jwt));
                    res.setStatusCode(200).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                            .writeResponse(jsonResponse.toString());
                } else {
                    jsonResponse.addProperty("status", "fail");
                    jsonResponse.addProperty("message", "user already exists");
                    res.setStatusCode(500).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                            .writeResponse(jsonResponse.toString());
                }

            } catch (IOException e) {
                log.error("Got exception : {}", e);
                jsonResponse.addProperty("status", "fail");
                res.setStatusCode(500).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                        .writeResponse(jsonResponse.toString());
            }

        });

        return router;

    }

}
