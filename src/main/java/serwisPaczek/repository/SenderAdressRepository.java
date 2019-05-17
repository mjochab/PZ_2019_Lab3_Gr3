package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.SenderAdress;

@Repository
public interface SenderAdressRepository extends JpaRepository<SenderAdress, Long> {
}
//TODO: Rename class SenderAdressRepository -> SenderAddressRepository