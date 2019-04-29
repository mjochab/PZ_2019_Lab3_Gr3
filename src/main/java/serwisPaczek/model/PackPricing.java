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

    public PackPricing(float up_to_1, float up_to_2, float up_to_5, float up_to_10, float up_to_15, float up_to_20, float up_to_30, Courier courier) {
        this.up_to_1 = up_to_1;
        this.up_to_2 = up_to_2;
        this.up_to_5 = up_to_5;
        this.up_to_10 = up_to_10;
        this.up_to_15 = up_to_15;
        this.up_to_20 = up_to_20;
        this.up_to_30 = up_to_30;
        this.courier = courier;
    }

    public PackPricing(){

    }
}
