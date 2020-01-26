package tech.nullpointerexception.bicycles.util;

import tech.nullpointerexception.bicycles.dto.ProductCreationDto;
import tech.nullpointerexception.bicycles.dto.ProductDto;
import tech.nullpointerexception.bicycles.dto.ProviderDto;

import java.util.Arrays;
import java.util.UUID;

public class TestConstants {

    public static ProviderDto GET_PROVIDER() {
        return ProviderDto.builder()
                .id(1L)
                .providerDni("98221719")
                .providerName("Bicicletas Mena")
                .build();
    }

    public static ProductDto GET_PRODUCT() {
        return ProductDto.builder()
                .id(UUID.randomUUID())
                .productName("Bicicletita")
                .productQuantity(30)
                .build();
    }

    public static ProductCreationDto GET_PRODUCT_CREATION() {
        return ProductCreationDto.builder()
                .productDto(TestConstants.GET_PRODUCT())
                .providers(Arrays.asList(TestConstants.GET_PROVIDER())).build();

    }
}
