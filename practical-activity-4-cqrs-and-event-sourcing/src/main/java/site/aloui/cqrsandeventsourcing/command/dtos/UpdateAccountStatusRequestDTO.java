package site.aloui.cqrsandeventsourcing.command.dtos;

import lombok.Builder;
import site.aloui.cqrsandeventsourcing.commons.enums.AccountStatus;

@Builder
public record UpdateAccountStatusRequestDTO(
        String accountId,
        AccountStatus status
) {
}
