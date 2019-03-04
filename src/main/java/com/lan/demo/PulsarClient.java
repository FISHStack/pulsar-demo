package com.lan.demo;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClientException;

public class PulsarClient {
    public static void main(String[] args) throws PulsarClientException {
        String localClusterUrl = "pulsar://192.168.43.88:6650";

        org.apache.pulsar.client.api.PulsarClient client = org.apache.pulsar.client.api.PulsarClient.builder().serviceUrl(localClusterUrl).build();

        Consumer consumer = client.newConsumer()
                .topic("my-topic")
                .subscriptionName("my-subscription4")
                .subscribe();

        do {
            // Wait for a message
            Message msg = consumer.receive();

            System.out.printf("Message received: %s", new String(msg.getData()));
            System.out.println("\n");

            // Acknowledge the message so that it can be deleted by the message broker
            consumer.acknowledge(msg);
        } while (true);
    }
}
