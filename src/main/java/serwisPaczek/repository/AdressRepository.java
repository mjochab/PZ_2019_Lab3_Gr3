package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.Adress;
import serwisPaczek.model.User;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long> {
    Adress findByUser(User username);
}
//TODO: Rename class AdressRepository -> AddressRepository