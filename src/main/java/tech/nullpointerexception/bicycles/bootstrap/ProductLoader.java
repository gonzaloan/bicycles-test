package tech.nullpointerexception.bicycles.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.nullpointerexception.bicycles.model.Product;
import tech.nullpointerexception.bicycles.model.Provider;
import tech.nullpointerexception.bicycles.repository.ProductRepository;

import java.util.Arrays;
import java.util.Collections;

/**
 * Componente Bootstrap para cargar un par de datos de prueba
 * al iniciar la aplicación.
 */
@Component
@RequiredArgsConstructor
public class ProductLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        loadProducts();
    }


    private void loadProducts() {

        if (productRepository.count() == 0) {

            productRepository.save(new Product("Bicicleta Oxford",
                    30,
                    Arrays.asList(
                            new Provider("9882171", "Oxford Chile"),
                            new Provider("17645404", "Bicis Santiago"))));

            productRepository.save(new Product("Bicicleta Mena I",
                    50,
                    Collections.singletonList(
                            new Provider("6666666", "Juanito Mena"))));

        }

    }


}
