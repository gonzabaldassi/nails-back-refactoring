package jsges.nails.service.organization;

import jsges.nails.dto.organization.CustomerDTO;
import jsges.nails.domain.organization.Customer;
import jsges.nails.repository.organization.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerImplementation implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomerImplementation.class);
    @Override
    public List<Customer> listar() {
        return customerRepository.buscarNoEliminados();
    }

    @Override
    public Customer buscarPorId(Integer id) {
        Customer model = customerRepository.findById(id).orElse(null);
        return model;
    }

    @Override
    public Customer guardar(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void eliminar(Customer customer) {
          customerRepository.save(customer);
    }

    @Override
    public List<Customer> listar(String consulta) {
         return customerRepository.buscarNoEliminados(consulta);
    }

    public Page<Customer> getClientes(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Page<CustomerDTO> findPaginated(Pageable pageable, List<CustomerDTO> clientes) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<CustomerDTO> list;
        if (clientes.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, clientes.size());
            list = clientes.subList(startItem, toIndex);
        }

        Page<CustomerDTO> bookPage
                = new PageImpl<CustomerDTO>(list, PageRequest.of(currentPage, pageSize), clientes.size());

        return bookPage;
    }


}
