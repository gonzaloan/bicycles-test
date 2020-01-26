package tech.nullpointerexception.bicycles.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.nullpointerexception.bicycles.dto.ProductDto;
import tech.nullpointerexception.bicycles.mappers.ProductMapper;
import tech.nullpointerexception.bicycles.model.Product;
import tech.nullpointerexception.bicycles.repository.ProductRepository;
import tech.nullpointerexception.bicycles.repository.ProviderRepository;
import tech.nullpointerexception.bicycles.util.TestConstants;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProviderRepository providerRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void getProduct() {

        Mockito.when(productRepository.findById(any(UUID.class)))
                .thenReturn(ProductMapper.INSTANCE.productDtoToProduct(TestConstants.GET_PRODUCT()));
        ProductDto product = productService.getProductInfo(TestConstants.GET_PRODUCT().getId());

        assertThat(product).isNotNull();
        assertThat(product.getProductQuantity()).isEqualTo(TestConstants.GET_PRODUCT().getProductQuantity());
        assertThat(product.getProductName()).isEqualTo(TestConstants.GET_PRODUCT().getProductName());

    }

    @Test
    void createProduct() {

        Mockito.when(providerRepository.findByProviderDni(any())).thenReturn(null);
        Mockito.when(productRepository.save(any()))
                .thenReturn(ProductMapper.INSTANCE.productDtoToProduct(TestConstants.GET_PRODUCT()));

        ProductDto product = productService.createProduct(TestConstants.GET_PRODUCT(), TestConstants.GET_PRODUCT_CREATION().getProviders());

        assertThat(product).isNotNull();
        assertThat(product.getProductQuantity()).isEqualTo(TestConstants.GET_PRODUCT().getProductQuantity());
        assertThat(product.getProductName()).isEqualTo(TestConstants.GET_PRODUCT().getProductName());


    }

    @Test
    void updateProduct() {
        Mockito.when(productRepository.findById(any(UUID.class)))
                .thenReturn(ProductMapper.INSTANCE.productDtoToProduct(TestConstants.GET_PRODUCT()));

        Mockito.when(productRepository.save(any()))
                .thenReturn(ProductMapper.INSTANCE.productDtoToProduct(TestConstants.GET_PRODUCT()));

        ProductDto product = productService.updateProduct(TestConstants.GET_PRODUCT().getId(), TestConstants.GET_PRODUCT());
        assertThat(product).isNotNull();
        assertThat(product.getProductQuantity()).isEqualTo(TestConstants.GET_PRODUCT().getProductQuantity());
        assertThat(product.getProductName()).isEqualTo(TestConstants.GET_PRODUCT().getProductName());


    }

    @Test
    void deleteByProductDni() {
        UUID id = TestConstants.GET_PRODUCT().getId();
        productService.deleteProduct(id);
        verify(productRepository, times(1)).deleteById(eq(id));
    }

    @Test
    void findAll(){
        List<Product> products = Stream.of(TestConstants.GET_PRODUCT())
                .map(ProductMapper.INSTANCE::productDtoToProduct)
                .collect(Collectors.toList());
        Mockito.when(productRepository.findAll())
                .thenReturn(products);
        List<ProductDto> product = productService.getAllProducts();

        assertThat(product).isNotNull();
        assertThat(product).hasSize(1);

    }
}