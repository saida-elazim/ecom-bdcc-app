package net.youssfiy.orderservice;

import lombok.Builder;
import net.youssfiy.orderservice.entities.Order;
import net.youssfiy.orderservice.entities.OrderState;
import net.youssfiy.orderservice.entities.ProductItem;
import net.youssfiy.orderservice.model.Product;
import net.youssfiy.orderservice.repository.OrderRepository;
import net.youssfiy.orderservice.repository.ProductItemRepository;
import net.youssfiy.orderservice.restClient.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            OrderRepository orderRepository,
            ProductItemRepository productItemRepository,
            InventoryRestClient inventoryRestClient

    ) {
        return args -> {
           // List<Product> allProducts = inventoryRestClient.getAllProducts();
            List<String> productIds = List.of("P01", "P02", "P03");
            for (int i = 0; i < 5; i++) {
                Order order = Order.builder()
                        .id(UUID.randomUUID().toString())
                        .date(LocalDate.now())
                        .state(OrderState.PENDING)
                        .build();
                Order savedOrder = orderRepository.save(order);

                productIds.forEach(pId -> {
                    ProductItem productItem = ProductItem.builder()
                            .productid(pId)
                            .quantity(new Random().nextInt(29))
                            .price(Math.random() * 6000+1000)
                            .order(savedOrder)
                            .build();
                productItemRepository.save(productItem);
                });

            }


        };

    }
}