package tech.nullpointerexception.bicycles.util;

public class UtilConstants {

    /* Errores API */
    public static final String ERROR_MESSAGE = "Ha ocurrido un error.";
    public static final String ERROR_GETTING_DATA = "No se pudo obtener la información solicitada.";
    public static final String NOT_FOUND_DATA = "No se encontró la información solicitada.";
    public static final String ERROR_WHILE_CREATING_PROVIDER = "Ha ocurrido un error al crear al proveedor";
    public static final String ERROR_WHILE_CREATING_PRODUCT = "Ha ocurrido un error al crear el producto";
    public static final String ERROR_WHILE_UPDATING_PROVIDER = "Ha ocurrido un error al actualizar al proveedor";

    public static final String ERROR_WHILE_UPDATING_PRODUCT = "Ha ocurrido un error al actualizar el producto";
    public static final String ERROR_WHILE_DELETING_PROVIDER = "Ha ocurrido un error al eliminar al proveedor";
    public static final String ERROR_WHILE_DELETING_PRODUCT = "Ha ocurrido un error al eliminar el producto";

    public static final String CANT_REPEAT_PROVIDER_ID = "El id del proveedor ya existe.";
    public static final String CANT_REPEAT_PRODUCT_ID = "El id del producto ya existe.";
    /* Validation Errors */
    public static final String ERROR_VALIDATION_PROVIDER_DNI = "El campo providerDni no puede estar vacío o es inválido";
    public static final String ERROR_VALIDATION_PROVIDER_NAME = "El campo providerName no puede estar vacío o es inválido";
    public static final String ERROR_VALIDATION_PRODUCT_NAME = "El nombre de producto no puede estar vacío o es inválido";
    public static final String ERROR_VALIDATION_PRODUCT_QUANTITY = "La cantidad de producto no puede estar vacía o es inválida";
}
