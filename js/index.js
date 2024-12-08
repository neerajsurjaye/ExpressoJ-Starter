import * as Effects from "./Effects.js";
let heroBackground = document.getElementById("scrl-back");

Effects.createScrollingTextBackground(heroBackground, [
    "Expresso expresso = Expresso.init();",
    "expresso.use(...)",
    "expresso.get(...)",
    "expresso.post(...)",
    "expresso.delete(...)",
    "expresso.put(...)",
    "new StaticFileServer(...)",
    "(req, res, next) -> {...}",
    'res.writeResponse("use1")',
    "next.next()",
    "new PathRouter();",
    "res.writeResponse(...);",
    "expresso.listen(5757);",
    "expresso.start();",
]);
