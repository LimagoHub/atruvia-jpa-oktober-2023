package de.atruvia.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Anschrift {

    @Column(length = 6, nullable = true)
    private String plz;
    @Column(length = 30, nullable = true)
    private String ort;
}
