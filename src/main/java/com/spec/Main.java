package com.spec;

import com.spec.web.expresso.Expresso;
import com.spec.web.expresso.router.PathRouter;

public class Main {
    public static void main(String[] args) {

        Expresso expresso = Expresso.init();

        expresso.use((req, res, next) -> {
            res.writeResponse("use1");
            next.next();
        });

        expresso.use("/home", (req, res, next) -> {
            res.writeResponse("Using /home \n");
            next.next();
        });

        expresso.get("/home", (req, res, next) -> {
            res.writeResponse("Getting /home\n");
            next.next();
        });

        expresso.post("/home", (req, res, next) -> {
            res.writeResponse("posting on /home\n");
            next.next();
        });

        PathRouter router = new PathRouter();
        router.use("/about", (req, res, next) -> {
            res.writeResponse("using /about in path router \n");
            next.next();
        });

        router.use("/etc", (req, res, next) -> {
            res.writeResponse("using /etc in path router \n");
            next.next();
        });

        router.get("/home", (req, res, next) -> {
            res.writeResponse("getting /home in path router\n");
            next.next();
        });

        expresso.use("/user", router);

        expresso.put("/home", (req, res, next) -> {
            res.writeResponse("updaing /home\n");
        });

        expresso.delete("/home", (req, res, next) -> {
            res.writeResponse("deleting /home\n");
        });

        expresso.get("/gethtml", (req, res, next) -> {
            res.setHtml(
                    "<html><head><title>Simple Document</title></head><body><h1>Welcome to My Page</h1><p>This is a simple HTML document.</p></body></html>\n");
        });

        expresso.listen(5757);
        expresso.start();

    }
}