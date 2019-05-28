package serwisPaczek.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    private Date date;
    @Column(name = "premium_points")
    private int premiumPoints;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "sender_address_id")
    private SenderAddress senderAddress;
    @OneToOne
    @JoinColumn(name = "parcel_id")
    private Parcel parcel;
    @ManyToOne
    @JoinColumn(name = "recipient_address_id")
    private RecipientAddress recipientAddress;
    @OneToOne(mappedBy = "userOrder", cascade = CascadeType.ALL)
    private Opinion opinion;

    public UserOrder() {
    }

    public UserOrder(double price, Date date, User user, Courier courier, Status status, SenderAddress senderAddress,
                     RecipientAddress recipientAddress, Parcel parcel) {
        this.price = price;
        this.date = date;
        this.user = user;
        this.courier = courier;
        this.status = status;
        this.senderAddress = senderAddress;
        this.recipientAddress = recipientAddress;
        this.parcel = parcel;
    }

    public UserOrder(double price, Date date, int premiumPoints, User user, Courier courier, Status status,
                     SenderAddress senderAddress, RecipientAddress recipientAddress, Parcel parcel) {
        this.price = price;
        this.date = date;
        this.premiumPoints = premiumPoints;
        this.user = user;
        this.courier = courier;
        this.status = status;
        this.senderAddress = senderAddress;
        this.recipientAddress = recipientAddress;
        this.parcel = parcel;
    }

    public UserOrder(double price, Date date, User user, Courier courier, Status status, SenderAddress senderAddress,
                     RecipientAddress recipientAddress) {
        this.price = price;
        this.date = date;
        this.user = user;
        this.courier = courier;
        this.status = status;
        this.senderAddress = senderAddress;
        this.recipientAddress = recipientAddress;
    }
}
