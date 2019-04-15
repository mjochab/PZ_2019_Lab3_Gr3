package serwisPaczek.model;


import javax.persistence.*;
import java.util.Date;

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
    @JoinColumn(name = "sender_adress_id")
    private SenderAdress senderAdress;

    @OneToOne
    @JoinColumn(name = "parcel_id")
    private Parcel parcel;

    @ManyToOne
    @JoinColumn(name = "recipient_adress_id")
    private RecipientAdress recipientAdress;

    @OneToOne(mappedBy = "userOrder", cascade = CascadeType.ALL)
    private Opinion opinion;

    public UserOrder(float price, Date date, User user, Courier courier, Status status, SenderAdress senderAdress, serwisPaczek.model.RecipientAdress recipientAdress, Parcel parcel) {
        this.price = price;
        this.date = date;
        this.user = user;
        this.courier = courier;
        this.status = status;
        this.senderAdress = senderAdress;
        this.recipientAdress = recipientAdress;
        this.parcel = parcel;
    }
}
