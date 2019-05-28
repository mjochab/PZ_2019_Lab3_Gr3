package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.Courier;
import serwisPaczek.model.Opinion;
import serwisPaczek.model.User;

import java.util.List;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    Opinion findByUserOrder_Courier(Courier courier);

    Opinion findByUserOrder_Id(Long id);

    List<Opinion> findAllByUserOrder_User(User user);

    List<Opinion> findAllByUserOrder_Courier(Courier courier);
}
