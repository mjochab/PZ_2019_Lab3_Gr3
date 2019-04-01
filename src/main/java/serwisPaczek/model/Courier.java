package serwisPaczek.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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



}

