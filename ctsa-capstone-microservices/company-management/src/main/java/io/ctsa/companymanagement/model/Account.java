package io.ctsa.companymanagement.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private int enabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                enabled == account.enabled &&
                Objects.equals(username, account.username) &&
                Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, password, enabled);
    }
}
