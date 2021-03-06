package io.anhkhue.ctsa.vietnamworksscraper.model;

import lombok.*;

import java.sql.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recruitment {

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
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, source, link, positionId, number);
    }
}
