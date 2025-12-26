package site.aloui.bankAccountService.web;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import site.aloui.bankAccountService.dtos.CustomerResponseDTO;
import site.aloui.bankAccountService.services.ICustomerService;

import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerGraphQLController {

    private ICustomerService customerService;

    @QueryMapping
    public List<CustomerResponseDTO> customers() {
        return customerService.getCustomers();
    }
}
