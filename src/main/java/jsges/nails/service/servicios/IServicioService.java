package jsges.nails.service.servicios;

import jsges.nails.dto.servicios.ServicioDTO;
import jsges.nails.domain.services.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServicioService {
    public List<Service> listar();

    public Service buscarPorId(Integer id);

    public Service guardar(Service model);

    public Page<ServicioDTO> findPaginated(Pageable pageable,List<ServicioDTO> servicios);

    public Page<Service> getServicios(Pageable pageable);

    public List<Service> listar(String consulta);

}
