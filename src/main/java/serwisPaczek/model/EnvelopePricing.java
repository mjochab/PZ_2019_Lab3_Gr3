package serwisPaczek.model;

import javax.persistence.*;

@Entity
public class EnvelopePricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //inkrementacja ID
    private Long id;
    private float up_to_1;
}
