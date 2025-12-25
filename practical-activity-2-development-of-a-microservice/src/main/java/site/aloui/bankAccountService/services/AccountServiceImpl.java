package site.aloui.bankAccountService.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.aloui.bankAccountService.dtos.BankAccountRequestDTO;
import site.aloui.bankAccountService.dtos.BankAccountResponseDTO;
import site.aloui.bankAccountService.entities.BankAccount;
import site.aloui.bankAccountService.mappers.IBankAccountMapper;
import site.aloui.bankAccountService.repositories.BankAccountRepository;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private BankAccountRepository bankAccountRepository;
    private IBankAccountMapper bankAccountMapper;

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO) {

        BankAccount account = bankAccountMapper.toEntity(bankAccountRequestDTO);

        BankAccount savedAccount = bankAccountRepository.save(account);

        return bankAccountMapper.toResponseDto(savedAccount);
    }
}
