package site.aloui.bankAccountService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import site.aloui.bankAccountService.entities.BankAccount;
import site.aloui.bankAccountService.enums.AccountType;
import site.aloui.bankAccountService.repositories.BankAccountRepository;

import java.util.Date;


@SpringBootApplication
public class PracticalActivity2Application {

    public static void main(String[] args) {
        SpringApplication.run(PracticalActivity2Application.class, args);
    }

    @Bean
    CommandLineRunner init(BankAccountRepository bankAccountRepository) {
        return (args) -> {
            for(int i = 0; i < 10; i++) {
                bankAccountRepository.save(
                  BankAccount.builder()
                          .type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                          .balance(10 + Math.random() * 10000)
                          .currency("MAD")
                          .createdAt(new Date())
                          .build()
                );
            }
        };
    }
}
