package jsges.nails.service.organizacion;

import jsges.nails.dto.Organizacion.ClienteDTO;
import jsges.nails.domain.organization.Customer;
import jsges.nails.repository.organizacion.ClienteRepository;
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
public class ClienteService implements IClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);
    @Override
    public List<Customer> listar() {
        return clienteRepository.buscarNoEliminados();
    }

    @Override
    public Customer buscarPorId(Integer id) {
        Customer model = clienteRepository.findById(id).orElse(null);
        return model;
    }

    @Override
    public Customer guardar(Customer customer) {
        return clienteRepository.save(customer);
    }

    @Override
    public void eliminar(Customer customer) {
          clienteRepository.save(customer);
    }

    @Override
    public List<Customer> listar(String consulta) {
         return clienteRepository.buscarNoEliminados(consulta);
    }

    public Page<Customer> getClientes(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Page<ClienteDTO> findPaginated(Pageable pageable, List<ClienteDTO> clientes) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ClienteDTO> list;
        if (clientes.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, clientes.size());
            list = clientes.subList(startItem, toIndex);
        }

        Page<ClienteDTO> bookPage
                = new PageImpl<ClienteDTO>(list, PageRequest.of(currentPage, pageSize), clientes.size());

        return bookPage;
    }


}
