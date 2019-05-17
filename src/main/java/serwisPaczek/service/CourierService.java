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

    public void createCourierCompany(CourierPricingDto courierPricing) {
        Courier courier = new Courier(
                courierPricing.getCourier_name());
        EnvelopePricing envelopePricing = new EnvelopePricing(
                courierPricing.getEnvelope_up_to_1(), courier);
        PackPricing packPricing = new PackPricing(
                courierPricing.getPack_up_to_1(), courierPricing.getPack_up_to_2(), courierPricing.getPack_up_to_5(),
                courierPricing.getPack_up_to_10(), courierPricing.getPack_up_to_15(), courierPricing.getPack_up_to_20(),
                courierPricing.getPack_up_to_30(), courier);
        PalletPricing palletPricing = new PalletPricing(
                courierPricing.getPallet_up_to_300(), courierPricing.getPallet_up_to_500(),
                courierPricing.getPallet_up_to_800(), courierPricing.getPallet_up_to_1000(), courier);
        courierRepository.save(courier);
        envelopePricingRepository.save(envelopePricing);
        packPricingRepository.save(packPricing);
        palletPricingRepository.save(palletPricing);
    }

    public void editCourierCompany(CourierPricingDto courierPricing) {
        Courier courier = courierPricing.getCourier();
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
        courierRepository.save(courier);
    }
}
