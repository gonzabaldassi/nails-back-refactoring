package jsges.nails.mapper.services;

import jsges.nails.domain.services.ServiceType;
import jsges.nails.dto.services.ServiceTypeDTO;
import org.springframework.stereotype.Component;

@Component
public class ServiceTypeMapper {

    public ServiceTypeDTO convertModelToDto(ServiceType serviceType) {
        ServiceTypeDTO serviceTypeDTO = new ServiceTypeDTO();

        serviceTypeDTO.setId(serviceType.getId());
        serviceTypeDTO.setDenomination(serviceType.getDenomination());

        return serviceTypeDTO;
    }
}
