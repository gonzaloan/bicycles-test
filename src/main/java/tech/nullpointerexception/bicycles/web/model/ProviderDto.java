package tech.nullpointerexception.bicycles.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tech.nullpointerexception.bicycles.util.UtilConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderDto implements Serializable {

    private static final long serialVersionUID = 3401363725461046180L;

    @Null
    @JsonIgnore
    private Long id;

    @NotBlank(message = UtilConstants.ERROR_VALIDATION_PROVIDER_DNI)
    private String providerDni;

    @NotBlank(message = UtilConstants.ERROR_VALIDATION_PROVIDER_NAME)
    private String providerName;

}
