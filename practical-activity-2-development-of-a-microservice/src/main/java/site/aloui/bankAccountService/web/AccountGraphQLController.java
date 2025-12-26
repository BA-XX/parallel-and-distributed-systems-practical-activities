package site.aloui.bankAccountService.web;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import site.aloui.bankAccountService.dtos.BankAccountRequestDTO;
import site.aloui.bankAccountService.dtos.BankAccountResponseDTO;
import site.aloui.bankAccountService.services.IAccountService;

import java.util.List;

@Controller
@AllArgsConstructor
public class AccountGraphQLController {

    private IAccountService accountService;

    @QueryMapping
    public List<BankAccountResponseDTO> accountsList() {
        return accountService.getAllAccounts();
    }


    @QueryMapping
    public BankAccountResponseDTO findAccountById(@Argument String id) {
        return accountService.getAccountById(id);
    }

    @MutationMapping
    public BankAccountResponseDTO createAccount(@Argument BankAccountRequestDTO bankAccount) {
        return accountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountResponseDTO updateBankAccount(@Argument String id, @Argument BankAccountRequestDTO bankAccount) {
        return accountService.updateAccount(id, bankAccount);
    }

    @MutationMapping
    public String deleteBankAccount(@Argument String id) {
        accountService.deleteAccountById(id);
        return id;
    }
}
