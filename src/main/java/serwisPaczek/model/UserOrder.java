package serwisPaczek.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
