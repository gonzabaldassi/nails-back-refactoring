package jsges.nails.mapper.organization;

import jsges.nails.domain.organization.Customer;
import jsges.nails.dto.organization.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDTO convertModelToDto(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setBusinessName(customer.getBusinessName());
        customerDTO.setLetter(customer.getLetter());
        customerDTO.setContact(customer.getContact());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setStartDate(customer.getStartDate());
        customerDTO.setDateOfBirth(customer.getDateOfBirth());

        return customerDTO;
    }
}

