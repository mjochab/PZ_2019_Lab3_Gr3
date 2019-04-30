package serwisPaczek.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Opinion{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @OneToOne
    @JoinColumn(name = "user_order_id")
    private UserOrder userOrder;

    private String content;

    private int rating;

    public Opinion(Date date, String content, int rating, UserOrder userOrder){
        this.date = date;
        this.content = content;
        this.rating = rating;
        this.userOrder = userOrder;
    }
}