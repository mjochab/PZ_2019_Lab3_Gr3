package serwisPaczek.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;
    @JoinColumn(name = "premium_points")
    private int premiumPoints;
    @OneToMany(mappedBy = "gift", cascade = CascadeType.ALL)
    private List<GiftOrder> giftOrder = new ArrayList<>();

    public Gift() {
    }

    public Gift(String name, int premiumPoints, String status) {
        this.status = status;
        this.name = name;
        this.premiumPoints = premiumPoints;
    }
}
