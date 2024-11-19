package jsges.nails.service.services;

import jsges.nails.dto.items.SalesItemDTO;
import jsges.nails.dto.services.ServiceTypeDTO;
import jsges.nails.domain.services.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServiceTypeService {

    public List<ServiceTypeDTO> getModels();

    public ServiceTypeDTO getModelById(Integer id);

    public List<ServiceTypeDTO> getModelByRequest(String request);

    public Page<ServiceTypeDTO> findPaginated(Pageable pageable, List<ServiceTypeDTO> models);

    public ServiceTypeDTO createModel(ServiceType model);

    public ServiceTypeDTO updateModel(ServiceTypeDTO editedModelDTO, ServiceType newModel);

    public void deleteModel(ServiceTypeDTO model);
}
