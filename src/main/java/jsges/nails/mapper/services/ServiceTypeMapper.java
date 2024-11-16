package jsges.nails.mapper.services;

import jsges.nails.domain.services.ServiceType;
import jsges.nails.dto.services.ServiceTypeDTO;

public class ServiceTypeMapper {

    public ServiceTypeDTO convertServiceTypeToDto(ServiceType serviceType) {
        ServiceTypeDTO serviceTypeDTO = new ServiceTypeDTO();

        serviceTypeDTO.setId(serviceType.getId());
        serviceTypeDTO.setDenomination(serviceType.getDenomination());

        return serviceTypeDTO;
    }

    public ServiceType convertDtoToServiceType(ServiceTypeDTO serviceTypeDTO) {
        ServiceType serviceType = new ServiceType();

        serviceType.setId(serviceTypeDTO.getId());
        serviceType.setDenomination(serviceTypeDTO.getDenomination());

        return serviceType;
    }
}
