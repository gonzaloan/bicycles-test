package tech.nullpointerexception.bicycles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.nullpointerexception.bicycles.model.Product;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findById(UUID id);


    void deleteById(UUID providerDni);


}
