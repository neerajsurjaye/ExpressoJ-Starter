package com.spec;

import java.io.IOException;

import com.spec.controllers.AuthenticationController;
import com.spec.controllers.NotesRestController;
import com.spec.controllers.NotesController;
import com.spec.web.expresso.Expresso;
import com.spec.web.expresso.middleware.standard.StaticResourceServer;

/**
 * The following class start the ExpressoJ server.
 */
public class Application {
    public static void main(String[] args) {
        Expresso app = Expresso.init();

        app.use(new StaticResourceServer("/static"));

        app.use(AuthenticationController.getAuthRouter());

        app.use("/api/notes", NotesRestController.getNotesRestApiRouter());

        app.use("/notes", NotesController.getNotesRouter());

        app.listenOnPort(5757);
        app.startServer();

    }
}