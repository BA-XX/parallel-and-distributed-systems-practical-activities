package site.aloui.cqrsandeventsourcing.command.commands;

import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
public record AddAccountCommand(

       @TargetAggregateIdentifier String id,
        double initialBalance,
        String currency
) {
}
