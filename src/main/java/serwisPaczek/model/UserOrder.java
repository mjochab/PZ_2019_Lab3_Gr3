package serwisPaczek.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private float price;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    @OneToMany(mappedBy = "adress", cascade = CascadeType.ALL)
    List<SenderAdress> senderAdresses = new ArrayList<>();

    @OneToMany(mappedBy = "adress", cascade = CascadeType.ALL)
    List<RecipientAdress> recipientAdresses = new ArrayList<>();

    private Status status;
}
