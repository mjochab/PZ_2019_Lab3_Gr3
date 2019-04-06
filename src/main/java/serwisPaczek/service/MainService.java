package serwisPaczek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serwisPaczek.model.Adress;
import serwisPaczek.model.Courier;
import serwisPaczek.model.Role;
import serwisPaczek.model.User;
import serwisPaczek.repository.*;
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
    private GiftOrderRepository giftOrderRepository;
    @Autowired
    private GiftRepository giftRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PackPricingRepository packPricingRepository;
    @Autowired
    private PalletPricingRepository palletPricingRepository;
    @Autowired
    private RecipientAdressRepository recipientAdressRepository;
    @Autowired
    private SenderAdressRepository senderAdressRepository;

    public void fillDatabase() {
        List<Role> roleList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        List<Adress> adressList = new ArrayList<>();
        List<Courier> courierList = new ArrayList<>();

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
        }

        roleRepository.saveAll(roleList);
        adressRepository.saveAll(adressList);
        userRepository.saveAll(userList);
    }
}
