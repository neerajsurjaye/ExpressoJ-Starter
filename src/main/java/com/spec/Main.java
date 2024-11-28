package com.spec;

import java.io.IOException;

import com.spec.web.expresso.Expresso;
import com.spec.web.expresso.middleware.standard.StaticFileServer;
import com.spec.web.expresso.router.PathRouter;

public class Main {
    public static void main(String[] args) {

        Expresso expresso = Expresso.init();

        expresso.use("/static/*", new StaticFileServer("D:\\Coding\\Java\\expressojdemo\\src\\main\\webapp\\"));

        expresso.use((req, res, next) -> {
            res.writeResponse("use1");
            next.next();
        });

        expresso.use("/home", (req, res, next) -> {
            res.writeResponse("use /home");
            next.next();
        });

        expresso.get("/home", (req, res, next) -> {
            res.writeResponse("\nget /home\n");
            next.next();
        });

        expresso.post("/post", (req, res, next) -> {
            res.writeResponse("\npost /post\n");
            next.next();
        });

        PathRouter router = new PathRouter();
        router.use("/about", (req, res, next) -> {
            res.writeResponse("use / about");
            next.next();
        });

        router.use("/etc", (req, res, next) -> {
            res.writeResponse("use /etc");
            next.next();
        });

        router.get("/home", (req, res, next) -> {
            res.writeResponse("\nget inside router /home\n");
            next.next();
        });

        expresso.use(router);

        expresso.use("/customPath1", router);

        expresso.use((req, res, next) -> {
            res.writeResponse("end");
            next.next();
        });

        expresso.put("/home", (req, res, next) -> {
            res.writeResponse("putting");
        });

        expresso.delete("/home", (req, res, next) -> {
            res.writeResponse("deleting");
        });

        expresso.get("/gethtml", (req, res, next) -> {
            res.setHtml(
                    "<html><head><title>Simple Document</title></head><body><h1>Welcome to My Page</h1><p>This is a simple HTML document.</p></body></html>\n");
        });

        expresso._log_Metadata();

        expresso.listen(5757);
        expresso.start();

    }
}