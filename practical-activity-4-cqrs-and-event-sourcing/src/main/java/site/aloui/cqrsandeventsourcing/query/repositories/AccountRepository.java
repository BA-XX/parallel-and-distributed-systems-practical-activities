package site.aloui.cqrsandeventsourcing.query.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import site.aloui.cqrsandeventsourcing.query.entities.Account;

public interface AccountRepository extends JpaRepository<Account,String> {
}
