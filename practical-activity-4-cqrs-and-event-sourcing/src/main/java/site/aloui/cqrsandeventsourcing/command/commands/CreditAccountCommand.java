package site.aloui.cqrsandeventsourcing.command.commands;

import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
public record CreditAccountCommand(
        @TargetAggregateIdentifier String accountId,
        double amount
) {
}
