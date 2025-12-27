package site.aloui.cqrsandeventsourcing.query.handlers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import site.aloui.cqrsandeventsourcing.query.dtos.AccountStatementResponseDTO;
import site.aloui.cqrsandeventsourcing.query.entities.Account;
import site.aloui.cqrsandeventsourcing.query.entities.AccountOperation;
import site.aloui.cqrsandeventsourcing.query.queries.GetAccountStatementQuery;
import site.aloui.cqrsandeventsourcing.query.queries.GetAllAccountsQuery;
import site.aloui.cqrsandeventsourcing.query.queries.WatchEventQuery;
import site.aloui.cqrsandeventsourcing.query.repositories.AccountRepository;
import site.aloui.cqrsandeventsourcing.query.repositories.OperationRepository;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class AccountQueryHandler {

    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query) {
        return accountRepository.findAll();
    }

    @QueryHandler
    public AccountStatementResponseDTO on(GetAccountStatementQuery query) {
        Account account = accountRepository.findById(query.getAccountId()).get();

        List<AccountOperation> operations = operationRepository.findByAccountId(query.getAccountId());

        return AccountStatementResponseDTO.builder()
                .account(account)
                .operations(operations)
                .build();
    }

    @QueryHandler
    public AccountOperation on(WatchEventQuery query) {
        return AccountOperation.builder().build();
    }
}
