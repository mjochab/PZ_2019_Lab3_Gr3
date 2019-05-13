package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.Courier;
import serwisPaczek.model.Opinion;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    Opinion findByUserOrder_Courier(Courier courier);
}
