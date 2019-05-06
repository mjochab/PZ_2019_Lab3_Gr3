package serwisPaczek.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class EnvelopePricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //inkrementacja ID
    private Long id;
    private float up_to_1;

    @OneToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    public EnvelopePricing(float up_to_1, Courier courier) {
        this.up_to_1 = up_to_1;
        this.courier = courier;
    }

    public EnvelopePricing() {
    }
}