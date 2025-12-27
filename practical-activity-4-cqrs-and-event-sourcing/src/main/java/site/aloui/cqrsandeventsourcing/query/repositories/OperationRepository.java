package site.aloui.cqrsandeventsourcing.query.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import site.aloui.cqrsandeventsourcing.query.entities.AccountOperation;

import java.util.List;

public interface OperationRepository extends JpaRepository<AccountOperation, String> {

    List<AccountOperation> findByAccountId(String accountId);

}
