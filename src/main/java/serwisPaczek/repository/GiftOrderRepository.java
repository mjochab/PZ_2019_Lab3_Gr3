package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.GiftOrder;

@Repository
public interface GiftOrderRepository extends JpaRepository<GiftOrder, Long> {
}
