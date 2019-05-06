package serwisPaczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serwisPaczek.model.Courier;
import serwisPaczek.model.PalletPricing;

@Repository
public interface PalletPricingRepository extends JpaRepository<PalletPricing, Long> {
    PalletPricing findByCourier(Courier courier);
}
