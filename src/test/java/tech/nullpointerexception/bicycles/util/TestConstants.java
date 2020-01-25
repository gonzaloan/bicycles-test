package tech.nullpointerexception.bicycles.util;

import tech.nullpointerexception.bicycles.web.model.ProviderDto;

public class TestConstants {

    public static ProviderDto GET_PROVIDER() {
        return ProviderDto.builder()
                .id(1L)
                .providerDni("98221719")
                .providerName("Bicicletas Mena")
                .build();
    }
}
