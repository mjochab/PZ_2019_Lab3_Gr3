package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.Courier;
import serwisPaczek.model.Opinion;

import java.util.List;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    Opinion findByUserOrder_Courier(Courier courier);

    List<Opinion> findAllByUserOrder_Courier(Courier courier);
}
