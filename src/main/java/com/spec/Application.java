package com.spec;

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

        app.listenOnPort(5757);
        app.startServer();

    }
}