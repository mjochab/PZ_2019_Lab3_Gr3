package serwisPaczek.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Parcel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Int length;

    private Int width;

    private Int height;

    private String rating;

    private String content;

}