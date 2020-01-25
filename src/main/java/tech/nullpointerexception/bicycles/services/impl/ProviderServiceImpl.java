package tech.nullpointerexception.bicycles.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.nullpointerexception.bicycles.repository.ProviderRepository;
import tech.nullpointerexception.bicycles.services.ProviderService;
import tech.nullpointerexception.bicycles.web.model.ProviderDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;

    @Override
    public ProviderDto getProvider(String providerDni) {
        log.debug("Dentro de GetProvider");
        return Optional.ofNullable(providerRepository.findByProviderDni())
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
