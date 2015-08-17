package com.idan.verticals;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Created by Ext_IdanF on 10/08/2015.
 */
public class EventBusReceiverVerticle extends AbstractVerticle {

        private String name = null;

        public EventBusReceiverVerticle(String name) {
            this.name = name;
        }

        public void start(Future<Void> startFuture) {
            vertx.eventBus().consumer("anAddress", message -> {
                System.out.println(this.name +
                        " received message: " +
                        message.body());
                try {
                    //doing some looong work..
                    Thread.sleep(5000);
                    System.out.printf("finished waiting\n");
                    message.reply("I came from a long working job");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

