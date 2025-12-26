package site.aloui.bankAccountService.mappers;

import site.aloui.bankAccountService.dtos.CustomerResponseDTO;
import site.aloui.bankAccountService.entities.Customer;

public interface ICustomerMapper {

    CustomerResponseDTO toCustomerResponseDTO(Customer customer);
    Customer toEntity(CustomerResponseDTO customerResponseDTO);

}
