package serwisPaczek.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class SenderAdress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "adress_id")
    private Adress adress;
    @OneToMany(mappedBy = "senderAdress", cascade = CascadeType.ALL)
    private List<UserOrder> userOrder = new ArrayList<>();

    public SenderAdress() {
    }

    public SenderAdress(Adress adress) {
        this.adress = adress;
    }
}
//TODO[PATRYK]: Rename class SenderAdress -> SenderAddress