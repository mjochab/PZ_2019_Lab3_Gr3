package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.Courier;
import serwisPaczek.model.EnvelopePricing;

@Repository
public interface EnvelopePricingRepository extends JpaRepository<EnvelopePricing, Long> {
    EnvelopePricing findByCourier(Courier courier);
}
