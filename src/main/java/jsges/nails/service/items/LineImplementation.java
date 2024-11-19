package jsges.nails.service.items;

import jsges.nails.domain.items.Line;
import jsges.nails.dto.items.LineDTO;
import jsges.nails.mapper.items.LineMapper;
import jsges.nails.repository.items.LineRepository;
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
public class LineImplementation implements ILineService {

    @Autowired
    private LineRepository modelRepository;

    @Autowired
    private LineMapper modelMapper;

    @Override
    public List<LineDTO> getModels(){
        return modelRepository.findByState0()
                .stream()
                .map(modelMapper::convertModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LineDTO getModelById(Integer id) {
        Line line = modelRepository.findByIdAndState(id,0);

        if (line == null) {
            return null;
        }

        return modelMapper.convertModelToDto(line);
    }

    @Override
    public List<LineDTO> getModelByRequest(String request) {

        return modelRepository.findByRequest(request)
                .stream()
                .map(modelMapper::convertModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<LineDTO> findPaginated(Pageable pageable, List<LineDTO> models) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<LineDTO> list;
        if (models.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, models.size());
            list = models.subList(startItem, toIndex);
        }

        Page<LineDTO> bookPage
                = new PageImpl<LineDTO>(list, PageRequest.of(currentPage, pageSize), models.size());

        return bookPage;
    }

    @Override
    public LineDTO createModel(Line model){

        Integer verification = verifyCode(model.getCode());

        if (verification == null){
            Line newModel = modelRepository.save(model);
            return modelMapper.convertModelToDto(newModel);
        }

        Line existingLine = modelRepository.findById(verification).orElse(null);

        if(existingLine.getState() == 1){

            existingLine.setSalesItems(model.getSalesItems());
            existingLine.setDenomination(model.getDenomination());
            existingLine.setDetail(model.getDetail());
            existingLine.setState(0);

            Line updatedLine = modelRepository.save(existingLine);

            return modelMapper.convertModelToDto(updatedLine);
        }
        return null;
    }

    @Override
    public LineDTO updateModel(LineDTO editedModelDTO, Line newModel){
        Integer verification = verifyCode(newModel.getCode(), editedModelDTO.getId());

        Line editedModel = modelRepository.findById(editedModelDTO.getId()).orElse(null);

        if (verification == null){

            editedModel.setDetail(newModel.getDetail());
            editedModel.setCode(newModel.getCode());
            editedModel.setDenomination(newModel.getDenomination());
            editedModel.setSalesItems(newModel.getSalesItems());
            editedModel.setState(0);

            Line updatedModel = modelRepository.save(editedModel);

            return modelMapper.convertModelToDto(updatedModel);
        }

        Line existingModel = modelRepository.findById(verification).orElse(null);

        if (existingModel.getState() == 1){

            editedModel.setState(1);

            existingModel.setDetail(newModel.getDetail());
            existingModel.setDenomination(newModel.getDenomination());
            existingModel.setSalesItems(newModel.getSalesItems());
            existingModel.setState(0);

            Line updatedModel = modelRepository.save(existingModel);

            return modelMapper.convertModelToDto(updatedModel);
        }
        return null;
    }

    @Override
    public void deleteModel(LineDTO model){
        Line existingModel = modelRepository.findById(model.getId()).orElse(null);
        if (existingModel != null) {
            existingModel.setState(1);
            modelRepository.save(existingModel);
        }
    }


    @Override
    public Integer verifyCode(Integer code){
        List<Line> lines = modelRepository.findAll();

        for(Line line : lines){
            if(line.getCode().equals(code)){
                return (line.getId());
            }
        }
        return null;
    }

    @Override
    public Integer verifyCode(Integer code, Integer id){
        List<Line> lines = modelRepository.findAll();

        for(Line line : lines){
            if(line.getId() != id && code.equals(line.getCode())){
                return (line.getId());
            }
        }
        return null;
    }













    /*
    @Autowired
    private LineRepository modelRepository;
    private static final Logger logger = LoggerFactory.getLogger(LineImplementation.class);

    @Override
    public List<Line> listar() {
        return modelRepository.buscarNoEliminados();
    }

    @Override
    public Line buscarPorId(Integer id) {
        return modelRepository.findById(id).orElse(null);
    }


    @Override
    public Line guardar(Line model) {
        return modelRepository.save(model);
    }


    @Override
    public Line newModel(LineaDTO modelDTO) {
        Line model =  new Line(modelDTO);
        return guardar(model);
    }


    @Override
    public void eliminar(Line model) {

        modelRepository.save(model);
    }

    @Override
    public List<Line> listar(String consulta) {
        //logger.info("service " +consulta);
        return modelRepository.buscarNoEliminados(consulta);
    }

    @Override
    public Page<Line> getLineas(Pageable pageable) {
        return  modelRepository.findAll(pageable);
    }

    public List<Line> buscar(String consulta) {
        return modelRepository.buscarExacto(consulta);
    }


    @Override
    public Page<LineaDTO> findPaginated(Pageable pageable, List<LineaDTO>lineas) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<LineaDTO> list;
        if (lineas.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, lineas.size());
            list = lineas.subList(startItem, toIndex);
        }

        Page<LineaDTO> bookPage
                = new PageImpl<LineaDTO>(list, PageRequest.of(currentPage, pageSize), lineas.size());

        return bookPage;
    }
    */
}
