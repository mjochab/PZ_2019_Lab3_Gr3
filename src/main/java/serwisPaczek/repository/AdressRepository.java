package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.Adress;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long> {
}
