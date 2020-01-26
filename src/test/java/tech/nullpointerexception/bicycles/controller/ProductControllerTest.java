package tech.nullpointerexception.bicycles.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tech.nullpointerexception.bicycles.dto.ProductCreationDto;
import tech.nullpointerexception.bicycles.dto.ProductDto;
import tech.nullpointerexception.bicycles.services.ProductService;
import tech.nullpointerexception.bicycles.util.TestConstants;
import tech.nullpointerexception.bicycles.util.UtilConstants;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * TDD para Provider
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ProductController.class)
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;


    @Test
    void getProduct_when_is_ok() throws Exception {
        Mockito.when(productService.getProductInfo(any())).thenReturn(TestConstants.GET_PRODUCT());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/{id}", UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.productName", is(TestConstants.GET_PRODUCT().getProductName())))
                .andExpect(jsonPath("$.productQuantity", is(TestConstants.GET_PRODUCT().getProductQuantity())))
                .andDo(print());

    }

    @Test
    void getProduct_when_returns_null() throws Exception {
        Mockito.when(productService.getProductInfo(any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/{id}", UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.code", is(404)))
                .andExpect(jsonPath("$.message", is(UtilConstants.ERROR_MESSAGE)))
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andDo(print());

    }

    @Test
    void postProduct_when_is_ok() throws Exception {
        Mockito.when(productService.createProduct(any(), anyList())).thenReturn(TestConstants.GET_PRODUCT());

        System.out.println("============> " + objectMapper.writeValueAsString(TestConstants.GET_PRODUCT_CREATION()));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product/")
                .content(objectMapper.writeValueAsString(TestConstants.GET_PRODUCT_CREATION()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.productName", is(TestConstants.GET_PRODUCT().getProductName())))
                .andExpect(jsonPath("$.productQuantity", is(TestConstants.GET_PRODUCT().getProductQuantity())))
                .andDo(print());

    }


    @Test
    void postProduct_when_is_service_fails() throws Exception {
        Mockito.when(productService.createProduct(any(), anyList())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product/")
                .content(objectMapper.writeValueAsString(TestConstants.GET_PRODUCT_CREATION()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.message", is(UtilConstants.ERROR_MESSAGE)))
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andDo(print());

    }

    @Test
    void putProduct_when_is_ok() throws Exception {
        ProductDto productDto = TestConstants.GET_PRODUCT();
        productDto.setProductName("Bicicletas Monicaco I");
        Mockito.when(productService.updateProduct(any(), any())).thenReturn(productDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/product/{id}", productDto.getId())
                .content(objectMapper.writeValueAsString(productDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void putProduct_when_is_service_fails() throws Exception {
        Mockito.when(productService.updateProduct(any(), any())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/product/{id}", UUID.randomUUID())
                .content(objectMapper.writeValueAsString(TestConstants.GET_PRODUCT()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.message", is(UtilConstants.ERROR_MESSAGE)))
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andDo(print());

    }

    @Test
    void deleteProduct_when_is_ok() throws Exception {

        Mockito.when(productService.deleteProduct(any())).thenReturn(Boolean.TRUE);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/product/{id}", UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());

    }

    @Test
    void deleteProduct_when_is_nok() throws Exception {

        Mockito.when(productService.deleteProduct(any())).thenReturn(Boolean.FALSE);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/product/{id}", UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.message", is(UtilConstants.ERROR_MESSAGE)))
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andDo(print());

    }


}