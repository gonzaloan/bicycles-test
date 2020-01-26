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
import tech.nullpointerexception.bicycles.services.ProviderService;
import tech.nullpointerexception.bicycles.util.TestConstants;
import tech.nullpointerexception.bicycles.util.UtilConstants;
import tech.nullpointerexception.bicycles.dto.ProviderDto;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ProviderController.class)
class ProviderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ProviderService providerService;


    @Test
    void getProvider_when_is_ok() throws Exception {
        Mockito.when(providerService.getProvider(anyString())).thenReturn(TestConstants.GET_PROVIDER());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/provider/{id}", "66667777")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.providerDni", is(TestConstants.GET_PROVIDER().getProviderDni())))
                .andExpect(jsonPath("$.providerName", is(TestConstants.GET_PROVIDER().getProviderName())))
                .andDo(print());

    }

    @Test
    void getProvider_when_returns_null() throws Exception {
        Mockito.when(providerService.getProvider(anyString())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/provider/{id}", "66667777")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.code", is(404)))
                .andExpect(jsonPath("$.message", is(UtilConstants.ERROR_MESSAGE)))
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andDo(print());

    }

    @Test
    void postProvider_when_is_ok() throws Exception {
        Mockito.when(providerService.createProvider(any())).thenReturn(TestConstants.GET_PROVIDER());


        ProviderDto providerDto = TestConstants.GET_PROVIDER();
        providerDto.setId(null); //id lo setea la bd
        System.out.println("============> " + objectMapper.writeValueAsString(providerDto));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/provider/")
                .content(objectMapper.writeValueAsString(providerDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.providerDni", is(TestConstants.GET_PROVIDER().getProviderDni())))
                .andExpect(jsonPath("$.providerName", is(TestConstants.GET_PROVIDER().getProviderName())))
                .andDo(print());

    }


    @Test
    void postProvider_when_is_service_fails() throws Exception {
        Mockito.when(providerService.createProvider(any())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/provider/")
                .content(objectMapper.writeValueAsString(TestConstants.GET_PROVIDER()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.message", is(UtilConstants.ERROR_MESSAGE)))
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andDo(print());

    }

    @Test
    void postProvider_when_field_is_missing() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/provider/")
                .content(objectMapper.writeValueAsString(ProviderDto.builder().providerDni("123").build()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());

    }

    @Test
    void putProvider_when_is_ok() throws Exception {
        ProviderDto providerDto = TestConstants.GET_PROVIDER();
        providerDto.setProviderName("Bicicletas Mena 2");
        Mockito.when(providerService.updateProvider(anyString(), any())).thenReturn(providerDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/provider/{id}", "66667777")
                .content(objectMapper.writeValueAsString(providerDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.providerDni", is(TestConstants.GET_PROVIDER().getProviderDni())))
                .andExpect(jsonPath("$.providerName", is(providerDto.getProviderName())))
                .andDo(print());

    }

    @Test
    void putProvider_when_is_service_fails() throws Exception {
        Mockito.when(providerService.updateProvider(anyString(), any())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/provider/{id}", "66667777")
                .content(objectMapper.writeValueAsString(TestConstants.GET_PROVIDER()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.message", is(UtilConstants.ERROR_MESSAGE)))
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andDo(print());

    }

    @Test
    void deleteProvider_when_is_ok() throws Exception {

        Mockito.when(providerService.deleteByProviderDni(anyString())).thenReturn(Boolean.TRUE);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/provider/{id}", "66667777")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());

    }

    @Test
    void deleteProvider_when_is_nok() throws Exception {

        Mockito.when(providerService.deleteByProviderDni(anyString())).thenReturn(Boolean.FALSE);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/provider/{id}", "66667777")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.message", is(UtilConstants.ERROR_MESSAGE)))
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andDo(print());

    }


}