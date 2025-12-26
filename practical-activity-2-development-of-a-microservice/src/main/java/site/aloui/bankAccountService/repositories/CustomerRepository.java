package site.aloui.bankAccountService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import site.aloui.bankAccountService.entities.Customer;

public interface  CustomerRepository extends JpaRepository<Customer, String> {
}
