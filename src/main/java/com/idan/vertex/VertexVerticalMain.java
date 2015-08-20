package com.idan.vertex;

import com.idan.verticals.EventBusReceiverVerticle;
import com.idan.verticals.EventBusSenderVerticle;
import com.idan.verticals.VertxWebsocketServerVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * Created by Ext_IdanF on 10/08/2015.
 */
public class VertexVerticalMain {


    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
       vertx.deployVerticle(new EventBusReceiverVerticle("R1"),new DeploymentOptions().setWorker(true),handler->{
           if (handler.succeeded())
           {
               System.out.printf("handler success1");
           }
       });
        //Thread.sleep(3000);
        vertx.deployVerticle(new EventBusSenderVerticle());
       // vertx.deployVerticle(new VertxHttpServerVerticle());
       // vertx.deployVerticle(new VertxTcpServerVerticle());
        vertx.deployVerticle(new VertxWebsocketServerVerticle());

        JsonObject dbJson = new JsonObject();
        dbJson.put("address", "database.vertx");
        dbJson.put("connection", "MySQL");
        dbJson.put("host", "127.0.0.1");
        dbJson.put("port", "3306");
        dbJson.put("username", "root");
        dbJson.put("password", "password");
        dbJson.put("database", "notification");
       // vertx.deployVerticle(new WorkerVerticle(), new DeploymentOptions().setWorker(true));

    }
}
