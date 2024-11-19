package jsges.nails.service.services;

import jsges.nails.domain.items.SalesItem;
import jsges.nails.dto.items.SalesItemDTO;
import jsges.nails.dto.services.ServiceTypeDTO;
import jsges.nails.domain.services.ServiceType;
import jsges.nails.mapper.services.ServiceTypeMapper;
import jsges.nails.repository.services.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceTypeImplementation implements IServiceTypeService {

    @Autowired
    private ServiceTypeRepository modelRepository;

    @Autowired
    private ServiceTypeMapper modelMapper;

    @Override
    public List<ServiceTypeDTO> getModels(){
        return modelRepository.findByState0()
                .stream()
                .map(modelMapper::convertModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceTypeDTO getModelById(Integer id) {
        ServiceType model = modelRepository.findByIdAndState(id,0);

        if (model == null) {
            return null;
        }

        return modelMapper.convertModelToDto(model);
    }

    @Override
    public List<ServiceTypeDTO> getModelByRequest(String request) {

        return modelRepository.findByRequest(request)
                .stream()
                .map(modelMapper::convertModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ServiceTypeDTO> findPaginated(Pageable pageable, List<ServiceTypeDTO> models) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ServiceTypeDTO> list;
        if (models.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, models.size());
            list = models.subList(startItem, toIndex);
        }

        Page<ServiceTypeDTO> bookPage
                = new PageImpl<ServiceTypeDTO>(list, PageRequest.of(currentPage, pageSize), models.size());

        return bookPage;
    }

    @Override
    public ServiceTypeDTO createModel(ServiceType model){

        ServiceType newModel = modelRepository.save(model);
        return modelMapper.convertModelToDto(newModel);
    }

    @Override
    public ServiceTypeDTO updateModel(ServiceTypeDTO editedModelDTO, ServiceType newModel){
        ServiceType editedModel = modelRepository.findById(editedModelDTO.getId()).orElse(null);

        if (editedModel == null) {
            return null;
        }

        editedModel.setDenomination(newModel.getDenomination());
        editedModel.setServiceItems(newModel.getServiceItems());
        editedModel.setDetail(newModel.getDetail());
        editedModel.setCode(newModel.getCode());

        ServiceType updatedModel = modelRepository.save(editedModel);

        return modelMapper.convertModelToDto(updatedModel);
    }


    @Override
    public void deleteModel(ServiceTypeDTO model){
        ServiceType existingModel = modelRepository.findById(model.getId()).orElse(null);
        if (existingModel != null) {
            existingModel.setState(1);
            modelRepository.save(existingModel);
        }
    }

    /*
    @Autowired
    private ServiceTypeRepository modelRepository;
    private static final Logger logger = LoggerFactory.getLogger(ServiceTypeImplementation.class);

    @Override
    public List<ServiceType> listar() {
        return modelRepository.buscarNoEliminados();
    }

    @Override
    public ServiceType buscarPorId(Integer id) {
        return modelRepository.findById(id).orElse(null);
    }



    @Override
    public ServiceType guardar(ServiceType model) {
        return modelRepository.save(model);
    }


    @Override
    public ServiceType newModel(ServiceTypeDTO modelDTO) {
        ServiceType model =  new ServiceType();
        model.setDenominacion(modelDTO.denomination);
        return guardar(model);
    }


    @Override
    public void eliminar(ServiceType model) {

        modelRepository.save(model);
    }

    @Override
    public List<ServiceType> listar(String consulta) {
        //logger.info("service " +consulta);
        return modelRepository.buscarNoEliminados(consulta);
    }

    @Override
    public Page<ServiceType> getTiposServicios(Pageable pageable) {
        return  modelRepository.findAll(pageable);
    }

    public List<ServiceType> buscar(String consulta) {
        return modelRepository.buscarExacto(consulta);
    }


    @Override
    public Page<ServiceType> findPaginated(Pageable pageable, List<ServiceType>lineas) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ServiceType> list;
        if (lineas.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, lineas.size());
            list = lineas.subList(startItem, toIndex);
        }

        Page<ServiceType> bookPage
                = new PageImpl<ServiceType>(list, PageRequest.of(currentPage, pageSize), lineas.size());

        return bookPage;
    }*/

}
