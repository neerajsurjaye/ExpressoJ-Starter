package com.spec;

import java.io.IOException;

import com.spec.web.expresso.Expresso;

public class Main {
    public static void main(String[] args) {

        System.out.println("executing main");
        Expresso expresso = Expresso.init();

        expresso.get("/home", (req, res, ctx) -> {
            res.writeResponse("In home path");
            ctx.executeNextMiddleware();
        });

        expresso.get("/home", (req, res, ctx) -> {
            res.writeResponse("In home path");
        });

        expresso.get("/home", (req, res, ctx) -> {
            res.writeResponse("In home path");
        });

        expresso.listen(5757);
        expresso.start();
    }
}