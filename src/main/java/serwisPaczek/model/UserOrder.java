package serwisPaczek.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class UserOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float price;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "SenderAdress_id")
    private SenderAdress senderAdress;

    @ManyToOne
    @JoinColumn(name = "RecipientAdress_id")
    private RecipientAdress recipientAdress;

    public UserOrder(float price, Date date, User user, Courier courier, Status status, SenderAdress senderAdress, serwisPaczek.model.RecipientAdress recipientAdress) {
        this.price = price;
        this.date = date;
        this.user = user;
        this.courier = courier;
        this.status = status;
        this.senderAdress = senderAdress;
        this.recipientAdress = recipientAdress;
    }
}
