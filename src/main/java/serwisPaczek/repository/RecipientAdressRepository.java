package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.RecipientAdress;

@Repository
public interface RecipientAdressRepository extends JpaRepository<RecipientAdress, Long> {
}
//TODO[PATRYK]: Rename class RecipientAdressRepository -> RecipientAddressRepository