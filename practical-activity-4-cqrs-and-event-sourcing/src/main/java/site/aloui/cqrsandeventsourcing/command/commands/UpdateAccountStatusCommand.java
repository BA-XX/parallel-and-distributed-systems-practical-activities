package site.aloui.cqrsandeventsourcing.command.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import site.aloui.cqrsandeventsourcing.commons.enums.AccountStatus;

@Builder
@Getter
@AllArgsConstructor
public class UpdateAccountStatusCommand {
    @TargetAggregateIdentifier
    private String accountId;
    private AccountStatus status;

}
