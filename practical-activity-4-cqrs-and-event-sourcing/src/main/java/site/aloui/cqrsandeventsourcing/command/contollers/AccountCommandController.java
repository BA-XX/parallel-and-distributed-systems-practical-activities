package site.aloui.cqrsandeventsourcing.command.contollers;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;
import site.aloui.cqrsandeventsourcing.command.commands.AddAccountCommand;
import site.aloui.cqrsandeventsourcing.command.commands.CreditAccountCommand;
import site.aloui.cqrsandeventsourcing.command.commands.DebitAccountCommand;
import site.aloui.cqrsandeventsourcing.command.commands.UpdateAccountStatusCommand;
import site.aloui.cqrsandeventsourcing.command.dtos.AddAccountRequestDTO;
import site.aloui.cqrsandeventsourcing.command.dtos.CreditAccountRequestDTO;
import site.aloui.cqrsandeventsourcing.command.dtos.DebitAccountRequestDTO;
import site.aloui.cqrsandeventsourcing.command.dtos.UpdateAccountStatusRequestDTO;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@RequestMapping("/commands/accounts")
public class AccountCommandController {

    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/add")
    public CompletableFuture<String> addAccount(@RequestBody AddAccountRequestDTO request) {
        return commandGateway.send(
                AddAccountCommand.builder()
                        .id(UUID.randomUUID().toString())
                        .initialBalance(request.initialBalance())
                        .currency(request.currency())
                        .build()
        );
    }

    @PostMapping("/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO request) {
        return commandGateway.send(
                CreditAccountCommand.builder()
                        .accountId(request.id())
                        .amount(request.amount())
                        .build()
        );
    }

    @PostMapping("/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO request) {
        return commandGateway.send(
                DebitAccountCommand.builder()
                        .accountId(request.accountId())
                        .amount(request.amount())
                        .build()
        );
    }

    @PutMapping("/updateStatus")
    public CompletableFuture<String> updateAccountStatus(@RequestBody UpdateAccountStatusRequestDTO request) {
        return commandGateway.send(
                UpdateAccountStatusCommand.builder()
                        .accountId(request.accountId())
                        .status(request.status())
                        .build()
        );
    }

    @GetMapping("/events/{accountId}")
    public Stream eventStore(@PathVariable String accountId) {
        return eventStore.readEvents(accountId).asStream();
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception exception) {
        return exception.getMessage();
    }
}
