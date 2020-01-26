package tech.nullpointerexception.bicycles.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.nullpointerexception.bicycles.dto.ProductDto;
import tech.nullpointerexception.bicycles.dto.ProviderDto;
import tech.nullpointerexception.bicycles.exception.NotFoundException;
import tech.nullpointerexception.bicycles.exception.ProductException;
import tech.nullpointerexception.bicycles.mappers.ProductMapper;
import tech.nullpointerexception.bicycles.mappers.ProviderMapper;
import tech.nullpointerexception.bicycles.model.Product;
import tech.nullpointerexception.bicycles.model.Provider;
import tech.nullpointerexception.bicycles.repository.ProductRepository;
import tech.nullpointerexception.bicycles.repository.ProviderRepository;
import tech.nullpointerexception.bicycles.services.ProductService;
import tech.nullpointerexception.bicycles.util.UtilConstants;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProviderRepository providerRepository;

    @Override
    public ProductDto getProductInfo(UUID productId) {
        log.debug("Dentro de GetProvider");
        return Optional.ofNullable(productRepository.findById(productId))
                .map(ProductMapper.INSTANCE::productToProductDto)
                .orElse(null);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto, List<ProviderDto> providersList) {

        log.debug("Creando un product {}", productDto.toString());
        if (productRepository.findById(productDto.getId()) != null)
            throw new ProductException(UtilConstants.CANT_REPEAT_PRODUCT_ID);

        //A partir de esta lista se van a crear nuevos proveedores si no existen, sino,
        //el producto se asigna a proveedor ya existente
        List<Provider> finalProviders = providersList
                .stream()
                .map(x -> {
                    Provider provider = providerRepository.findByProviderDni(x.getProviderDni());
                    if (provider == null) {
                        return ProviderMapper.INSTANCE.providerDtoToProvider(x);
                    } else {
                        return provider;
                    }
                }).collect(Collectors.toList());

        return Optional.of(productDto)
                .map(ProductMapper.INSTANCE::productDtoToProduct)
                .map(product -> new Product(product.getProductName(), product.getProductQuantity(), finalProviders))
                .map(productRepository::save)
                .map(ProductMapper.INSTANCE::productToProductDto)
                .orElse(null);

    }

    @Override
    public ProductDto updateProduct(UUID productId, ProductDto newProductDto) {

        log.debug("Actualizando un Producto {}", productId);
        Product product = Optional.ofNullable(productRepository.findById(productId)).orElseThrow(NotFoundException::new);
        product.setProductName(newProductDto.getProductName());
        product.setProductQuantity(newProductDto.getProductQuantity());


        return Optional.of(product)
                .map(productRepository::save)
                .map(ProductMapper.INSTANCE::productToProductDto)
                .orElse(null);
    }

    @Override
    public Boolean deleteProduct(UUID product) {
        log.debug("Deleting a Product: {}", product);
        try {
            productRepository.deleteById(product);
        } catch (Exception e) {
            log.error("Ocurrio un error al borrar el producto {}", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        log.debug("Dentro de getAllProducts");
        return Optional.of(productRepository.findAll()
                .stream()
                .map(ProductMapper.INSTANCE::productToProductDto)
                .collect(Collectors.toList()))
                .orElse(null);
    }
}
