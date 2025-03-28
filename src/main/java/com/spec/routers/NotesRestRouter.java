package com.spec.routers;

import java.io.IOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spec.constants.NotesAppConstants;
import com.spec.services.NotesService;
import com.spec.web.expresso.router.PathRouter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotesRestRouter {

    public static PathRouter getNotesRestApiRouter() {

        PathRouter router = new PathRouter();

        router.get((req, res, ctx) -> {
            JsonObject jsonResponse = new JsonObject();

            try {
                String userNotesJsonString = NotesService
                        .getNotesAsJsonString(ctx.getState(NotesAppConstants.USER_NAME));

                res.setContentTypeHeader(NotesAppConstants.MIME_JSON);
                jsonResponse.addProperty(NotesAppConstants.RESP_STATUS, "success");
                jsonResponse.add("notes", JsonParser.parseString(userNotesJsonString));
                res.setStatusCode(200).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                        .writeResponse(jsonResponse.toString());

            } catch (Exception e) {
                log.error("Got exception : {}", e);
                jsonResponse.addProperty(NotesAppConstants.RESP_STATUS, "fail");
                res.setStatusCode(500).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                        .writeResponse(jsonResponse.toString());
            }

        });

        router.post((req, res, ctx) -> {

            JsonObject jsonResponse = new JsonObject();

            try {
                String noteAdded = NotesService.addNote(req.body(), ctx.getState(NotesAppConstants.USER_NAME));
                jsonResponse.addProperty(NotesAppConstants.RESP_STATUS, "success");
                jsonResponse.add("note", JsonParser.parseString(noteAdded));
                res.setStatusCode(200).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                        .writeResponse(jsonResponse.toString());

            } catch (IOException e) {
                log.error("Got exception : {}", e);
                jsonResponse.addProperty(NotesAppConstants.RESP_STATUS, "fail");
                res.setStatusCode(500).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                        .writeResponse(jsonResponse.toString());
            }

        });

        router.put((req, res, ctx) -> {

            JsonObject jsonResponse = new JsonObject();

            try {
                String noteUpdated = NotesService.updateNote(req.body(), ctx.getState(NotesAppConstants.USER_NAME));

                jsonResponse.addProperty(NotesAppConstants.RESP_STATUS, "success");
                jsonResponse.add("note", JsonParser.parseString(noteUpdated));
                res.setStatusCode(200).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                        .writeResponse(jsonResponse.toString());

            } catch (Exception e) {
                log.error("Got exception : {}", e);
                jsonResponse.addProperty(NotesAppConstants.RESP_STATUS, "fail");
                res.setStatusCode(500).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                        .writeResponse(jsonResponse.toString());
            }

        });

        router.delete((req, res, ctx) -> {
            JsonObject jsonResponse = new JsonObject();

            try {
                String noteDeleted = NotesService.deleteNote(req.body(), ctx.getState(NotesAppConstants.USER_NAME));

                jsonResponse.addProperty(NotesAppConstants.RESP_STATUS, "success");
                jsonResponse.add("note", JsonParser.parseString(noteDeleted));
                res.setStatusCode(200).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                        .writeResponse(jsonResponse.toString());
            } catch (Exception e) {
                log.error("Got exception : {}", e);
                jsonResponse.addProperty(NotesAppConstants.RESP_STATUS, "fail");
                res.setStatusCode(500).setContentTypeHeader(NotesAppConstants.MIME_JSON)
                        .writeResponse(jsonResponse.toString());
            }

        });

        return router;
    }

}
