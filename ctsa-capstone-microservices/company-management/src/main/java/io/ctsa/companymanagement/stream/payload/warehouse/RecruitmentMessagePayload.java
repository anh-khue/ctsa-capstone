package io.ctsa.companymanagement.stream.payload.warehouse;

import io.ctsa.companymanagement.model.Recruitment;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@AllArgsConstructor
@Builder
public class RecruitmentMessagePayload {

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

    public RecruitmentMessagePayload(Recruitment recruitment) {
        this.startDate = new Date(recruitment.getStartDate().getTime());
        this.endDate = new Date(recruitment.getEndDate().getTime());
        this.source = "CTSA System";
        this.link = "http://localhost:8007/recruitment/" + recruitment.getId();
        this.positionId = recruitment.getPositionId();
        this.number = recruitment.getNumber();
    }
}
