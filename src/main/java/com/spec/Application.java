package com.spec;

import com.spec.middleware.AuthenticationMiddleware;
import com.spec.routers.AuthenticationRouter;
import com.spec.routers.NotesRestRouter;
import com.spec.web.expresso.Expresso;
import com.spec.web.expresso.middleware.standard.StaticResourceServer;

import lombok.extern.slf4j.Slf4j;

/**
 * The following class start the ExpressoJ server.
 */
@Slf4j
public class Application {
    public static void main(String[] args) {
        Expresso app = Expresso.init();

        app.use(new StaticResourceServer("/static"));

        // app.use(new
        // StaticFileServer("D:\\Coding\\Java\\ExpressoJ-Starter\\src\\main\\resources\\static"))

        app.use("/auth", AuthenticationRouter.getAuthRouter());

        app.use("/api/notes", new AuthenticationMiddleware(), NotesRestRouter.getNotesRestApiRouter());

        app.listenOnPort(5757);
        app.startServer();

    }
}