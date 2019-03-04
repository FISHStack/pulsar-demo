package com.lan.demo;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class PulsarServer {
    public static void main(String[] args) throws PulsarClientException {
        String localClusterUrl = "pulsar://192.168.43.88:6650";

        PulsarClient client = PulsarClient.builder().serviceUrl(localClusterUrl).build();
        Producer<byte[]> producer = client.newProducer().topic("my-topic").create();
//        producer.send("My message".getBytes());
        producer.sendAsync("My message".getBytes());


        ExecutorService executorService = Executors.newFixedThreadPool(100);

        do{
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        producer.send("My message".getBytes());
//                    } catch (PulsarClientException e) {
//                        System.out.println(e.getMessage());
//                    }
//                }
//            });
            producer.send(("My message"+System.currentTimeMillis()).getBytes());

        }while (true);
    }
}
