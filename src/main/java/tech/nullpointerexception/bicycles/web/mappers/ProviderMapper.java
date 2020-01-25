package tech.nullpointerexception.bicycles.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.nullpointerexception.bicycles.model.Provider;
import tech.nullpointerexception.bicycles.web.model.ProviderDto;

@Mapper
public interface ProviderMapper {
    ProviderMapper INSTANCE = Mappers.getMapper(ProviderMapper.class);

    ProviderDto providerToProviderDto(Provider provider);

    Provider providerDtoToProvider(ProviderDto providerDto);
}
