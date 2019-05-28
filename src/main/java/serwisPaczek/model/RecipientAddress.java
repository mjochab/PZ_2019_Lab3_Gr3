package serwisPaczek.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class RecipientAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "recipientAddress", cascade = CascadeType.ALL)
    private List<GiftOrder> giftOrder = new ArrayList<>();
    @OneToMany(mappedBy = "recipientAddress", cascade = CascadeType.ALL)
    private List<UserOrder> userOrder = new ArrayList<>();

    public RecipientAddress() {
    }

    public RecipientAddress(Address address) {
        this.address = address;
    }
}
