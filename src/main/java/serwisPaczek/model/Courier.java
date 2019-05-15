package serwisPaczek.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "courier", cascade = CascadeType.ALL)
    private EnvelopePricing envelopePricing;
    @OneToOne(mappedBy = "courier", cascade = CascadeType.ALL)
    private PalletPricing palletPricing;
    @OneToOne(mappedBy = "courier", cascade = CascadeType.ALL)
    private PackPricing packPricing;

    public Courier(String name) {
        this.name = name;
    }

    public Courier() {
    }

    @Override
    public String toString() {
        return this.name;
    }
}

