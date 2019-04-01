package serwisPaczek.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JoinColumn(name = "premium_points")
    private int premiumPoints;


    @OneToMany(mappedBy = "gift", cascade = CascadeType.ALL)
    private List<GiftOrder> giftOrders = new ArrayList<>();


}