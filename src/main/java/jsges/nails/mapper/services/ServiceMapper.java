package jsges.nails.mapper.services;

import jsges.nails.domain.services.Service;
import jsges.nails.dto.services.ServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class ServiceMapper {

    @Autowired
    private ServiceItemMapper serviceItemMapper;

    public ServiceDTO convertServiceToDTO(Service service) {
        ServiceDTO serviceDTO = new ServiceDTO();

        serviceDTO.setId(service.getId());
        serviceDTO.setRegistrationTimestamp(service.getRegistrationTimestamp());
        serviceDTO.setCompletionTimestamp(service.getCompletionTimestamp());
        serviceDTO.setTotal(service.getTotal());

        serviceDTO.setCustomerId(service.getCustomer().getId());
        serviceDTO.setCustomerBusinessName(service.getCustomer().getBusinessName());

        serviceDTO.setServiceItems(service.getServiceItems()
                .stream()
                .map(serviceItemMapper::convertServiceItemToDto)
                .collect(Collectors.toList()));
        return serviceDTO;
    }

    /*public Service convertDTOToService(ServiceDTO serviceDTO) {
        Service service = new Service();

        service.setId(serviceDTO.getId());
        service.setRegistrationTimestamp(serviceDTO.getRegistrationTimestamp());
        service.setCompletionTimestamp(serviceDTO.getCompletionTimestamp());
        service.setTotal(serviceDTO.getTotal());

        service.setCustomer();

        service.setServiceItems();
    }*/
}
