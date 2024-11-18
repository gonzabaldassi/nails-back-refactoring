package jsges.nails.service.organization;

import jsges.nails.domain.items.Line;
import jsges.nails.dto.items.LineDTO;
import jsges.nails.dto.organization.CustomerDTO;
import jsges.nails.domain.organization.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {

    public List<CustomerDTO> getModels();

    public CustomerDTO getModelById(Integer id);

    public List<CustomerDTO> getModelByRequest(String request);

    public Page<CustomerDTO> findPaginated(Pageable pageable, List<CustomerDTO> lines);

    public CustomerDTO createModel(Customer model);

    public CustomerDTO updateModel(CustomerDTO editedModelDTO, Customer newModel);

    public void deleteModel(CustomerDTO model);

    public Integer verifyBusinessName(String businessName);

    public Integer verifyBusinessName(String businessName,Integer id);


    /*public List<Customer> listar();

    public Customer buscarPorId(Integer id);

    public Customer guardar(Customer customer);

    public void eliminar(Customer customer);

      public List<Customer> listar(String consulta);

    public Page<Customer> getClientes(Pageable pageable);

    public Page<CustomerDTO> findPaginated(Pageable pageable, List<CustomerDTO> clientes);*/
}
