package io.ctsa.warehouseservice;

import io.ctsa.warehouseservice.stream.consumer.ConsumerChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(ConsumerChannels.class)
public class WarehouseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseServiceApplication.class, args);
    }
}
