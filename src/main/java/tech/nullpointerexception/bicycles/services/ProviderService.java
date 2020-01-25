package tech.nullpointerexception.bicycles.services;

import tech.nullpointerexception.bicycles.web.model.ProviderDto;

public interface ProviderService {
    ProviderDto getProvider(String providerDni);

    ProviderDto createProvider(ProviderDto newProvider);

    ProviderDto updateProvider(String providerDni, ProviderDto providerDto);

    Boolean deleteByProviderDni(String providerDni);

}
