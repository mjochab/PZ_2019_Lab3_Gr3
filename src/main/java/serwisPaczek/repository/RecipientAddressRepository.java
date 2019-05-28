package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.RecipientAddress;

@Repository
public interface RecipientAddressRepository extends JpaRepository<RecipientAddress, Long> {
}
