package net.youssfiy.repository;

import net.youssfiy.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {


}
