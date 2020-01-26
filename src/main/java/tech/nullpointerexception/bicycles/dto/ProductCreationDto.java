package tech.nullpointerexception.bicycles.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreationDto implements Serializable {

    private static final long serialVersionUID = 1684001240482497196L;

    private ProductDto productDto;
    private List<ProviderDto> providers;
}
