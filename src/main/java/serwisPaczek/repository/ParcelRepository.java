package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.Parcel;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {
}
