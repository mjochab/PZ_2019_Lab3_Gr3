package serwisPaczek.model;

import javax.persistence.*;

@Entity
public class PalletPricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //inkrementacja ID
    private Long id;

    private float up_to_300;
    private float up_to_500;
    private float up_to_800;
    private float up_to_1000;

    @OneToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;
}