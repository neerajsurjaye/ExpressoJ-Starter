package com.spec;

import java.io.IOException;

import com.spec.web.expresso.Expresso;

public class Main {
    public static void main(String[] args) {

        Expresso expresso = Expresso.init();

        expresso.get("/custompath", (req, res) -> {
            try {
                res.getWriter().println("Reponse from customPath");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        expresso.listen(8000);
    }
}