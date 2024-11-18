package jsges.nails.service.services;

import jsges.nails.dto.services.ServiceClassDTO;
import jsges.nails.domain.services.ServiceClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServiceClassService {

    public List<ServiceClassDTO> getModels();

    public ServiceClassDTO getModelById(Integer id);

    public Page<ServiceClassDTO> findPaginated(Pageable pageable, List<ServiceClassDTO> models);

    public ServiceClassDTO createModel(ServiceClass model);

    /*public ServiceClassDTO updateModel(ServiceClassDTO editedModelDTO, ServiceClass newModel);*/

    public void deleteModel(ServiceClassDTO model);


    /*public List<Service> listar();

    public Service buscarPorId(Integer id);

    public Service guardar(Service model);

    public Page<ServiceDTO> findPaginated(Pageable pageable, List<ServiceDTO> servicios);

    public Page<Service> getServicios(Pageable pageable);

    public List<Service> listar(String consulta);*/

}
