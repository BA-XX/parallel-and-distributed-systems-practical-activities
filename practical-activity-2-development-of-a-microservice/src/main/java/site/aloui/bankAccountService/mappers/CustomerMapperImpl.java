package site.aloui.bankAccountService.mappers;

import org.springframework.stereotype.Component;
import site.aloui.bankAccountService.dtos.CustomerResponseDTO;
import site.aloui.bankAccountService.entities.Customer;

@Component
public class CustomerMapperImpl implements ICustomerMapper {
    @Override
    public CustomerResponseDTO toCustomerResponseDTO(Customer customer) {
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .bankAccounts(customer.getBankAccounts())
                .build();
    }

    @Override
    public Customer toEntity(CustomerResponseDTO customerResponseDTO) {
        return Customer.builder()
                .id(customerResponseDTO.getId())
                .name(customerResponseDTO.getName())
                .bankAccounts(customerResponseDTO.getBankAccounts())
                .build();
    }
}
