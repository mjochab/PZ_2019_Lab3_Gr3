package serwisPaczek.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Data
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

    public PalletPricing(float up_to_300, float up_to_500, float up_to_800, float up_to_1000, Courier courier) {
        this.up_to_300 = up_to_300;
        this.up_to_500 = up_to_500;
        this.up_to_800 = up_to_800;
        this.up_to_1000 = up_to_1000;
        this.courier = courier;
    }

    public PalletPricing() {
    }
}
