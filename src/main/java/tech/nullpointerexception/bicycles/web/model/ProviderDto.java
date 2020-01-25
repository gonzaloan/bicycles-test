package tech.nullpointerexception.bicycles.web.model;

import lombok.*;
import tech.nullpointerexception.bicycles.util.UtilConstants;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderDto implements Serializable {

    private static final long serialVersionUID = 3401363725461046180L;

    private Long id;

    @NotBlank(message = UtilConstants.ERROR_VALIDATION_PROVIDER_DNI)
    private String providerDni;

    @NotBlank(message = UtilConstants.ERROR_VALIDATION_PROVIDER_NAME)
    private String providerName;

}
