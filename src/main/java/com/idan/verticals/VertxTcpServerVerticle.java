package com.idan.verticals;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.NetServer;

/**
 * Created by Ext_IdanF on 11/08/2015.
 */
public class VertxTcpServerVerticle extends AbstractVerticle {


    @Override
    public void start() throws Exception {
        NetServer server = vertx.createNetServer();
        server.connectHandler(socket -> {
            System.out.println("Incoming connection");
            socket.handler(inBuffer -> {
                System.out.println("incoming data: " + inBuffer.length());
                socket.write("some data");
               /* String data =
                        inBuffer.getString(0, inBuffer.length());
                Buffer outBuffer = Buffer.buffer();
                outBuffer.appendString("response...");
                socket.write(outBuffer);*/
            });
        });


        server.listen(10000);
    }


    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
