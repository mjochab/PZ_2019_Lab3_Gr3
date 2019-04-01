package serwisPaczek.model;
import javax.persistence.*;

@Entity
public class RecipientAdress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "adress_id")
    private Adress adress;
}
