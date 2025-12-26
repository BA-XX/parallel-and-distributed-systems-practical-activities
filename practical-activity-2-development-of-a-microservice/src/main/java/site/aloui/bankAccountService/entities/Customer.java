package site.aloui.bankAccountService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter @Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @OneToMany(mappedBy = "customer")
    private List<BankAccount> bankAccounts;
}
