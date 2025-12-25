package site.aloui.bankAccountService.mappers;

import org.springframework.stereotype.Component;
import site.aloui.bankAccountService.dtos.BankAccountRequestDTO;
import site.aloui.bankAccountService.dtos.BankAccountResponseDTO;
import site.aloui.bankAccountService.entities.BankAccount;

@Component
public class BankAccountMapperImpl implements IBankAccountMapper {

    @Override
    public BankAccountResponseDTO toResponseDto(BankAccount account) {
        return BankAccountResponseDTO.builder()
                .id(account.getId())
                .createdAt(account.getCreatedAt())
                .balance(account.getBalance())
                .type(account.getType())
                .currency(account.getCurrency())
                .build();
    }

    @Override
    public BankAccount toEntity(BankAccountRequestDTO requestDTO) {
        return BankAccount.builder()
                .type(requestDTO.getType())
                .currency(requestDTO.getCurrency())
                .balance(requestDTO.getBalance())
                .build();
    }
}
