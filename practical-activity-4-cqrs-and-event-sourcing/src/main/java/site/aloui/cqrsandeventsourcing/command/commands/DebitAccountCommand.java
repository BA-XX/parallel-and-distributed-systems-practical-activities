package site.aloui.cqrsandeventsourcing.command.commands;

import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
public record DebitAccountCommand(
        @TargetAggregateIdentifier String accountId,
        double amount
) {
}
