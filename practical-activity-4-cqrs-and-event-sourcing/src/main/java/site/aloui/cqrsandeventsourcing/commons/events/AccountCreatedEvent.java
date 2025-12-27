package site.aloui.cqrsandeventsourcing.commons.events;

import lombok.Builder;
import site.aloui.cqrsandeventsourcing.commons.enums.AccountStatus;

@Builder
public record AccountCreatedEvent(
        String accountId,
        double initialBalance,
        AccountStatus status,
        String currency
) {
}
