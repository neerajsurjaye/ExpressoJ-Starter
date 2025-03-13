package com.spec.controllers;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spec.constants.NotesAppConstants;
import com.spec.db.NotesDB;
import com.spec.models.Note;
import com.spec.web.expresso.router.PathRouter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotesRestController {

    public static PathRouter getNotesRestApiRouter() {
        PathRouter router = new PathRouter();

        router.get((req, res, ctx) -> {
            try {

                String body = req.body();
                JsonElement jsonElement = JsonParser.parseString(body);
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                String userName = jsonObject.get("username").getAsString();

                log.error(userName);
                log.error(NotesDB.getDbAsString());
                List<Note> userNotes = NotesDB.getData(userName);

                String userNotesJsonString = new Gson().toJson(userNotes);
                res.setContentTypeHeader(NotesAppConstants.MIME_JSON);
                res.writeResponse(userNotesJsonString);

            } catch (IOException e) {
                res.resetResponse();
                res.setStatusCode(500).writeResponse("Internal Server Error");
            }
        });

        router.post((req, res, ctx) -> {
            String body;
            try {
                body = req.body();
                Gson gson = new Gson();

                JsonElement jsonElement = JsonParser.parseString(body);
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonElement notejsonElement = jsonObject.get("note");
                String userName = jsonObject.get("username").getAsString();

                Note note = gson.fromJson(notejsonElement, Note.class);

                NotesDB.putData(userName, note);

            } catch (IOException e) {
                res.resetResponse();
                res.setStatusCode(500).writeResponse("Internal Server Error");
            }

        });

        return router;
    }

}
