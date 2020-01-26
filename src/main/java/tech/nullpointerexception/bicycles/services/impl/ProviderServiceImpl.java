package tech.nullpointerexception.bicycles.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.nullpointerexception.bicycles.dto.ProviderDto;
import tech.nullpointerexception.bicycles.exception.NotFoundException;
import tech.nullpointerexception.bicycles.exception.ProviderException;
import tech.nullpointerexception.bicycles.mappers.ProviderMapper;
import tech.nullpointerexception.bicycles.model.Provider;
import tech.nullpointerexception.bicycles.repository.ProviderRepository;
import tech.nullpointerexception.bicycles.services.ProviderService;
import tech.nullpointerexception.bicycles.util.UtilConstants;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;

    @Override
    public ProviderDto getProvider(String providerDni) {
        log.debug("Dentro de GetProvider");
        return Optional.ofNullable(providerRepository.findByProviderDni(providerDni))
                .map(ProviderMapper.INSTANCE::providerToProviderDto)
                .orElse(null);
    }

    @Override
    public ProviderDto createProvider(ProviderDto newProvider) {
        log.debug("Creando un proveedor {}", newProvider.toString());
        if (providerRepository.findByProviderDni(newProvider.getProviderDni()) != null)
            throw new ProviderException(UtilConstants.CANT_REPEAT_PROVIDER_ID);
        return Optional.of(newProvider)
                .map(ProviderMapper.INSTANCE::providerDtoToProvider)
                .map(providerRepository::save)
                .map(ProviderMapper.INSTANCE::providerToProviderDto)
                .orElse(null);
    }

    @Override
    public ProviderDto updateProvider(String providerDni, ProviderDto providerDto) {
        log.debug("Actualizando un Proveedor {}", providerDni);
        Provider provider = Optional.ofNullable(providerRepository.findByProviderDni(providerDni)).orElseThrow(NotFoundException::new);
        provider.setProviderDni(providerDto.getProviderDni());
        provider.setProviderName(providerDto.getProviderName());
        return Optional.of(provider)
                .map(providerRepository::save)
                .map(ProviderMapper.INSTANCE::providerToProviderDto)
                .orElse(null);
    }

    @Override
    public Boolean deleteByProviderDni(String providerDni) {
        log.debug("Deleting a Provider: {}", providerDni);
        try {
            providerRepository.deleteByProviderDni(providerDni);
        } catch (Exception e) {
            log.error("Ocurrio un error al borrar el proveedor {}", e.getMessage());
            return false;
        }
        return true;
    }
}
