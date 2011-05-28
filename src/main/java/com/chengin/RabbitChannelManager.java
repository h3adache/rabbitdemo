package com.chengin;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConnectionParameters;

import java.io.IOException;

import static com.chengin.RabbitConstants.*;
import static com.chengin.RabbitConstants.PORT;
import static com.chengin.RabbitConstants.EXCHANGE;
import static com.chengin.RabbitConstants.ROUTEKEY;

public class RabbitChannelManager {
    final Connection theConnection;
    final Channel theChannel;

    public RabbitChannelManager() throws IOException {
        final ConnectionParameters myParms = new ConnectionParameters();
        myParms.setUsername(USER);
        myParms.setPassword(PASSWORD);
        myParms.setVirtualHost(VHOST);

        myParms.setRequestedHeartbeat(0);
        final ConnectionFactory myFactory = new ConnectionFactory(myParms);
        theConnection = myFactory.newConnection(HOST, PORT);
        theChannel = theConnection.createChannel();
    }

    public final void connect() throws IOException {
        theChannel.exchangeDeclare(EXCHANGE, "direct");
        theChannel.queueDeclare(QUEUE);
        theChannel.queueBind(QUEUE, EXCHANGE, ROUTEKEY);
    }

    public final void disconnect() throws IOException {
        theChannel.close();
        theConnection.close();
    }

    public Channel getChannel() {
        return theChannel;
    }
}