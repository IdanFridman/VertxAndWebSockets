package com.idan.verticals;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

/**
 * Created by Ext_IdanF on 11/08/2015.
 */
public class VertxHttpServerVerticle extends AbstractVerticle {

    private HttpServer httpServer = null;

    @Override
    public void start() throws Exception {
        httpServer = vertx.createHttpServer();
        httpServer.requestHandler(req -> {
            System.out.printf("incoming request\n");
            HttpServerResponse response = req.response();
            response.setStatusCode(200);
            response.headers()
                    .add("Content-Length", String.valueOf(57))
                    .add("Content-Type", "text/html");
            response.write("Vert.X is alive");
            response.end();

        });
        httpServer.listen(8080);

    }


}
