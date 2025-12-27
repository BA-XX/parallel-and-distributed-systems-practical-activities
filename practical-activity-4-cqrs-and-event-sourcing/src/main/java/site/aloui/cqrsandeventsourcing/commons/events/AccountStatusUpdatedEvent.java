package site.aloui.cqrsandeventsourcing.commons.events;

import lombok.Builder;
import site.aloui.cqrsandeventsourcing.commons.enums.AccountStatus;

@Builder
public record AccountStatusUpdatedEvent(
        String accountId,
        AccountStatus status
) {
}
