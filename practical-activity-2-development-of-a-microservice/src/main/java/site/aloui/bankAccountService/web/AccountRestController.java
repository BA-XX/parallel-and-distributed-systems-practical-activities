package site.aloui.bankAccountService.web;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.aloui.bankAccountService.dtos.BankAccountRequestDTO;
import site.aloui.bankAccountService.dtos.BankAccountResponseDTO;
import site.aloui.bankAccountService.entities.BankAccount;
import site.aloui.bankAccountService.repositories.BankAccountRepository;
import site.aloui.bankAccountService.services.IAccountService;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountRestController {

    private BankAccountRepository bankAccountRepository;

    private IAccountService accountService;


    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Bank account with id %s not found", id)));
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO newBankAccount(@RequestBody BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount updateBankAccount(@PathVariable String id, @RequestBody BankAccount bankAccount){

        BankAccount account = bankAccountRepository.findById(id).orElseThrow();

        if(bankAccount.getBalance() != null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCurrency() != null) account.setCurrency(bankAccount.getCurrency());
        if(bankAccount.getType() != null) account.setType(bankAccount.getType());

        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteBankAccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }
}
