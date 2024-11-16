package jsges.nails.service.items;

import jsges.nails.dto.items.LineaDTO;
import jsges.nails.domain.items.Line;
import jsges.nails.repository.items.LineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LineImplementation implements ILineService {

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

}
