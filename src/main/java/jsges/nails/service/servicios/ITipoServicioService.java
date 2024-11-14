package jsges.nails.service.servicios;

import jsges.nails.dto.servicios.TipoServicioDTO;
import jsges.nails.domain.services.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITipoServicioService {

    public List<ServiceType> listar();

    public ServiceType buscarPorId(Integer id);

    public ServiceType guardar(ServiceType model);

    public void eliminar(ServiceType model);

    public List<ServiceType> listar(String consulta);

    public Page<ServiceType> getTiposServicios(Pageable pageable);

    public Page<ServiceType> findPaginated(Pageable pageable, List<ServiceType> serviceTypes);


    public List<ServiceType> buscar(String consulta);

    public ServiceType newModel(TipoServicioDTO model);
}
