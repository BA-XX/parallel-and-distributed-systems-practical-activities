package site.aloui.cqrsandeventsourcing.commons.events;

import lombok.Builder;

@Builder
public record AccountCreditedEvent(
        String accountId,
        double amount
) {
}
