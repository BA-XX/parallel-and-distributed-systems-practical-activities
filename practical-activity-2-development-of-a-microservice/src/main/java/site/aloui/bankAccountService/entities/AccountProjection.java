package site.aloui.bankAccountService.entities;

import org.springframework.data.rest.core.config.Projection;
import site.aloui.bankAccountService.enums.AccountType;

@Projection(types = BankAccount.class, name = "p1")
public interface AccountProjection {

    String getId();
    AccountType getType();
}
