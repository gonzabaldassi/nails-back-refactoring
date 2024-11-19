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
}
