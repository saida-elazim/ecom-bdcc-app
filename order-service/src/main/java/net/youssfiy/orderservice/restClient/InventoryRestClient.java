package net.youssfiy.orderservice.restClient;

import net.youssfiy.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import net.youssfiy.orderservice.restClient.InventoryRestClient;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@FeignClient(url = "http://localhost:8087", name = "inventory-service")
public interface InventoryRestClient {

    @GetMapping("/api/products")
     List<Product> getAllProducts();

    @GetMapping("/api/products/{id}")
    Product findProductById(@PathVariable String id);
}
