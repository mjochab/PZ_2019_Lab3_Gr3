package serwisPaczek.model;

import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserOrder> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<GiftOrder> giftOrders = new ArrayList<>();

    //    @ManyToOne
//    @JoinColumn(name = "address_id")
//    private Address address;
}
