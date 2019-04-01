package serwisPaczek.model;

import javax.persistence.*;

@Entity
public class PackPricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //inkrementacja ID
    private Long id;
    private float up_to_1;
    private float up_to_2;
    private float up_to_5;
    private float up_to_10;
    private float up_to_15;
    private float up_to_20;
    private float up_to_30;

    @OneToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;
}
