package site.aloui.cqrsandeventsourcing.command.dtos;

import lombok.*;

@Builder
public record AddAccountRequestDTO(
        double initialBalance,
        String currency
) {
}
