package site.aloui.cqrsandeventsourcing.command.commands;

import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import site.aloui.cqrsandeventsourcing.commons.enums.AccountStatus;

@Builder
public record UpdateAccountStatusCommand(
        @TargetAggregateIdentifier String accountId,
        AccountStatus status
) {
}
