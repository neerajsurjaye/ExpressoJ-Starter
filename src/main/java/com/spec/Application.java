package com.spec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import com.spec.controllers.Auth;
import com.spec.web.expresso.Expresso;

/**
 * The following class start the ExpressoJ server.
 */
public class Application {
    public static void main(String[] args) {
        Expresso app = Expresso.init();

        app.get("/home", (req, res, ctx) -> {
            res.writeResponse("data : testing docker");
        });

        app.get("/login", (req, res, ctx) -> {
            try {
                String body = req.body();

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        app.use(new Auth());

        app.use("/restricted", (req, res, ctx) -> {
            res.writeResponse("On restricted area");
        });

        app.listenOnPort(5757);
        app.startServer();

    }
}