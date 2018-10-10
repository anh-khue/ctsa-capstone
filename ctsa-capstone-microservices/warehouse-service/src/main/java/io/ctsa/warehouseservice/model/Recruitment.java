package io.ctsa.warehouseservice.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private Date startDate;
    @NonNull
    private Date endDate;
    @NonNull
    private String source;
    @NonNull
    private String link;
    private int positionId;
    private int number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recruitment that = (Recruitment) o;
        return id == that.id &&
                positionId == that.positionId &&
                number == that.number &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(source, that.source) &&
                Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, source, link, positionId, number);
    }
}
