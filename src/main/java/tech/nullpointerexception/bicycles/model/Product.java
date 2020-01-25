package tech.nullpointerexception.bicycles.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Size(max = 250)
    private String productName;

    @NotNull
    private Integer productQuantity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_provider",
        joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "provider_id")
    )
    private Set<Provider> providers;

    public Product(String productName, Integer productQuantity, Provider...providers){
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.providers = Stream.of(providers).collect(Collectors.toSet());
        this.providers.forEach(x -> x.getProducts().add(this));
    }
}
