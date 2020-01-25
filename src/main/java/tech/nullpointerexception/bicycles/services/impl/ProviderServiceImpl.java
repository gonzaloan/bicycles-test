package tech.nullpointerexception.bicycles.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.nullpointerexception.bicycles.services.ProviderService;
import tech.nullpointerexception.bicycles.web.model.ProviderDto;

@Service
@Slf4j
public class ProviderServiceImpl implements ProviderService {

    @Override
    public ProviderDto getProvider(String providerDni) {
        return null;
    }

    @Override
    public ProviderDto createProvider(ProviderDto newProvider) {
        return null;
    }

    @Override
    public ProviderDto updateProvider(String providerDni, ProviderDto providerDto) {
        return null;
    }

    @Override
    public Boolean deleteByProviderDni(String providerDni) {
        log.debug("Deleting a Provider: {}", providerDni);
        return true;
    }
}
