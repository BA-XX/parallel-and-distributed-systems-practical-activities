package site.aloui.bankAccountService.dtos;

import lombok.*;
import site.aloui.bankAccountService.enums.AccountType;

import java.util.Date;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountResponseDTO {
    private String id;
    private Date createdAt;
    private Double balance;
    private String currency;
    private AccountType type;
}
