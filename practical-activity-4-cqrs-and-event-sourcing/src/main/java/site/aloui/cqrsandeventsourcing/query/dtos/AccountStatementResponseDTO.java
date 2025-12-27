package site.aloui.cqrsandeventsourcing.query.dtos;

import lombok.Builder;
import site.aloui.cqrsandeventsourcing.query.entities.Account;
import site.aloui.cqrsandeventsourcing.query.entities.AccountOperation;

import java.util.List;

@Builder
public record AccountStatementResponseDTO(
        Account account,
        List<AccountOperation> operations
) {
}
