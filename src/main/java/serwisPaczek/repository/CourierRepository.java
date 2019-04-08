package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.Courier;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
}
