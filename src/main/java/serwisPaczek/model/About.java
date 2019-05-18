package serwisPaczek.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    public About() {
    }

    public About(String content) {
        this.content = content;
    }
}
