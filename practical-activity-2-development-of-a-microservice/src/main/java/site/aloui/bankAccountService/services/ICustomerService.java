package site.aloui.bankAccountService.services;

import site.aloui.bankAccountService.dtos.CustomerResponseDTO;

import java.util.List;

public interface ICustomerService {

    List<CustomerResponseDTO> getCustomers();

}
