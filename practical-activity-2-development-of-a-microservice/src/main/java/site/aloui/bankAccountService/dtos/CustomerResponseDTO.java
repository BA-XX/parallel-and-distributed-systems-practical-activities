package site.aloui.bankAccountService.dtos;

import lombok.*;
import site.aloui.bankAccountService.entities.BankAccount;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDTO {
    private String id;
    private String name;
    private List<BankAccount> bankAccounts;
}
