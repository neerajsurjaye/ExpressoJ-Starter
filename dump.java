
public static void main(String[] args) {
    /* Initialize expresso */
    Expresso expresso = Expresso.init();
    /* Use a folder to host static files */
    expresso.use(
            "/static/*",
            new StaticFileServer("D:\\Coding\\Java\\expressojdemo\\src\\main\\webapp\\"));
    /*
     * .use() executes on all requests, regardless of the HTTP method.
     * You can also specify a path to restrict its execution to a perticular route.
     */
    expresso.use((req, res, next) -> {
        res.writeResponse("Executes on all requests");
        next.next();
    });
    /* Supports [get, post, put, delete] http methods */
    expresso.get("/home", (req, res, next) -> {
        res.writeResponse("get /home");
        next.next();
    });
    /* A router can be create a subset of routes. */
    PathRouter router = new PathRouter();

    router.get("/home", (req, res, next) -> {
        res.writeResponse("\nget inside router /home\n");
        next.next();
    });
    /*
     * Mount the router on the main Expresso instance with a path. All routes within
     * the router will be prefixed with the given path.
     */
    expresso.use("/customPath1", router);
    /* Set a listening address */
    expresso.listen(5757);
    /* Start the app */
    expresso.start();
}
