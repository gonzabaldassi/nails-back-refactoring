package jsges.nails.mapper.services;

import jsges.nails.domain.services.ServiceItem;
import jsges.nails.dto.services.ServiceItemDTO;

public class ServiceItemMapper {

    public ServiceItemDTO convertServiceItemToDto(ServiceItem serviceItem) {
        ServiceItemDTO serviceItemDTO = new ServiceItemDTO();

        serviceItemDTO.setId(serviceItem.getId());
        serviceItemDTO.setObservation(serviceItem.getObservation());
        serviceItemDTO.setPrice(serviceItem.getPrice());

        serviceItemDTO.setServiceTypeId(serviceItem.getServiceType().getId());
        serviceItemDTO.setServiceId(serviceItem.getService().getId());

        return serviceItemDTO;
    }

    /*public ServiceItem convertDtoToServiceItem(ServiceItemDTO serviceItemDTO) {
        ServiceItem serviceItem = new ServiceItem();

        serviceItem.setId(serviceItemDTO.getId());
        serviceItem.setObservation(serviceItemDTO.getObservation());
        serviceItem.setPrice(serviceItemDTO.getPrice());

        serviceItem.setServiceType();
        serviceItem.setService();
    }*/
}
