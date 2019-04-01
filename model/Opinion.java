package serwisPaczek.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Opinion{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private UserOrder UserOrder;
    
    private String content;

    private Int rating;


}