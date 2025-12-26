package site.aloui.bankAccountService.services;

import site.aloui.bankAccountService.dtos.BankAccountRequestDTO;
import site.aloui.bankAccountService.dtos.BankAccountResponseDTO;

import java.util.List;

public interface IAccountService {

    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO);

    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountRequestDTO);

    List<BankAccountResponseDTO> getAllAccounts();

    BankAccountResponseDTO getAccountById(String id);

    void deleteAccountById(String id);
}
