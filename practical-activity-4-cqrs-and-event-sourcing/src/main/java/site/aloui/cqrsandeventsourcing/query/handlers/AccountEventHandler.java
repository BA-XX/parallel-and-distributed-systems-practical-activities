package site.aloui.cqrsandeventsourcing.query.handlers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;
import site.aloui.cqrsandeventsourcing.commons.enums.AccountStatus;
import site.aloui.cqrsandeventsourcing.commons.enums.OperationType;
import site.aloui.cqrsandeventsourcing.commons.events.*;
import site.aloui.cqrsandeventsourcing.query.entities.Account;
import site.aloui.cqrsandeventsourcing.query.entities.AccountOperation;
import site.aloui.cqrsandeventsourcing.query.repositories.AccountRepository;
import site.aloui.cqrsandeventsourcing.query.repositories.OperationRepository;

@Component
@AllArgsConstructor
@Slf4j
public class AccountEventHandler {

    private AccountRepository accountRepository;
    private OperationRepository operationRepository;
    private QueryUpdateEmitter  queryUpdateEmitter;

    @EventHandler
    public void on(AccountCreatedEvent event, EventMessage eventMessage) {

        log.info("[QuerySide] Received Account Created Event");

        Account account = Account.builder()
                .id(event.accountId())
                .balance(event.initialBalance())
                .status(event.status())
                .currency(event.currency())
                .createdAt(eventMessage.getTimestamp())
                .build();

        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent event) {
        log.info("[QuerySide] Received Account Activated Event");

        Account account = accountRepository.findById(event.accountId()).get();
        account.setStatus(AccountStatus.ACTIVATED);

        accountRepository.save(account);

    }

    @EventHandler
    public void on(AccountStatusUpdatedEvent event) {
        log.info("[QuerySide] Received Account Status Updated Event");

        Account account = accountRepository.findById(event.accountId()).get();
        account.setStatus(AccountStatus.ACTIVATED);

        accountRepository.save(account);

    }

    @EventHandler
    public void on(AccountDebitedEvent event, EventMessage eventMessage) {
        log.info("[QuerySide] Received Account Debited Event");

        Account account = accountRepository.findById(event.accountId()).get();

        AccountOperation accountOperation = AccountOperation.builder()
                .type(OperationType.DEBIT)
                .account(account)
                .amount(event.amount())
                .date(eventMessage.getTimestamp())
                .build();

        operationRepository.save(accountOperation);

        account.setBalance(account.getBalance() - event.amount());

        accountRepository.save(account);

        queryUpdateEmitter.emit(e-> true, accountOperation);

    }

    @EventHandler
    public void on(AccountCreditedEvent event, EventMessage eventMessage) {
        log.info("[QuerySide] Received Account Credited Event");

        Account account = accountRepository.findById(event.accountId()).get();

        AccountOperation accountOperation = AccountOperation.builder()
                .type(OperationType.CREDIT)
                .account(account)
                .amount(event.amount())
                .date(eventMessage.getTimestamp())
                .build();

        operationRepository.save(accountOperation);

        account.setBalance(account.getBalance() + event.amount());

        accountRepository.save(account);

        queryUpdateEmitter.emit(e-> true, accountOperation);
    }
}
