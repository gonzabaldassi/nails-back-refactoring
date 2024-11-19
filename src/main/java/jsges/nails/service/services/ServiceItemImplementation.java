package jsges.nails.service.services;

import jsges.nails.domain.services.ServiceItem;
import jsges.nails.dto.services.ServiceItemDTO;
import jsges.nails.mapper.services.ServiceItemMapper;
import jsges.nails.repository.services.ServiceItemRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ServiceItemImplementation implements IServiceItemService {

    @Autowired
    private ServiceItemRepository modelRepository;

    @Autowired
    private ServiceItemMapper modelMapper;

    @Override
    public List<ServiceItemDTO> getModels(){
        return modelRepository.findByState0()
                .stream()
                .map(modelMapper::convertModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceItemDTO getModelById(Integer id) {
        ServiceItem model = modelRepository.findByIdAndState(id,0);

        if (model == null) {
            return null;
        }

        return modelMapper.convertModelToDto(model);
    }

    @Override
    public List<ServiceItemDTO> getModelByService(Integer id) {;
        return modelRepository.findByService(id)
                .stream()
                .map(modelMapper::convertModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceItemDTO createModel(ServiceItem model){

        ServiceItem newModel = modelRepository.save(model);
        return modelMapper.convertModelToDto(newModel);
    }

    @Override
    public void deleteModel(Integer id){
        ServiceItem existingModel = modelRepository.findById(id).orElse(null);
        if (existingModel != null) {
            existingModel.setState(1);
            modelRepository.save(existingModel);
        }
    }
}
