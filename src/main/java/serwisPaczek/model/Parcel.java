package serwisPaczek.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Parcel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int length;

    private int width;

    private int height;

    private String type;

    private String content;

    @OneToOne(mappedBy = "parcel", cascade = CascadeType.ALL)
    private UserOrder userOrder;

    public Parcel() {
    }

    public Parcel(int lenght, int width, int height, String type, String content){
        this.length = lenght;
        this.width = width;
        this.height = height;
        this.type = type;
        this.content = content;
    }

}

