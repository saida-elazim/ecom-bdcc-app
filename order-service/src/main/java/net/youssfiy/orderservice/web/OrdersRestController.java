package net.youssfiy.orderservice.web;

import net.youssfiy.orderservice.entities.Order;
import net.youssfiy.orderservice.repository.OrderRepository;
import net.youssfiy.orderservice.restClient.InventoryRestClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrdersRestController {
    private OrderRepository orderRepository;

    private InventoryRestClient inventoryRestClient;

    public OrdersRestController(OrderRepository orderRepository, InventoryRestClient inventoryRestClient) {
        this.orderRepository = orderRepository;
        this.inventoryRestClient = inventoryRestClient;
    }

    @GetMapping("/orders")
   //creer une methode qui retourner la liste d'orders
    public List<Order> findAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
        allOrders.forEach(o->{
            o.getProductItems().forEach(pi->{

            });
        });
       return allOrders;
    }

    @GetMapping("/orders/{id}")
    public Order findOrderById(@PathVariable String id) {
        Order order = orderRepository.findById(id).get();
        order.getProductItems().forEach(pi-> {
           pi.setProduct(inventoryRestClient.findProductById(pi.getProductid()));
        });
        return order;
    }
}
