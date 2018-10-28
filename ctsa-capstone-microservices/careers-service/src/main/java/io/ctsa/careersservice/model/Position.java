package io.ctsa.careersservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Position {

    private int id;
    private String name;
    private int businessFieldId;
    private String imageUrl;
    private BusinessField businessField;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "business_field_id")
    public int getBusinessFieldId() {
        return businessFieldId;
    }

    public void setBusinessFieldId(int businessFieldId) {
        this.businessFieldId = businessFieldId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return id == position.id &&
                businessFieldId == position.businessFieldId &&
                Objects.equals(name, position.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, businessFieldId);
    }

    @Basic
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @ManyToOne
    @JoinColumn(name = "business_field_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false)
    public BusinessField getBusinessField() {
        return businessField;
    }

    public void setBusinessField(BusinessField businessField) {
        this.businessField = businessField;
    }
}
