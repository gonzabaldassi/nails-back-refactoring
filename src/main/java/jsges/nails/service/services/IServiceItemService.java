package jsges.nails.service.services;

import jsges.nails.domain.services.ServiceItem;
import jsges.nails.dto.services.ServiceItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServiceItemService {
    public List<ServiceItemDTO> getModels();

    public ServiceItemDTO getModelById(Integer id);

    public List<ServiceItemDTO> getModelByService(Integer id);

    public ServiceItemDTO createModel(ServiceItem model);

    public void deleteModel(Integer id);

    /*public Page<ServiceItem> findPaginated(Pageable pageable, List<ServiceItem> servicios);

    public Page<ServiceItem> getItemServicios(Pageable pageable);*/
}
