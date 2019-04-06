package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.UserOrder;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long> {
}
