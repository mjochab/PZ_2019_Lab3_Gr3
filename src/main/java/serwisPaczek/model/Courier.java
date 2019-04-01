package serwisPaczek.model;
import javax.persistence.*;


@Entity
public class Courier {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

}

