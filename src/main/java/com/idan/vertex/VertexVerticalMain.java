package com.idan.vertex;

import com.idan.verticals.VertxWebsocketServerVerticle;
import io.vertx.core.Vertx;

/**
 * Created by Ext_IdanF on 10/08/2015.
 */
public class VertexVerticalMain {


    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
       // vertx.deployVerticle(new EventBusReceiverVerticle("R1"));
        //Thread.sleep(3000);
        //vertx.deployVerticle(new EventBusSenderVerticle());
       // vertx.deployVerticle(new VertxHttpServerVerticle());
       // vertx.deployVerticle(new VertxTcpServerVerticle());
        vertx.deployVerticle(new VertxWebsocketServerVerticle());

    }
}
