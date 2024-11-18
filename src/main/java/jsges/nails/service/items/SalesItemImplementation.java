package jsges.nails.service.items;

import jsges.nails.dto.items.SalesItemDTO;
import jsges.nails.domain.items.SalesItem;
import jsges.nails.mapper.items.SalesItemMapper;
import jsges.nails.repository.items.SalesItemRepository;
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
public class SalesItemImplementation implements ISalesItemService {

    @Autowired
    private SalesItemRepository modelRepository;

    @Autowired
    private SalesItemMapper modelMapper;

    @Override
    public List<SalesItemDTO> getModels(){
        return modelRepository.findByState0()
                .stream()
                .map(modelMapper::convertModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SalesItemDTO getModelById(Integer id) {
        SalesItem model = modelRepository.findByIdAndState(id,0);

        if (model == null) {
            return null;
        }

        return modelMapper.convertModelToDto(model);
    }

    @Override
    public List<SalesItemDTO> getModelByRequest(String request) {

        return modelRepository.findByRequest(request)
                .stream()
                .map(modelMapper::convertModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SalesItemDTO> findPaginated(Pageable pageable, List<SalesItemDTO> serviceItems) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<SalesItemDTO> list;
        if (serviceItems.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, serviceItems.size());
            list = serviceItems.subList(startItem, toIndex);
        }

        Page<SalesItemDTO> bookPage
                = new PageImpl<SalesItemDTO>(list, PageRequest.of(currentPage, pageSize), serviceItems.size());

        return bookPage;
    }

    @Override
    public SalesItemDTO createModel(SalesItem model){

        SalesItem newModel = modelRepository.save(model);
        return modelMapper.convertModelToDto(newModel);
    }

    @Override
    public SalesItemDTO updateModel(SalesItemDTO editedModelDTO, SalesItem newModel){
        SalesItem editedModel = modelRepository.findById(editedModelDTO.getId()).orElse(null);

        if (editedModel == null) {
            return null;
        }

        editedModel.setDenomination(newModel.getDenomination());
        editedModel.setObservation(newModel.getObservation());
        editedModel.setLine(newModel.getLine());

        SalesItem updatedModel = modelRepository.save(editedModel);

        return modelMapper.convertModelToDto(updatedModel);
    }

    @Override
    public void deleteModel(SalesItemDTO model){
        SalesItem existingModel = modelRepository.findById(model.getId()).orElse(null);
        if (existingModel != null) {
            existingModel.setState(1);
        }

    }


    /*


    @Autowired
    private SalesItemRepository modelRepository;
    private static final Logger logger = LoggerFactory.getLogger(SalesItemImplementation.class);


    @Override
    public List<SalesItem> listar() {
        return modelRepository.buscarNoEliminados();
    }

    @Override
    public SalesItem buscarPorId(Integer id) {
        return modelRepository.findById(id).orElse(null);
    }

    @Override
    public SalesItem guardar(SalesItem model) {
        return modelRepository.save(model);
    }

    @Override
    public void eliminar(SalesItem model) {
        modelRepository.save(model);
    }

    @Override
    public List<SalesItem> listar(String consulta) {
        //logger.info("service " +consulta);
        return modelRepository.buscarNoEliminados(consulta);
    }

    @Override
    public Page<SalesItem> getArticulos(Pageable pageable) {
        return  modelRepository.findAll(pageable);
    }

    @Override
    public Page<SalesItemDTO> findPaginated(Pageable pageable, List<SalesItemDTO> listado) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<SalesItemDTO> list;
        if (listado.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listado.size());
            list = listado.subList(startItem, toIndex);
        }

        Page<SalesItemDTO> bookPage
                = new PageImpl<SalesItemDTO>(list, PageRequest.of(currentPage, pageSize), listado.size());

        return bookPage;
    }


*/
}
