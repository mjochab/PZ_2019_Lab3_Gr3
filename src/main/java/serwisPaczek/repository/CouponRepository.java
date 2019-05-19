package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
