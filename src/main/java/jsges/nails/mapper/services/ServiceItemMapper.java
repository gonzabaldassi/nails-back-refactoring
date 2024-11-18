package jsges.nails.mapper.services;

import jsges.nails.domain.services.ServiceItem;
import jsges.nails.dto.services.ServiceItemDTO;
import org.springframework.stereotype.Component;

@Component
public class ServiceItemMapper {

    public ServiceItemDTO convertModelToDto(ServiceItem serviceItem) {
        ServiceItemDTO serviceItemDTO = new ServiceItemDTO();

        serviceItemDTO.setId(serviceItem.getId());
        serviceItemDTO.setObservation(serviceItem.getObservation());
        serviceItemDTO.setPrice(serviceItem.getPrice());

        serviceItemDTO.setServiceTypeId(serviceItem.getServiceType().getId());
        serviceItemDTO.setServiceId(serviceItem.getService().getId());

        return serviceItemDTO;
    }
}
