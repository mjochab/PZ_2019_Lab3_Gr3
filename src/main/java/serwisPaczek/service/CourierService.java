package serwisPaczek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serwisPaczek.model.Courier;
import serwisPaczek.model.EnvelopePricing;
import serwisPaczek.model.PackPricing;
import serwisPaczek.model.PalletPricing;
import serwisPaczek.model.dto.CourierPricingDto;
import serwisPaczek.repository.CourierRepository;
import serwisPaczek.repository.EnvelopePricingRepository;
import serwisPaczek.repository.PackPricingRepository;
import serwisPaczek.repository.PalletPricingRepository;

/**
 * This class is responsible for managing the courier in the database.
 */
@Service
public class CourierService {
    @Autowired
    CourierRepository courierRepository;
    @Autowired
    EnvelopePricingRepository envelopePricingRepository;
    @Autowired
    PackPricingRepository packPricingRepository;
    @Autowired
    PalletPricingRepository palletPricingRepository;

    /**
     * This method is used to create a new courier company and and it's pricing.
     *
     * @param courierPricing The object that stores necessary information needed to create a courier.
     */
    public void createCourierCompany(CourierPricingDto courierPricing) {
        Courier courier = new Courier(
                courierPricing.getCourier_name());

        EnvelopePricing envelopePricing = new EnvelopePricing(
                courierPricing.getEnvelope_up_to_1(), courier);

        PackPricing packPricing = getPackPricing(courierPricing, courier);

        PalletPricing palletPricing = getPalletPricing(courierPricing, courier);

        courierRepository.save(courier);
        envelopePricingRepository.save(envelopePricing);
        packPricingRepository.save(packPricing);
        palletPricingRepository.save(palletPricing);
    }

    /**
     * This method is used to edit an existing courier company and it's pricing.
     *
     * @param courierPricing The object that stores necessary information needed to edit an existing courier.
     */
    public void editCourierCompany(CourierPricingDto courierPricing) {
        Courier courier = getCourierByCourierPricingDto(courierPricing, new Courier());
        courierRepository.save(courier);
    }

    /** method to count pack price
     * @param courierPricing - courierPricing The object that stores necessary information needed to edit an existing
     *                       courier.
     * @param courier - selected courier
     * @return
     */
     public PackPricing getPackPricing(CourierPricingDto courierPricing, Courier courier){
        return new PackPricing(
                courierPricing.getPack_up_to_1(), courierPricing.getPack_up_to_2(), courierPricing.getPack_up_to_5(),
                courierPricing.getPack_up_to_10(), courierPricing.getPack_up_to_15(), courierPricing.getPack_up_to_20(),
                courierPricing.getPack_up_to_30(), courier);
    }
    /** method to count pack pallet
     * @param courierPricing - courierPricing The object that stores necessary information needed to edit an existing
     *                       courier.
     * @param courier - selected courier
     * @return
     */
     public PalletPricing getPalletPricing(CourierPricingDto courierPricing, Courier courier){
        return new PalletPricing(
                courierPricing.getPallet_up_to_300(), courierPricing.getPallet_up_to_500(),
                courierPricing.getPallet_up_to_800(), courierPricing.getPallet_up_to_1000(), courier);
    }

    /** method to set Courier datas from courierPricingDto
     * @param courierPricing - courierPricing The object that stores necessary information needed to edit an existing
     *                       courier.
     * @param courier
     * @return courier with new datas
     */
     public Courier getCourierByCourierPricingDto(CourierPricingDto courierPricing, Courier courier){

        courierPricing.getCourier();
        courier.setName(courierPricing.getCourier_name());
        courier.set_blocked(courierPricing.isBlocked());
        courier.getEnvelopePricing().setUp_to_1(courierPricing.getEnvelope_up_to_1());
        courier.getPackPricing().setUp_to_1(courierPricing.getPack_up_to_1());
        courier.getPackPricing().setUp_to_2(courierPricing.getPack_up_to_2());
        courier.getPackPricing().setUp_to_5(courierPricing.getPack_up_to_5());
        courier.getPackPricing().setUp_to_10(courierPricing.getPack_up_to_10());
        courier.getPackPricing().setUp_to_15(courierPricing.getPack_up_to_15());
        courier.getPackPricing().setUp_to_20(courierPricing.getPack_up_to_20());
        courier.getPackPricing().setUp_to_30(courierPricing.getPack_up_to_30());
        courier.getPalletPricing().setUp_to_300(courierPricing.getPallet_up_to_300());
        courier.getPalletPricing().setUp_to_500(courierPricing.getPallet_up_to_500());
        courier.getPalletPricing().setUp_to_800(courierPricing.getPallet_up_to_800());
        courier.getPalletPricing().setUp_to_1000(courierPricing.getPallet_up_to_1000());

        return courier;
    }
}
