package com.idan.verticals;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.json.JsonObject;

/**
 * Created by Ext_IdanF on 11/08/2015.
 */
public class VertxWebsocketServerVerticle extends AbstractVerticle {


    public void start() {

        vertx.createHttpServer().websocketHandler(ws -> {
            vertx.eventBus().consumer("user-management", new Handler<Message<JsonObject>>() {
                @Override
                public void handle(Message<JsonObject> event) {
                    String id = event.body().getString("id");
                    if (event.body().getString("action").equalsIgnoreCase("disconnect")) {
                        ws.close();
                    }
                }
            });

            ws.path();
            vertx.sharedData().getLocalMap("users").put("111", ws.textHandlerID());

            System.out.println("Connected!");

            ws.handler(new Handler<Buffer>() {
                @Override
                public void handle(Buffer event) {
                    JsonObject msg = new JsonObject(event.toString());
                    String action = msg.getString("action");
                    if (action.equalsIgnoreCase("pos")) {
                        msg.put("address", ws.textHandlerID());
                        vertx.eventBus().send("geo-service", msg);
                    }
                }
            });
            vertx.eventBus().send(ws.textHandlerID(), "hello friend");
        }).listen(8080);
    }

    private void handleWebSocketRequests(ServerWebSocket webSocketHandler) {
        webSocketHandler.handler(buffer -> {
            String inputString = buffer.getString(0, buffer.length());
            System.out.println("inputString=" + inputString);
            vertx.executeBlocking(future -> vertx.eventBus().send("anAddress", inputString, handler -> {
                System.out.println("handler success2");
                String resultMsg= (String) handler.result().body();
                future.complete(resultMsg);
            }), res -> {
                if (res.succeeded()) {
                    webSocketHandler.writeFinalTextFrame("output=" + res.result().toString());
                }
            });

        });
    }


    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
