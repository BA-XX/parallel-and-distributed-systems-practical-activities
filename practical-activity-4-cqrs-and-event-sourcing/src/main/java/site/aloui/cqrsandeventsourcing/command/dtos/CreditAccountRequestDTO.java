package site.aloui.cqrsandeventsourcing.command.dtos;

import lombok.Builder;

@Builder
public record CreditAccountRequestDTO(
        String id,
        double amount
) {
}
