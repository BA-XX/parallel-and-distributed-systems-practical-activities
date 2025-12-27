package site.aloui.cqrsandeventsourcing.command.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Getter
@AllArgsConstructor
public class CreditAccountCommand {

    @TargetAggregateIdentifier
    private String accountId;
    private double amount;

}
