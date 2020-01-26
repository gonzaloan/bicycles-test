# Bicycles

El proyecto está realizado con Spring Boot y Java 8. 

Para poder
importa el proyecto, para IntelliJ, sólo se requiere crear un nuevo proyecto
a partir de un controlador de versiones e indicar la url del proyecto GIT: https://github.com/gonzaloan/bicycles-test.git


También es posible clonarlo manualmente, e importarlo como un proyecto Maven
en nuestro Eclipse.



## Dependencias

Este proyecto entre otras librerías utiliza Lombok y MapStruct por lo que
es necesario habilitar el procesamiento de anotaciones en el IDE, y plugin:

- **Eclipse**: Instalar plugin para permitir habilitar el procesamiento de anotaciones Plugin m2e y el plugin de MapStruct
- **IntelliJ**: Sólo habilitar el procesamiento de anotaciones: Settings/Build, Execution, Deployment/Compiler/Annotation Processors


## Información Técnica del proyecto

Cosas a saber:
- El proyecto utiliza la librería MapStruct para el Mapeo de Entidades en DTO.
- Por otro lado utiliza Lombok para eliminación de código boilerplate como getters, setters, constructores.
- Se implementó un Handler de exceptions, para capturar excepciones personalizadas del proyecto. 
- Se utiliza validaciones de Constraints de la API de javax.validation. Esto se puede apreciar en los DTO de ProviderDto y ProductDTO.
- Los test unitarios se hacen con JUNIT 5, Mockito y MockMvc.
- Existe un Bootstrap que inicializa algunos datos de prueba al levantar la aplicación. Esto se encuentra en el package **bootstrap**/ProductLoader. Básicamente son datos de prueba para ser usados.

