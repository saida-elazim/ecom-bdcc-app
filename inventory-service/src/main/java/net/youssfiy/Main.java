package net.youssfiy;

import net.youssfiy.entities.Product;
import net.youssfiy.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args ->  {
            productRepository.save(Product.builder().id("P01").name("Computer").price(2300).quantity(6)
                    .build());
            productRepository.save(Product.builder().id("P02").name("Printer").price(1200).quantity(10)
                    .build());
            productRepository.save(Product.builder().id("P03").name("Smart Phone").price(4200).quantity(34)
                    .build());
        };

    }
}


