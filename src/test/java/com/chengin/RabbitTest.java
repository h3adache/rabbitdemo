package com.chengin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class RabbitTest {
    private RabbitProducer theProducer;


    @Before
    public void setup() throws IOException {
        theProducer = new RabbitProducer();
        theProducer.connect();
    }

    @After
    public void teardown() throws IOException {
        theProducer.disconnect();
    }

    @Test
    public void testMessaging() throws IOException {
        theProducer.sendTextMessage("Hello World!!");
    }
}
