package site.aloui.cqrsandeventsourcing.commons.events;

import lombok.Builder;

@Builder
public record AccountActivatedEvent(
        String accountId
) {
}
