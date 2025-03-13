package com.spec.services;

import com.spec.web.expresso.message.HttpRequest;
import com.spec.web.expresso.message.HttpResponse;
import com.spec.web.expresso.middleware.Middleware;
import com.spec.web.expresso.middleware.MiddlewareContext;
import jakarta.servlet.http.Cookie;

public class Authenticator implements Middleware {

    @Override
    public void execute(HttpRequest req, HttpResponse res, MiddlewareContext ctx) {

        Cookie[] cookies = req.getCookies();
        String authStatus = null;

        for (Cookie c : cookies) {
            if (c.getName().equals("auth")) {
                authStatus = c.getValue();
                break;
            }
        }

        if (authStatus != null && authStatus.equals("true")) {
            ctx.executeNextMiddleware();
            return;
        }

        res.setStatusCode(403).writeResponse("Forbidden: Kindly login");

    }

}
