package tech.nullpointerexception.bicycles.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tech.nullpointerexception.bicycles.util.UtilConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto implements Serializable {

    private static final long serialVersionUID = -4843231305284808961L;


    private UUID id;
    @NotBlank(message = UtilConstants.ERROR_VALIDATION_PRODUCT_NAME)
    private String productName;

    @PositiveOrZero(message = UtilConstants.ERROR_VALIDATION_PRODUCT_QUANTITY)
    private Integer productQuantity;
}
