package com.spec;

import java.io.IOException;

import com.spec.web.expresso.Expresso;

public class Main {
    public static void main(String[] args) {

        Expresso expresso = Expresso.init();

        expresso.get("/home", (req, res) -> {
            res.setResponse("In home path");
        });

        expresso.get("/about", (req, res) -> {
            res.setResponse("in about section");
        });

        expresso.post("/post", (req, res) -> {
            res.setResponse("Data posted");
        });

        expresso.put("/put", (req, res) -> {
            res.setResponse("In put path");
        });

        expresso.delete("/delete", (req, res) -> {
            res.setResponse("deleting data");
        });

        expresso.listen(5757);
    }
}