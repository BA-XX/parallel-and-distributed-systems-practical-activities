package site.aloui.cqrsandeventsourcing.commons.events;

import lombok.Builder;

@Builder
public record AccountDebitedEvent(
        String accountId,
        double amount
) {
}
