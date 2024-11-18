package jsges.nails.mapper.services;

import jsges.nails.domain.services.ServiceClass;
import jsges.nails.dto.services.ServiceClassDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ServiceClassMapper {

    @Autowired
    private ServiceItemMapper serviceItemMapper;

    public ServiceClassDTO convertModelToDto(ServiceClass service) {
        ServiceClassDTO serviceDTO = new ServiceClassDTO();

        serviceDTO.setId(service.getId());
        serviceDTO.setRegistrationTimestamp(service.getRegistrationTimestamp());
        serviceDTO.setCompletionTimestamp(service.getCompletionTimestamp());
        serviceDTO.setTotal(service.getTotal());

        serviceDTO.setCustomerId(service.getCustomer().getId());
        serviceDTO.setCustomerBusinessName(service.getCustomer().getBusinessName());

        serviceDTO.setServiceItems(service.getServiceItems()
                .stream()
                .map(serviceItemMapper::convertModelToDto)
                .collect(Collectors.toList()));
        return serviceDTO;
    }
}
