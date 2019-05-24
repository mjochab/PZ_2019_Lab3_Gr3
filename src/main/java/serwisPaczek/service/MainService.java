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
    private AdressRepository adressRepository;
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
    private RecipientAdressRepository recipientAdressRepository;
    @Autowired
    private SenderAdressRepository senderAdressRepository;
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
    private AboutRepository aboutRepository;
    @Autowired
    private CouponRepository couponRepository;

    public void addAbout(){
        List<About> aboutList = new ArrayList<>();
        About about = new About("Serwis nadawania paczek to nowoczesna platforma wysyłkowa umożliwiająca wysyłkę paczek zarówno na terenie całego kraju jak i za granicę. Wyróżnia nas szeroki wachlarz usług oraz dbałość o ich najwyższą jakość i niską cenę. Poprzez współpracę z najlepszymi firmami kurierskimi takimi jak UPS, DPD, Geis, InPost, FedEx, Paczkomaty, DHL zawsze zapewniamy naszym klientom najkorzystniejsze warunki. Odpowiadając na nieustannie rozwijający się handel międzynarodowy, ciągle poszerzamy naszą ofertę o możliwość wysyłki paczek nawet w najbardziej odległe zakątki świata. Nasi kurierzy codziennie przemierzają tysiące kilometrów aby na czas dostarczyć nawet najbardziej niestandardowe przesyłki. Oprócz platformy wysyłkowej serwis paczek to również ponad 150 stacjonarnych placówek w całej Polsce, które każdego dnia obsługują tysiące zadowolonych klientów.");
        aboutList.add(about);
        aboutRepository.saveAll(aboutList);
    }

    public void fillDatabase() {
        List<User> userList = new ArrayList<>();
        List<Adress> adressList = new ArrayList<>();
        List<Courier> courierList = new ArrayList<>();
        List<EnvelopePricing> envelopePricingList = new ArrayList<>();
        List<PackPricing> packPricingList = new ArrayList<>();
        List<PalletPricing> palletPricingList = new ArrayList<>();
        List<Gift> giftList = new ArrayList<>();
        List<RecipientAdress> recipientAdressList = new ArrayList<>();
        List<SenderAdress> senderAdressList = new ArrayList<>();
        List<GiftOrder> giftOrderList = new ArrayList<>();
        List<UserOrder> orderList = new ArrayList<>();
        List<Parcel> parcelList = new ArrayList<>();
        List<Opinion> opinionList = new ArrayList<>();
        List<Coupon> couponList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {

            Adress adress = new Adress("name" + i, "surname" + i,
                    "Rzeszów" + i, "Rejtana" + i, i,
                    i + "-" + i, 1L,
                    "email" + i
            );
            adressList.add(adress);

            User user = new User("Patryk" + i, encryption.encode("Brzuchacz" + i), roleRepository.findByRoleName(
                    "USER_ROLE"),
                    adress);
            userList.add(user);

            Courier courier = new Courier("Kurier " + i);
            courierList.add(courier);

            EnvelopePricing envelopePricing = new EnvelopePricing(1 + i, courier);
            envelopePricingList.add(envelopePricing);

            PackPricing packPricing = new PackPricing(1 + i, 2 + i, 3 + i, 4 + i, 5 + i, 6 + i, 7 + i, courier);
            packPricingList.add(packPricing);

            PalletPricing palletPricing = new PalletPricing(100 + i, 200 + i, 300 + i, 400 + i, courier);
            palletPricingList.add(palletPricing);

            Gift gift = new Gift("Prezent nr " + i, 100 + i);
            giftList.add(gift);

            RecipientAdress recipientAdress = new RecipientAdress(adress);
            recipientAdressList.add(recipientAdress);

            SenderAdress senderAdress = new SenderAdress(adress);
            senderAdressList.add(senderAdress);

            Date date = new Date(System.currentTimeMillis());

            GiftOrder giftOrder = new GiftOrder(date, gift, user, Status.WYSLANO_ZGLOSZENIE, recipientAdress);
            giftOrderList.add(giftOrder);

            Parcel parcel = new Parcel(100 + i, 20 + i, 30 + i, "paczka", i);
            parcelList.add(parcel);

            UserOrder userOrder = new UserOrder(123 + i, date, user, courier, Status.WYSLANO_ZGLOSZENIE, senderAdress, recipientAdress, parcel);
            orderList.add(userOrder);

            Opinion opinion = new Opinion(date, "Bardzo dobrze", 2 + i, userOrder);
            opinionList.add(opinion);

            Coupon coupon = new Coupon("Kupon"+i, 10+i);
            couponList.add(coupon);

        }

        adressRepository.saveAll(adressList);
        userRepository.saveAll(userList);
        courierRepository.saveAll(courierList);
        envelopePricingRepository.saveAll(envelopePricingList);
        packPricingRepository.saveAll(packPricingList);
        palletPricingRepository.saveAll(palletPricingList);
        giftRepository.saveAll(giftList);
        recipientAdressRepository.saveAll(recipientAdressList);
        senderAdressRepository.saveAll(senderAdressList);
        giftOrderRepository.saveAll(giftOrderList);
        parcelRepository.saveAll(parcelList);
        orderRepository.saveAll(orderList);
        opinionRepository.saveAll(opinionList);
        couponRepository.saveAll(couponList);
    }
}
