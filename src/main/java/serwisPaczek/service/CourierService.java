package serwisPaczek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serwisPaczek.model.Courier;
import serwisPaczek.model.EnvelopePricing;
import serwisPaczek.model.PackPricing;
import serwisPaczek.model.PalletPricing;
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

    public void createCourierCompany(String name, float envelope_up_to_1, float pack_up_to_1, float pack_up_to_2,
                                     float pack_up_to_5, float pack_up_to_10, float pack_up_to_15, float pack_up_to_20,
                                     float pack_up_to_30, float pallet_up_to_300, float pallet_up_to_500,
                                     float pallet_up_to_800, float pallet_up_to_1000) {
        Courier courier = new Courier(name);
        EnvelopePricing envelopePricing = new EnvelopePricing(envelope_up_to_1, courier);
        PackPricing packPricing = new PackPricing(pack_up_to_1, pack_up_to_2, pack_up_to_5, pack_up_to_10,
                pack_up_to_15, pack_up_to_20, pack_up_to_30, courier);
        PalletPricing palletPricing = new PalletPricing(pallet_up_to_300, pallet_up_to_500, pallet_up_to_800,
                pallet_up_to_1000, courier);
        courierRepository.save(courier);
        envelopePricingRepository.save(envelopePricing);
        packPricingRepository.save(packPricing);
        palletPricingRepository.save(palletPricing);
    }

    public void editCourierCompany(Courier courier, String name, float envelope_up_to_1, float pack_up_to_1, float pack_up_to_2,
                                     float pack_up_to_5, float pack_up_to_10, float pack_up_to_15, float pack_up_to_20,
                                     float pack_up_to_30, float pallet_up_to_300, float pallet_up_to_500,
                                     float pallet_up_to_800, float pallet_up_to_1000) {
        courier.setName(name);
        courier.getEnvelopePricing().setUp_to_1(envelope_up_to_1);
        courier.getPackPricing().setUp_to_1(pack_up_to_1);
        courier.getPackPricing().setUp_to_2(pack_up_to_2);
        courier.getPackPricing().setUp_to_5(pack_up_to_5);
        courier.getPackPricing().setUp_to_10(pack_up_to_10);
        courier.getPackPricing().setUp_to_15(pack_up_to_15);
        courier.getPackPricing().setUp_to_20(pack_up_to_20);
        courier.getPackPricing().setUp_to_30(pack_up_to_30);
        courier.getPalletPricing().setUp_to_300(pallet_up_to_300);
        courier.getPalletPricing().setUp_to_500(pallet_up_to_500);
        courier.getPalletPricing().setUp_to_800(pallet_up_to_800);
        courier.getPalletPricing().setUp_to_1000(pallet_up_to_1000);
        courierRepository.save(courier);
    }
}
