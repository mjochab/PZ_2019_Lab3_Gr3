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
}
