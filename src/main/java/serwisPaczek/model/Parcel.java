package serwisPaczek.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int length;

    private int width;

    private int height;

    private String type;

    private int weight;

    @OneToOne(mappedBy = "parcel", cascade = CascadeType.ALL)
    private UserOrder userOrder;

    public Parcel() {
    }

    public Parcel(int lenght, int width, int height, String type, int weight) {
        this.length = lenght;
        this.width = width;
        this.height = height;
        this.type = type;
        this.weight=weight;
    }

}

