package site.aloui.cqrsandeventsourcing.query.controllers;

import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import site.aloui.cqrsandeventsourcing.query.dtos.AccountStatementResponseDTO;
import site.aloui.cqrsandeventsourcing.query.entities.Account;
import site.aloui.cqrsandeventsourcing.query.entities.AccountOperation;
import site.aloui.cqrsandeventsourcing.query.queries.GetAccountStatementQuery;
import site.aloui.cqrsandeventsourcing.query.queries.GetAllAccountsQuery;
import site.aloui.cqrsandeventsourcing.query.queries.WatchEventQuery;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/queries/accounts")
public class AccountQueryController {

    private QueryGateway queryGateway;

    @GetMapping("/all")
    public CompletableFuture<List<Account>> getAllAccounts() {
        return queryGateway.query(
                new GetAllAccountsQuery(),
                ResponseTypes.multipleInstancesOf(Account.class)
        );
    }

    @PostMapping("/accountStatement/{id}")
    public CompletableFuture<AccountStatementResponseDTO> getAccountStatement(@PathVariable String id) {
        return queryGateway.query(
                new GetAccountStatementQuery(id),
                ResponseTypes.instanceOf(AccountStatementResponseDTO.class)
        );
    }

    @GetMapping(value = "/watch/{accountId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<AccountOperation> watch(@PathVariable String accountId){
        SubscriptionQueryResult<AccountOperation, AccountOperation> result =
                queryGateway.subscriptionQuery(new WatchEventQuery(accountId),
                ResponseTypes.instanceOf(AccountOperation.class),
                ResponseTypes.instanceOf(AccountOperation.class)
        );
        return result.initialResult().concatWith(result.updates());
    }
}
