package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.Courier;
import serwisPaczek.model.PackPricing;

@Repository
public interface PackPricingRepository extends JpaRepository<PackPricing, Long> {
    PackPricing findByCourier(Courier courier);
}
