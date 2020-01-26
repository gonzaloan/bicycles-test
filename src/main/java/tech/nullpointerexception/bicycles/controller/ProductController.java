package tech.nullpointerexception.bicycles.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.nullpointerexception.bicycles.services.ProductService;
import tech.nullpointerexception.bicycles.util.UtilConstants;
import tech.nullpointerexception.bicycles.exception.NotFoundException;
import tech.nullpointerexception.bicycles.exception.ProductException;
import tech.nullpointerexception.bicycles.exception.ProviderException;
import tech.nullpointerexception.bicycles.dto.ProductCreationDto;
import tech.nullpointerexception.bicycles.dto.ProductDto;

import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    /**
     * Lista todos los productos existentes con su información respectiva.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getAllProducts() {
        return Optional.ofNullable(productService.getAllProducts())
                .orElseThrow(NotFoundException::new);

    }

    /**
     * Obtiene información de un producto a partir de su ID
     * @param productId: UUID del producto a consultar
     * @return ProductDTO con los datos del producto consultado.
     */
    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getProduct(@PathVariable("productId") UUID productId) {
        return Optional.ofNullable(productService.getProductInfo(productId))
                .orElseThrow(NotFoundException::new);
    }

    /**
     * Crea un nuevo producto en la base de datos y sus proveedores
     * @param productCreation
     * @return
     */
    @PostMapping
    public ResponseEntity<ProductDto> handleProductCreation(@Valid @RequestBody ProductCreationDto productCreation) {
        ProductDto savedProduct = Optional.ofNullable(productService.createProduct(productCreation.getProductDto(), productCreation.getProviders()))
                .orElseThrow(() -> new ProductException(UtilConstants.ERROR_WHILE_CREATING_PRODUCT));
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Location", InetAddress.getLocalHost().getHostAddress() + "/api/v1/product/" + savedProduct.getId().toString());
        } catch (UnknownHostException e) {
            headers.add("Location", "/api/v1/product/" + savedProduct.getId().toString());
        }
        return new ResponseEntity<>(savedProduct, headers, HttpStatus.CREATED);
    }

    /**
     * Actualiza la información de un producto ya existente.
     * @param productId: Identificador del producto
     * @param productDto: Datos del producto modificados.
     * @return El objeto modificado.
     */
    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto handleUpdateProduct(@PathVariable("productId") UUID productId,
                                          @Valid
                                          @RequestBody ProductDto productDto) {
        return Optional.ofNullable(productService.updateProduct(productId, productDto))
                .orElseThrow(() -> new ProductException(UtilConstants.ERROR_WHILE_UPDATING_PRODUCT));
    }

    /**
     * Elimina un producto a partid de su ID
     * @param productId: Identificador del producto a eliminar.
     */
    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDeleteProduct(@PathVariable("productId") UUID productId) {
        boolean response = Optional.ofNullable(productService.deleteProduct(productId))
                .orElse(Boolean.FALSE);
        if (!response) {
            throw new ProviderException(UtilConstants.ERROR_WHILE_DELETING_PROVIDER);
        }
    }
}
