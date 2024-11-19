package jsges.nails.service.services;
import jsges.nails.domain.services.ServiceClass;
import jsges.nails.domain.services.ServiceItem;
import jsges.nails.dto.services.ServiceClassDTO;
import jsges.nails.mapper.services.ServiceClassMapper;
import jsges.nails.repository.services.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ServiceClassImplementation implements IServiceClassService {

    @Autowired
    private ServiceRepository modelRepository;

    @Autowired
    private ServiceClassMapper modelMapper;

    @Autowired
    private ServiceItemImplementation serviceItemImplementation;

    @Override
    public List<ServiceClassDTO> getModels(){
        return modelRepository.findByState0()
                .stream()
                .map(modelMapper::convertModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceClassDTO getModelById(Integer id) {
        ServiceClass model = modelRepository.findByIdAndState(id,0);

        if (model == null) {
            return null;
        }

        return modelMapper.convertModelToDto(model);
    }

    @Override
    public Page<ServiceClassDTO> findPaginated(Pageable pageable, List<ServiceClassDTO> models) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ServiceClassDTO> list;
        if (models.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, models.size());
            list = models.subList(startItem, toIndex);
        }

        Page<ServiceClassDTO> bookPage
                = new PageImpl<ServiceClassDTO>(list, PageRequest.of(currentPage, pageSize), models.size());

        return bookPage;
    }

    @Override
    public ServiceClassDTO createModel(ServiceClass model){

        ServiceClass newModel = modelRepository.save(model);

        for(ServiceItem serviceItem : model.getServiceItems()){
            serviceItem.setService(newModel);
            serviceItemImplementation.createModel(serviceItem);
        }
        return modelMapper.convertModelToDto(newModel);
    }

    @Override
    public void deleteModel(ServiceClassDTO model){
        ServiceClass existingModel = modelRepository.findById(model.getId()).orElse(null);
        if (existingModel != null) {
            for (ServiceItem serviceItem: existingModel.getServiceItems()){
                serviceItemImplementation.deleteModel(serviceItem.getId());
            }
            existingModel.setState(1);
            modelRepository.save(existingModel);
        }
    }
}
