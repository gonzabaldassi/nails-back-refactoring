package jsges.nails.service.services;
import jsges.nails.domain.services.ServiceClass;
import jsges.nails.domain.services.ServiceItem;
import jsges.nails.dto.services.ServiceClassDTO;
import jsges.nails.dto.services.ServiceItemDTO;
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

    /*
    @Override
    public ServiceClassDTO updateModel(ServiceClassDTO editedModelDTO, ServiceClass newModel){
        ServiceClass editedModel = modelRepository.findById(editedModelDTO.getId()).orElse(null);

        if (editedModel == null) {
            return null;
        }

        editedModel.setTotal(newModel.getTotal());
        editedModel.setServiceItems(newModel.getServiceItems());
        editedModel.setRegistrationTimestamp(newModel.getRegistrationTimestamp());
        editedModel.setCompletionTimestamp(newModel.getCompletionTimestamp());
        editedModel.setCustomer(newModel.getCustomer());

        ServiceClass updatedModel = modelRepository.save(editedModel);

        return modelMapper.convertModelToDto(updatedModel);
    }*/

    @Override
    public void deleteModel(ServiceClassDTO model){
        ServiceClass existingModel = modelRepository.findById(model.getId()).orElse(null);
        if (existingModel != null) {
            existingModel.setState(1);
        }

    }


    /*
    @Autowired
    private ServiceRepository modelRepository;
    private static final Logger logger = LoggerFactory.getLogger(ServiceImplementation.class);

    @Override
    public List<Service> listar() {
        return modelRepository.buscarNoEliminados();
    }

    @Override
    public Service buscarPorId(Integer id) {
        return modelRepository.findById(id).orElse(null);
    }


    @Override
    public Service guardar(Service model) {
        return modelRepository.save(model);
    }


    @Override
    public Page<Service> getServicios(Pageable pageable) {
        return  modelRepository.findAll(pageable);
    }



    @Override
    public Page<ServiceDTO> findPaginated(Pageable pageable, List<ServiceDTO> listado) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ServiceDTO> list;
        if (listado.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listado.size());
            list = listado.subList(startItem, toIndex);
        }

        Page<ServiceDTO> bookPage
                = new PageImpl<ServiceDTO>(list, PageRequest.of(currentPage, pageSize), listado.size());

        return bookPage;
    }

    @Override
    public List<Service> listar(String consulta) {
        //logger.info("service " +consulta);
        return modelRepository.buscarNoEliminados(consulta);
    }
*/
}
