package serwisPaczek.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class RecipientAdress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "adress_id")
    private Adress adress;


    @OneToMany(mappedBy = "recipientAdress", cascade = CascadeType.ALL)
    private List<GiftOrder> giftOrder = new ArrayList<>();

    @OneToMany(mappedBy = "recipientAdress", cascade = CascadeType.ALL)
    private List<UserOrder> userOrder = new ArrayList<>();


    public RecipientAdress() {
    }

    public RecipientAdress(Adress adress) {
        this.adress = adress;
    }
}
