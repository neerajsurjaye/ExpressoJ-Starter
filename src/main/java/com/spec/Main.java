package com.spec;

import java.io.IOException;

import com.spec.web.expresso.Expresso;

public class Main {
    public static void main(String[] args) {

        Expresso expresso = Expresso.init();

        expresso.get("/home", (req, res) -> {
            try {
                res.getWriter().println("Welcome to home page!!!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        expresso.get("/about", (req, res) -> {
            try {
                res.getWriter().println("Information about the app");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        expresso.listen(5757);
    }
}