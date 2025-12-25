package site.aloui.bankAccountService.mappers;

import site.aloui.bankAccountService.dtos.BankAccountRequestDTO;
import site.aloui.bankAccountService.dtos.BankAccountResponseDTO;
import site.aloui.bankAccountService.entities.BankAccount;

public interface IBankAccountMapper {

    BankAccountResponseDTO toResponseDto(BankAccount account);
    BankAccount toEntity(BankAccountRequestDTO requestDTO);
}
