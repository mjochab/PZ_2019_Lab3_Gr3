package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.Gift;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {
}
