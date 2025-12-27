package site.aloui.cqrsandeventsourcing.query.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import site.aloui.cqrsandeventsourcing.commons.enums.AccountStatus;

import java.time.Instant;
import java.util.Date;

@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Account {
    @Id
    private String id;
    private double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private Instant createdAt;
}
