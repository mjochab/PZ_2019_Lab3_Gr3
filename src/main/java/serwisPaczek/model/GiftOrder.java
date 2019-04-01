package serwisPaczek.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class GiftOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "gift_id")
    private Gift gift;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

//    @ManyToOne
//    @JoinColumn(name = "recipient_id")
//    private Recipient recipient;


}
