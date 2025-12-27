package site.aloui.cqrsandeventsourcing.command.aggregates;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import site.aloui.cqrsandeventsourcing.command.commands.AddAccountCommand;
import site.aloui.cqrsandeventsourcing.command.commands.CreditAccountCommand;
import site.aloui.cqrsandeventsourcing.command.commands.DebitAccountCommand;
import site.aloui.cqrsandeventsourcing.command.commands.UpdateAccountStatusCommand;
import site.aloui.cqrsandeventsourcing.commons.enums.AccountStatus;
import site.aloui.cqrsandeventsourcing.commons.events.*;

@Aggregate
@NoArgsConstructor
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
    private String id;
    private double balance;
    private AccountStatus status;

    @CommandHandler
    public AccountAggregate(AddAccountCommand command) {

        log.info("[CommandHandler] Received AddAccountCommand");

        if (command.initialBalance() <= 0)
            throw new IllegalArgumentException("initial balance must be greater than 0.");
        AggregateLifecycle.apply(
                AccountCreatedEvent.builder()
                        .accountId(command.id())
                        .status(AccountStatus.CREATED)
                        .currency(command.currency())
                        .initialBalance(command.initialBalance())
                        .build()
        );

        AggregateLifecycle.apply(
                AccountActivatedEvent.builder()
                        .accountId(command.id())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        log.info("[EventSourcingHandler] Received AccountCreatedEvent");
        this.id = event.accountId();
        this.status = event.status();
        this.balance = event.initialBalance();
    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent event) {
        log.info("[EventSourcingHandler] Received AccountActivatedEvent");
        this.status = AccountStatus.ACTIVATED;
    }

    @CommandHandler
    public void handle(CreditAccountCommand command) {
        log.info("[CommandHandler] Received CreditAccountCommand");
        if (!status.equals(AccountStatus.ACTIVATED))
            throw new IllegalArgumentException(String.format("Account %s  not ACTIVATED", command.accountId()));
        if (command.amount() <= 0)
            throw new IllegalArgumentException("Amount must be greater than 0.");

        AggregateLifecycle.apply(
                AccountCreditedEvent.builder()
                        .accountId(command.accountId())
                        .amount(command.amount())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent event) {
        log.info("[EventSourcingHandler] Received AccountCreditedEvent");
        this.balance += event.amount();
    }

    @CommandHandler
    public void handle(DebitAccountCommand command) {
        log.info("[CommandHandler] Received DebitAccountCommand");

        if (!status.equals(AccountStatus.ACTIVATED))
            throw new IllegalArgumentException(String.format("Account %s  not ACTIVATED", command.accountId()));
        if (balance < command.amount())
            throw new IllegalArgumentException("Insufficient funds.");
        if (command.amount() <= 0)
            throw new IllegalArgumentException("Amount must be greater than 0.");

        AggregateLifecycle.apply(
                AccountDebitedEvent.builder()
                        .accountId(command.accountId())
                        .amount(command.amount())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent event) {
        log.info("[EventSourcingHandler] Received AccountDebitedEvent");
        this.balance -= event.amount();
    }

    @CommandHandler
    public void on(UpdateAccountStatusCommand command) {
        log.info("[CommandHandler] Received UpdateAccountStatusCommand");
        if (status.equals(command.status()))
            throw new IllegalArgumentException(String.format("Account %s is already %s", command.accountId(), command.status()));

        AggregateLifecycle.apply(
                AccountStatusUpdatedEvent.builder()
                        .accountId(command.accountId())
                        .status(command.status())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(AccountStatusUpdatedEvent event) {
        log.info("[EventSourcingHandler] Received AccountStatusUpdatedEvent");
        this.status = event.status();
    }
}
