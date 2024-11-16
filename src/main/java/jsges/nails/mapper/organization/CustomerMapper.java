package jsges.nails.mapper.organization;

import jsges.nails.domain.organization.Customer;
import jsges.nails.dto.organization.CustomerDTO;

public class CustomerMapper {

    public CustomerDTO convertCustomerToDto(Customer customer) {
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

    public Customer convertDtoToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();

        customer.setId(customerDTO.getId());
        customer.setBusinessName(customerDTO.getBusinessName());
        customer.setLetter(customerDTO.getLetter());
        customer.setContact(customerDTO.getContact());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setEmail(customerDTO.getEmail());
        customer.setStartDate(customerDTO.getStartDate());
        customer.setDateOfBirth(customerDTO.getDateOfBirth());

        return customer;
    }
}
