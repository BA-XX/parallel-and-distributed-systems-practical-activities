package site.aloui.cqrsandeventsourcing.command.dtos;

import lombok.Builder;

@Builder
public record DebitAccountRequestDTO(
        String accountId,
        double amount
) {
}
