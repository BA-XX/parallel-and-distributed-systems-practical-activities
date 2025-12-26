package site.aloui.bankAccountService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import site.aloui.bankAccountService.entities.BankAccount;
import site.aloui.bankAccountService.entities.Customer;
import site.aloui.bankAccountService.enums.AccountType;
import site.aloui.bankAccountService.repositories.BankAccountRepository;
import site.aloui.bankAccountService.repositories.CustomerRepository;

import java.util.Date;
import java.util.stream.Stream;


@SpringBootApplication
public class PracticalActivity2Application {

    public static void main(String[] args) {
        SpringApplication.run(PracticalActivity2Application.class, args);
    }

    @Bean
    CommandLineRunner init(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
        return (args) -> {

            Stream.of("Mohammed", "Bilal", "Yassine").forEach(name -> {
              customerRepository.save(Customer.builder()
                      .name(name)
                      .build());
            });

            customerRepository.findAll().forEach(customer -> {
                for(int i = 0; i < 10; i++) {
                    bankAccountRepository.save(
                            BankAccount.builder()
                                    .type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                                    .balance(10 + Math.random() * 10000)
                                    .currency("MAD")
                                    .createdAt(new Date())
                                    .customer(customer)
                                    .build()
                    );
                }
            });

        };
    }
}
