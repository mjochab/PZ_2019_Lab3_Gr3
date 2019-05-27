package serwisPaczek.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class SenderAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "senderAddress", cascade = CascadeType.ALL)
    private List<UserOrder> userOrder = new ArrayList<>();

    public SenderAddress() {
    }

    public SenderAddress(Address address) {
        this.address = address;
    }
}
