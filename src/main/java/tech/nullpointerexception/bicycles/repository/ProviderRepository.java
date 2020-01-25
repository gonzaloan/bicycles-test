package tech.nullpointerexception.bicycles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.nullpointerexception.bicycles.model.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {


    Provider findById(String id);

    Provider findByProviderDni(String providerDni);

    void deleteByProviderDni(String providerDni);

}
