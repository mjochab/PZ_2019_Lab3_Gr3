package serwisPaczek.model;

import javax.persistence.*;

@Entity
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int premiumPoints;

    private String logo; // url?


}
