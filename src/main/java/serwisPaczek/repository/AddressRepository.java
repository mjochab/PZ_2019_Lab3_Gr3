package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.Address;
import serwisPaczek.model.User;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByUser(User username);
}
