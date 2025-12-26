package site.aloui.bankAccountService.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import site.aloui.bankAccountService.dtos.CustomerResponseDTO;
import site.aloui.bankAccountService.mappers.ICustomerMapper;
import site.aloui.bankAccountService.repositories.CustomerRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private CustomerRepository customerRepository;
    private ICustomerMapper customerMapper;

    @Override
    public List<CustomerResponseDTO> getCustomers() {
        return customerRepository.findAll().stream().map(
                customer -> customerMapper.toCustomerResponseDTO(customer)
        ).toList();
    }
}
