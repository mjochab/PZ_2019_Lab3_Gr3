package serwisPaczek.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String zipCode;
    private Long telephoneNumber;
    private String email;

    @OneToOne(mappedBy = "adress", cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "adress", cascade = CascadeType.ALL)
    private List<SenderAdress> senderAdressList = new ArrayList<>();

    @OneToMany(mappedBy = "adress", cascade = CascadeType.ALL)
    private List<RecipientAdress> recipientAdressList = new ArrayList<>();

    public Adress(String name, String surname, String city, String street, int houseNumber, String zipCode, Long telephoneNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
    }
}
