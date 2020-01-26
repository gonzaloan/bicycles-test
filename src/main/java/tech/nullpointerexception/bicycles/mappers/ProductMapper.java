package tech.nullpointerexception.bicycles.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.nullpointerexception.bicycles.model.Product;
import tech.nullpointerexception.bicycles.dto.ProductDto;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto productToProductDto(Product provider);

    Product productDtoToProduct(ProductDto providerDto);
}
