package io.ctsa.companymanagement.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
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
    private String title;
    private String position;
    @NonNull
    private Timestamp startDate;
    @NonNull
    private Timestamp endDate;
    private Timestamp modifiedDate;
    private Integer number;
    @NonNull
    private String jobDescription;
    @NonNull
    private String jobRequirement;
    @NonNull
    private int enabled;
    @NonNull
    private String email;
    @NonNull
    private String phone;
    @NonNull
    private String address;
    private String additionalInformation;
    private Integer viewCount;
    private String image;
    @NonNull
    private int companyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recruitment that = (Recruitment) o;
        return id == that.id &&
                enabled == that.enabled &&
                companyId == that.companyId &&
                Objects.equals(title, that.title) &&
                Objects.equals(position, that.position) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(modifiedDate, that.modifiedDate) &&
                Objects.equals(number, that.number) &&
                Objects.equals(jobDescription, that.jobDescription) &&
                Objects.equals(jobRequirement, that.jobRequirement) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(address, that.address) &&
                Objects.equals(additionalInformation, that.additionalInformation) &&
                Objects.equals(viewCount, that.viewCount) &&
                Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, position, startDate, endDate, modifiedDate, number, jobDescription, jobRequirement, enabled, email, phone, address, additionalInformation, viewCount, image, companyId);
    }
}
