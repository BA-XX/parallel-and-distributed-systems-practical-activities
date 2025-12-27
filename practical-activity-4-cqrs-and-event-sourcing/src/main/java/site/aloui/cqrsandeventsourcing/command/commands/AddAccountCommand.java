package site.aloui.cqrsandeventsourcing.command.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Getter
@AllArgsConstructor
public class AddAccountCommand {

    @TargetAggregateIdentifier
    private String id;
    private double initialBalance;
    private String currency;

}
