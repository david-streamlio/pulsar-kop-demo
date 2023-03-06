package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {

    private static final String TOPIC = "persistent://public/default/hello-world";
    private static Properties props;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Producer<String, String> producer = new KafkaProducer<String, String>(getProperties());

        for (int idx = 0; idx < 1000; idx++) {
            final Future<RecordMetadata> recordMetadataFuture = producer.send(
                    new ProducerRecord<>(TOPIC, "hello-" + idx));

            final RecordMetadata recordMetadata = recordMetadataFuture.get();
            System.out.println("Sent hello-" + idx + " to " + recordMetadata);
        }

        producer.flush();
        producer.close();

        final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singleton(TOPIC));

        // Consume some messages and quit immediately
        boolean running = true;
        while (running) {
            final ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(600));
            if (!records.isEmpty()) {
                records.forEach(record -> System.out.println("Receive record: " + record.value() + " from "
                        + record.topic() + "-" + record.partition() + "@" + record.offset()));

            } else {
                running = false;
            }
        }

        System.out.println("Stopping consumer");
        consumer.close();

    }

    private static final Properties getProperties() {
        props = new Properties();
        props.setProperty("bootstrap.servers", "127.0.0.1:9092");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        props.put(ConsumerConfig.GROUP_ID_CONFIG, "hello-world");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 30000);

        return props;
    }

}