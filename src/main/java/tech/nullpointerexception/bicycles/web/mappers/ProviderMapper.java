package tech.nullpointerexception.bicycles.web.mappers;

import org.mapstruct.Mapper;
import tech.nullpointerexception.bicycles.model.Provider;
import tech.nullpointerexception.bicycles.web.model.ProviderDto;

@Mapper
public interface ProviderMapper {

    ProviderDto providerToProviderDto(Provider provider);
    Provider providerDtoToProvider(ProviderDto providerDto);
}
