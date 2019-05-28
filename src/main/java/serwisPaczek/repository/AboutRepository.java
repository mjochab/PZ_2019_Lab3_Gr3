package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.About;

@Repository
public interface AboutRepository extends JpaRepository<About, Long> {
}
