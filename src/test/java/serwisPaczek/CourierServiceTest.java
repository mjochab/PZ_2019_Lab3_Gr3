package serwisPaczek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import serwisPaczek.model.*;
import serwisPaczek.model.dto.CourierPricingDto;
import serwisPaczek.repository.PackPricingRepository;
import serwisPaczek.service.CourierService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CourierServiceTest {

    @InjectMocks
    private CourierService courierService;

    @Mock
    PackPricingRepository packPricingRepository;

    Courier courier;
    CourierPricingDto courierPricingDto;
    PalletPricing palletPricing;
    PackPricing packPricing;
    EnvelopePricing envelopePricing;
    @BeforeEach
    void setUp() {

      courierPricingDto = new CourierPricingDto(1,
                1,1,1,1,1,
                1,1,1,1,1,
                1, courier,true,"kurierek");
      palletPricing = new PalletPricing(1,1,1,1,courier);
      packPricing = new PackPricing(1,1,1,
                1,1,1,1,courier);
      envelopePricing = new EnvelopePricing(1, courier);
      courier = new Courier("kurierek",false,envelopePricing,palletPricing,packPricing);
      palletPricing.setCourier(courier);
      packPricing.setCourier(courier);
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void getPackPricing_Test(){
      assertEquals(courierService.getPackPricing(courierPricingDto, courier),
              packPricing);
    }

    @Test
    void getPalletPricing_Test(){
        assertEquals(courierService.getPalletPricing(courierPricingDto,courier),
                palletPricing);
    }

    @Test
    void getPackPricingShouldReturnFalse_Test(){
        packPricing.setUp_to_1(2);
        assertNotEquals(courierService.getPackPricing(courierPricingDto, courier),
                packPricing);
    }

    @Test
    void getPalletPricingShouldReturnFalse_Test(){
        palletPricing.setUp_to_300(2);
        assertNotEquals(courierService.getPalletPricing(courierPricingDto,courier),
                palletPricing);
    }

    @Test
    void getCourierByCourierPricingDto_Test() {
        assertEquals(courierService.getCourierByCourierPricingDto(courierPricingDto, courier), courier);
    }
}
