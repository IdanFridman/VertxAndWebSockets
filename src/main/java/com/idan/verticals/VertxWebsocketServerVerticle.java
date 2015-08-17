package com.idan.verticals;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;

/**
 * Created by Ext_IdanF on 11/08/2015.
 */
public class VertxWebsocketServerVerticle extends AbstractVerticle {


    public void start() {
        vertx.createHttpServer().websocketHandler(webSocketHandler -> {

            System.out.println("Connected!");
            Buffer buff = Buffer.buffer().appendInt(12).appendString("foo");
            webSocketHandler.writeFinalBinaryFrame(buff);
            webSocketHandler.handler(buffer -> {
                String inputString = buffer.getString(0, buffer.length());
                System.out.println("inputString=" + inputString);
                vertx.executeBlocking(future -> {
                    vertx.eventBus().send("anAddress", inputString, event -> System.out.printf("got back from reply"));
                    future.complete();
                }, res -> {
                    if (res.succeeded()) {
                        webSocketHandler.writeFinalTextFrame("output=" + inputString + "_result");
                    }
                });

            });
        }).listen(8080);
    }


    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
