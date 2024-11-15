package jsges.nails.service.organizacion;

import jsges.nails.dto.Organizacion.ClienteDTO;
import jsges.nails.domain.organization.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {
    public List<Customer> listar();

    public Customer buscarPorId(Integer id);

    public Customer guardar(Customer customer);

    public void eliminar(Customer customer);

      public List<Customer> listar(String consulta);

    public Page<Customer> getClientes(Pageable pageable);

    public Page<ClienteDTO> findPaginated(Pageable pageable, List<ClienteDTO> clientes);
}
