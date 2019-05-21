package serwisPaczek.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @Column (name = "premium_points_balance")
    private int premiumPointsBalance;

    @Column(name = "active")
    @ColumnDefault("true")
    private Boolean active = true;

    @Column(name = "account_balance")
    private double accountBalance;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserOrder> orders = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<GiftOrder> giftOrders = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "adress_id")
    private Adress adress;

    public User() {
    }

    public User(String username, String password, Role role, Adress adress, Boolean active) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.adress = adress;
        this.active = active;
    }

    public User(String username, String password, Role role, Adress adress) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.adress = adress;
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
