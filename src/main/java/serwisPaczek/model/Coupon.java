package serwisPaczek.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer discount;

    public Coupon() {
    }

    public Coupon(String name, int discount) {
        this.name = name;
        this.discount = discount;
    }
}
