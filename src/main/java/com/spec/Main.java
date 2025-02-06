package com.spec;

import com.spec.web.expresso.Expresso;
import com.spec.web.expresso.router.PathRouter;

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

        expresso.delete("/delete", (req, res) -> {
            res.setResponse("deleting data");
        });

        expresso.listen(5757);
    }
}