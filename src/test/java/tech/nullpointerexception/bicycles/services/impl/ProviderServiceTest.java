package tech.nullpointerexception.bicycles.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tech.nullpointerexception.bicycles.repository.ProviderRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProviderServiceTest {


    @MockBean
    private ProviderRepository providerRepository;

    @Test
    void getProvider() {
    }
}