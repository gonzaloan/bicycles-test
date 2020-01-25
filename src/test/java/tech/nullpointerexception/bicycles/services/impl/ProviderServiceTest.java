package tech.nullpointerexception.bicycles.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tech.nullpointerexception.bicycles.repository.ProviderRepository;
import tech.nullpointerexception.bicycles.services.ProviderService;
import tech.nullpointerexception.bicycles.util.TestConstants;
import tech.nullpointerexception.bicycles.web.mappers.ProviderMapper;
import tech.nullpointerexception.bicycles.web.model.ProviderDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProviderServiceTest {

    @Mock
    private ProviderRepository providerRepository;

    @InjectMocks
    private ProviderServiceImpl providerService;
    @Test
    void getProvider() {

        Mockito.when(providerRepository.findByProviderDni(anyString()))
                .thenReturn(ProviderMapper.INSTANCE.providerDtoToProvider(TestConstants.GET_PROVIDER()));
        ProviderDto provider = providerService.getProvider("98221719");

        assertThat(provider).isNotNull();
        assertThat(provider.getProviderDni()).isEqualTo(TestConstants.GET_PROVIDER().getProviderDni());
        assertThat(provider.getProviderName()).isEqualTo(TestConstants.GET_PROVIDER().getProviderName());

    }

    @Test
    void createProvider() {

        Mockito.when(providerRepository.save(any()))
                .thenReturn(ProviderMapper.INSTANCE.providerDtoToProvider(TestConstants.GET_PROVIDER()));

        ProviderDto provider = providerService.createProvider(TestConstants.GET_PROVIDER());

        assertThat(provider).isNotNull();
        assertThat(provider.getProviderDni()).isEqualTo(TestConstants.GET_PROVIDER().getProviderDni());
        assertThat(provider.getProviderName()).isEqualTo(TestConstants.GET_PROVIDER().getProviderName());


    }

    @Test
    void updateProvider() {
        Mockito.when(providerRepository.findByProviderDni(anyString()))
                .thenReturn(ProviderMapper.INSTANCE.providerDtoToProvider(TestConstants.GET_PROVIDER()));

        Mockito.when(providerRepository.save(any()))
                .thenReturn(ProviderMapper.INSTANCE.providerDtoToProvider(TestConstants.GET_PROVIDER()));

        ProviderDto provider = providerService.updateProvider("98111212", TestConstants.GET_PROVIDER());
        assertThat(provider).isNotNull();
        assertThat(provider.getProviderDni()).isEqualTo(TestConstants.GET_PROVIDER().getProviderDni());
        assertThat(provider.getProviderName()).isEqualTo(TestConstants.GET_PROVIDER().getProviderName());


    }

    @Test
    void deleteByProviderDni() {
        String dniTesting = TestConstants.GET_PROVIDER().getProviderDni();
        providerService.deleteByProviderDni(dniTesting);
        verify(providerRepository, times(1)).deleteByProviderDni(eq(dniTesting));
    }
}