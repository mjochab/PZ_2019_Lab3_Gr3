package serwisPaczek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serwisPaczek.model.*;
import serwisPaczek.repository.*;
import serwisPaczek.security.Encryption;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CourierRepository courierRepository;
    @Autowired
    private EnvelopePricingRepository envelopePricingRepository;
    @Autowired
    private PackPricingRepository packPricingRepository;
    @Autowired
    private PalletPricingRepository palletPricingRepository;
    @Autowired
    private GiftRepository giftRepository;
    @Autowired
    private RecipientAddressRepository recipientAddressRepository;
    @Autowired
    private SenderAddressRepository senderAddressRepository;
    @Autowired
    private GiftOrderRepository giftOrderRepository;
    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OpinionRepository opinionRepository;
    @Autowired
    private Encryption encryption;
    @Autowired
    private CouponRepository couponRepository;

    public void fillDatabase() {
        List<User> userList = new ArrayList<>();
        List<Address> addressList = new ArrayList<>();
        List<Courier> courierList = new ArrayList<>();
        List<EnvelopePricing> envelopePricingList = new ArrayList<>();
        List<PackPricing> packPricingList = new ArrayList<>();
        List<PalletPricing> palletPricingList = new ArrayList<>();
        List<Gift> giftList = new ArrayList<>();
        List<RecipientAddress> recipientAddressList = new ArrayList<>();
        List<SenderAddress> senderAddressList = new ArrayList<>();
        List<GiftOrder> giftOrderList = new ArrayList<>();
        List<UserOrder> orderList = new ArrayList<>();
        List<Parcel> parcelList = new ArrayList<>();
        List<Opinion> opinionList = new ArrayList<>();
        List<Coupon> couponList = new ArrayList<>();


        for (int i = 1; i < 10; i++) {

            Address address = new Address("name" + i, "surname" + i,
                    "Rzeszów" + i, "Rejtana" + i, i,
                    i + "-" + i, 1L,
                    "email" + i
            );
            addressList.add(address);

            User user = new User("Uzytkownik" + i, encryption.encode("Uzytkownik" + i), roleRepository.findByRoleName(
                    "USER_ROLE"),
                    address);
            userList.add(user);

            Courier courier = new Courier("Kurier " + i);
            courierList.add(courier);

            EnvelopePricing envelopePricing = new EnvelopePricing(1 + i, courier);
            envelopePricingList.add(envelopePricing);

            PackPricing packPricing = new PackPricing(1 + i, 2 + i, 3 + i, 4 + i, 5 + i, 6 + i, 7 + i, courier);
            packPricingList.add(packPricing);

            PalletPricing palletPricing = new PalletPricing(100 + i, 200 + i, 300 + i, 400 + i, courier);
            palletPricingList.add(palletPricing);

            Gift gift = new Gift("Prezent nr " + i, 100 + i, "AKTYWNY");
            giftList.add(gift);

            RecipientAddress recipientAddress = new RecipientAddress(address);
            recipientAddressList.add(recipientAddress);

            SenderAddress senderAddress = new SenderAddress(address);
            senderAddressList.add(senderAddress);

            Date date = new Date(System.currentTimeMillis());

            GiftOrder giftOrder = new GiftOrder(date, gift, user, Status.WYSLANO_ZGLOSZENIE, recipientAddress);
            giftOrderList.add(giftOrder);

            Parcel parcel = new Parcel(100 + i, 20 + i, 30 + i, "paczka", i);
            parcelList.add(parcel);

            UserOrder userOrder = new UserOrder(123 + i, date, user, courier, Status.WYSLANO_ZGLOSZENIE,
                    senderAddress, recipientAddress, parcel);
            orderList.add(userOrder);

            Opinion opinion = new Opinion(date, "Bardzo dobrze", 2 + i, userOrder);
            opinionList.add(opinion);

            Coupon coupon = new Coupon("Kupon" + i, 10 + i);
            couponList.add(coupon);

        }

        addressRepository.saveAll(addressList);
        userRepository.saveAll(userList);
        courierRepository.saveAll(courierList);
        envelopePricingRepository.saveAll(envelopePricingList);
        packPricingRepository.saveAll(packPricingList);
        palletPricingRepository.saveAll(palletPricingList);
        giftRepository.saveAll(giftList);
        recipientAddressRepository.saveAll(recipientAddressList);
        senderAddressRepository.saveAll(senderAddressList);
        giftOrderRepository.saveAll(giftOrderList);
        parcelRepository.saveAll(parcelList);
        orderRepository.saveAll(orderList);
        opinionRepository.saveAll(opinionList);
        couponRepository.saveAll(couponList);
    }
}
