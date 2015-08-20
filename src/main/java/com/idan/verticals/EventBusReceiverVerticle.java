package com.idan.verticals;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * Created by Ext_IdanF on 10/08/2015.
 */
public class EventBusReceiverVerticle extends AbstractVerticle {

        private String name = null;

        public EventBusReceiverVerticle(String name) {
            this.name = name;
        }

        public void start() {
            vertx.eventBus().consumer("geo-service", new Handler<Message<JsonObject>>() {
                        @Override
                        public void handle(Message<JsonObject> event) {
                            // calculate friends from redis
                            vertx.eventBus().send(event.body().getString("address"), "444, 666, 888");

                        }
                    });
        }
    }

