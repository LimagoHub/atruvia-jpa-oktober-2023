package de.atruvia.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="tbl_neue_personen")
public class NeuePerson extends AbstractEntity{



    @Column(length = 30, nullable = false)
    private String vorname;

    @Column(length = 30, nullable = false)
    private String nachname;

    @Version
    private long version;

//    @Version
//    private LocalDateTime lastUpdate;


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        final Person person = (Person) o;
        return getId() != null && Objects.equals(getId(), person.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}