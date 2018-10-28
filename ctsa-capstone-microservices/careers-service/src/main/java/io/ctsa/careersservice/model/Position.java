package io.ctsa.careersservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "business_field_id")
    private int businessFieldId;
    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "business_field_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false)
    private BusinessField businessField;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return id == position.id &&
                businessFieldId == position.businessFieldId &&
                Objects.equals(name, position.name);
    }
}
