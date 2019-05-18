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
    private boolean is_blocked;

    @OneToOne(mappedBy = "courier", cascade = CascadeType.ALL)
    private EnvelopePricing envelopePricing;
    @OneToOne(mappedBy = "courier", cascade = CascadeType.ALL)
    private PalletPricing palletPricing;
    @OneToOne(mappedBy = "courier", cascade = CascadeType.ALL)
    private PackPricing packPricing;

    public Courier() {
    }

    public Courier(String name) {
        this.name = name;
    }

    public Courier(String name, boolean isBlocked) {
        this.name = name;
        this.is_blocked = isBlocked;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

