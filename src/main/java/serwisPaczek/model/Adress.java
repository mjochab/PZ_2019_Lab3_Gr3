package serwisPaczek.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String city;
    private String street;
    @JoinColumn(name = "house_number")
    private Integer houseNumber;
    @JoinColumn(name = "zip_code")
    private String zipCode;
    @JoinColumn(name = "telephone_number")
    private Long telephoneNumber;
    private String email;
    @OneToOne(mappedBy = "adress", cascade = CascadeType.ALL)
    private User user;
    @OneToMany(mappedBy = "adress", cascade = CascadeType.ALL)
    private List<SenderAdress> senderAdressList = new ArrayList<>();
    @OneToMany(mappedBy = "adress", cascade = CascadeType.ALL)
    private List<RecipientAdress> recipientAdressList = new ArrayList<>();

    public Adress() {
    }

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
//TODO[PATRYK]: Rename class Adress -> Address