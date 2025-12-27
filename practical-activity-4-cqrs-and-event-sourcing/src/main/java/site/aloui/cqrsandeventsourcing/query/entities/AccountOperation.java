package site.aloui.cqrsandeventsourcing.query.entities;

import jakarta.persistence.*;
import lombok.*;
import site.aloui.cqrsandeventsourcing.commons.enums.OperationType;

import java.time.Instant;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Instant date;
    private double amount;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    @ManyToOne
    private Account account;

}
