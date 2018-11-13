package io.ctsa.companymanagement.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    private String description;
    @NonNull
    private String logo;
    @NonNull
    private String email;
    @NonNull
    private String phone;
    @NonNull
    private String address;
    private String additionalInformation;
    @NonNull
    private int accountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id &&
                accountId == company.accountId &&
                Objects.equals(name, company.name) &&
                Objects.equals(description, company.description) &&
                Objects.equals(logo, company.logo) &&
                Objects.equals(email, company.email) &&
                Objects.equals(phone, company.phone) &&
                Objects.equals(address, company.address) &&
                Objects.equals(additionalInformation, company.additionalInformation);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, logo, email, phone, address, additionalInformation, accountId);
    }
}
