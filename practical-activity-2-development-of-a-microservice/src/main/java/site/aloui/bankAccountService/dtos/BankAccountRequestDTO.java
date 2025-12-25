package site.aloui.bankAccountService.dtos;

import lombok.*;
import site.aloui.bankAccountService.enums.AccountType;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountRequestDTO {

    private Double balance;
    private String currency;
    private AccountType type;

}
