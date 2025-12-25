package site.aloui.bankAccountService.services;

import site.aloui.bankAccountService.dtos.BankAccountRequestDTO;
import site.aloui.bankAccountService.dtos.BankAccountResponseDTO;

public interface IAccountService {

    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO);
}
