package site.aloui.bankAccountService.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.aloui.bankAccountService.dtos.BankAccountRequestDTO;
import site.aloui.bankAccountService.dtos.BankAccountResponseDTO;
import site.aloui.bankAccountService.entities.BankAccount;
import site.aloui.bankAccountService.mappers.IBankAccountMapper;
import site.aloui.bankAccountService.repositories.BankAccountRepository;

import java.util.List;

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

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccount) {

        BankAccount account = bankAccountRepository.findById(id).orElseThrow();

        if (bankAccount.getBalance() != null) account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCurrency() != null) account.setCurrency(bankAccount.getCurrency());
        if (bankAccount.getType() != null) account.setType(bankAccount.getType());

        return bankAccountMapper.toResponseDto(
                bankAccountRepository.save(account)
        );
    }

    @Override
    public List<BankAccountResponseDTO> getAllAccounts() {
        return bankAccountRepository.findAll().stream().map(
                (bankAccount) -> bankAccountMapper.toResponseDto(bankAccount)
        ).toList();
    }

    @Override
    public BankAccountResponseDTO getAccountById(String id) {
        return bankAccountMapper.toResponseDto(
                bankAccountRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException(
                                        String.format("Account %s not found", id)
                                )
                        ));
    }

    @Override
    public void deleteAccountById(String id) {
        bankAccountRepository.deleteById(id);
    }
}
