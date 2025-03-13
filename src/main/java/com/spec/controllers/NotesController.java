package com.spec.controllers;

import com.spec.services.Authenticator;
import com.spec.web.expresso.router.PathRouter;

public class NotesController {

    public static PathRouter getNotesRouter() {

        PathRouter router = new PathRouter();

        router.use(new Authenticator());

        return router;

    }

}
