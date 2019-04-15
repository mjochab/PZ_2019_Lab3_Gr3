package serwisPaczek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serwisPaczek.model.*;
import serwisPaczek.repository.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static serwisPaczek.model.Status.WYSLANO_ZGLOSZENIE;

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
    private OrderRepository orderRepository;
    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private OpinionRepository opinionRepository;

    @SuppressWarnings("Duplicates")
    public void fillDatabase() {

        List<Role> roleList = new ArrayList<>();
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

        for (int i = 1; i < 10; i++) {
            Role role = new Role("ROLE_" + i);
            roleList.add(role);

            Adress adress = new Adress("name" + i, "surname" + i,
                    "Rzeszów" + i, "Rejtana" + i, i,
                    i + "-" + i, 1L,
                    "email" + i
            );
            adressList.add(adress);

            User user = new User("Patryk" + i, "Brzuchacz" + i, role, adress);
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

            GiftOrder giftOrder = new GiftOrder(date, gift, user, recipientAdress);
            giftOrderList.add(giftOrder);

            Parcel parcel = new Parcel(100 + i,20 + i,30 + i,"Paczka", "Karnisz");
            parcelList.add(parcel);

            UserOrder userOrder = new UserOrder(123 + i, date, user, courier, WYSLANO_ZGLOSZENIE, senderAdress, recipientAdress, parcel);
            orderList.add(userOrder);

            Opinion opinion = new Opinion(date,"Bardzo dobrze",2 + i, userOrder);
            opinionList.add(opinion);

        }

        roleRepository.saveAll(roleList);
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
        orderRepository.saveAll(orderList);
        parcelRepository.saveAll(parcelList);
        opinionRepository.saveAll(opinionList);
    }
}
