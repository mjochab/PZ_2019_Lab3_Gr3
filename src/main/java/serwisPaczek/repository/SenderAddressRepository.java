package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.SenderAddress;

@Repository
public interface SenderAddressRepository extends JpaRepository<SenderAddress, Long> {
}
