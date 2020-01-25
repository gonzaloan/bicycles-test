package tech.nullpointerexception.bicycles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nullpointerexception.bicycles.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
