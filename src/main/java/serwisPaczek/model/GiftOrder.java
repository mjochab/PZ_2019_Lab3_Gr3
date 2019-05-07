package serwisPaczek.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class GiftOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "gift_id")
    private Gift gift;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private RecipientAdress recipientAdress;

    public GiftOrder(Date date, Gift gift, User user, RecipientAdress recipientAdress) {
        this.date = date;
        this.gift = gift;
        this.user = user;
        this.recipientAdress = recipientAdress;
    }

    public GiftOrder() {
    }
}
