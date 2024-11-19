package jsges.nails.service.services;

import jsges.nails.domain.services.ServiceItem;
import jsges.nails.dto.services.ServiceItemDTO;

import java.util.List;

public interface IServiceItemService {
    public List<ServiceItemDTO> getModels();

    public ServiceItemDTO getModelById(Integer id);

    public List<ServiceItemDTO> getModelByService(Integer id);

    public ServiceItemDTO createModel(ServiceItem model);

    public void deleteModel(Integer id);
}
