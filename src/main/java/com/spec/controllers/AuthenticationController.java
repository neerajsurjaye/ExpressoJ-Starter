package com.spec.controllers;

import java.io.IOException;

import com.spec.web.expresso.router.PathRouter;

public class AuthenticationController {

    public static PathRouter getAuthRouter() {

        PathRouter router = new PathRouter();

        router.get("/login", (req, res, ctx) -> {
            try {
                String body = req.body();

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        return router;

    }

}
