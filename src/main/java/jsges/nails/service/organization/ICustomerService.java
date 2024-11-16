package jsges.nails.service.organization;

import jsges.nails.dto.organization.CustomerDTO;
import jsges.nails.domain.organization.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    public List<Customer> listar();

    public Customer buscarPorId(Integer id);

    public Customer guardar(Customer customer);

    public void eliminar(Customer customer);

      public List<Customer> listar(String consulta);

    public Page<Customer> getClientes(Pageable pageable);

    public Page<CustomerDTO> findPaginated(Pageable pageable, List<CustomerDTO> clientes);
}
