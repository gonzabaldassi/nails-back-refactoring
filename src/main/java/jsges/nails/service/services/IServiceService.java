package jsges.nails.service.services;

import jsges.nails.dto.services.ServiceDTO;
import jsges.nails.domain.services.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServiceService {
    public List<Service> listar();

    public Service buscarPorId(Integer id);

    public Service guardar(Service model);

    public Page<ServiceDTO> findPaginated(Pageable pageable, List<ServiceDTO> servicios);

    public Page<Service> getServicios(Pageable pageable);

    public List<Service> listar(String consulta);

}
