package com.chengin;

import java.io.IOException;

import static com.chengin.RabbitConstants.EXCHANGE;
import static com.chengin.RabbitConstants.ROUTEKEY;
import static com.rabbitmq.client.MessageProperties.PERSISTENT_TEXT_PLAIN;

public class RabbitProducer {
    private final RabbitChannelManager theChannelManager = new RabbitChannelManager();

    public RabbitProducer() throws IOException {
    }

    public final void connect() throws IOException {
        theChannelManager.connect();
    }

    public final void sendTextMessage(String aMessage) throws IOException {
        theChannelManager.getChannel().basicPublish(
                EXCHANGE,
                ROUTEKEY,
                PERSISTENT_TEXT_PLAIN,
                aMessage.getBytes());
    }

    public final void disconnect() throws IOException {
        theChannelManager.disconnect();
    }

    public static void main(String[] args) throws IOException {
        final RabbitProducer myProducer = new RabbitProducer();
        myProducer.connect();
        myProducer.sendTextMessage("Hello World!");
        myProducer.disconnect();
    }
}
