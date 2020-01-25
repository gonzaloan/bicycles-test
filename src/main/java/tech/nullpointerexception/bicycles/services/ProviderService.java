package tech.nullpointerexception.bicycles.services;

import tech.nullpointerexception.bicycles.web.model.ProviderDto;

public interface ProviderService {
    /**
     * Obtiene la información de un proveedor a partir de su DNI
     * @param providerDni: DNI del proveedor
     * @return ProviderDTO: Objeto poblado con información del proveedor
     */
    ProviderDto getProvider(String providerDni);

    /**
     * Creará un nuevo proveedor en el sistema.
     * @param newProvider: Datos del nuevo proveedor.
     * @return
     */
    ProviderDto createProvider(ProviderDto newProvider);

    /**
     * Actualizará un proveedor dado su DNI.
     * @param providerDni: DNI del proveedor a modificar
     * @param providerDto: Datos nuevos del proveedor.
     * @return objeto ProviderDTO poblado.
     */
    ProviderDto updateProvider(String providerDni, ProviderDto providerDto);

    /**
     * Elimina un proveedor de la base de datos a partir de su dni
     * @param providerDni: Dni del usuario a eliminar
     * @return Boolean con respuesta TRUE si se eliminó sin problemas o FALSE si ocurrió un error.
     */
    Boolean deleteByProviderDni(String providerDni);

}
