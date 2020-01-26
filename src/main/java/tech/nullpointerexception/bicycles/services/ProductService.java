package tech.nullpointerexception.bicycles.services;

import tech.nullpointerexception.bicycles.dto.ProductDto;
import tech.nullpointerexception.bicycles.dto.ProviderDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    /**
     * Retorna información de un producto.
     *
     * @param productId: Identificado del producto a consultar
     * @return Objeto con datos del producto
     */
    ProductDto getProductInfo(UUID productId);

    /**
     * Crea un nuevo producto.
     *
     * @param productDto: Información del nuevo producto a ingresar
     * @return Objeto Product.
     */
    ProductDto createProduct(ProductDto productDto, List<ProviderDto> providersList);

    /**
     * Actualizar un producto ya existente
     *
     * @param productId:     Identificador del producto a modificar
     * @param newProductDto: Datos del producto a modificar
     * @return El objeto actualizado
     */
    ProductDto updateProduct(UUID productId, ProductDto newProductDto);

    /**
     * Elimina un producto ya existente.
     *
     * @param product
     * @return
     */
    Boolean deleteProduct(UUID product);

    /**
     * Obtiene todos los productos
     *
     * @return Lista con toods los productos encontrados.
     */
    List<ProductDto> getAllProducts();

}
