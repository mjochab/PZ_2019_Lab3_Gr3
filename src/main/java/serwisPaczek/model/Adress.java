package serwisPaczek.model;
import javax.persistence.*;

@Entity
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String city;
    private String street;
    private int houseNumber;
    private int zipCode;
    private Long telephoneNumber;
    private String email;


}
