package tech.nullpointerexception.bicycles.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(max = 20)
    @NotNull
    private String providerDni;

    @NotNull
    @Size(max = 250)
    private String providerName;

    @ManyToMany(mappedBy = "providers")
    private Set<Product> products = new HashSet<>();

    public Provider(String providerDni, String providerName) {
        this.providerName = providerName;
        this.providerDni = providerDni;
    }
}
