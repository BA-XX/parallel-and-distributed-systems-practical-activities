package site.aloui.bankAccountService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import site.aloui.bankAccountService.enums.AccountType;

import java.util.Date;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date createdAt;
    private Double balance;
    private String currency;
    private AccountType type;

}
