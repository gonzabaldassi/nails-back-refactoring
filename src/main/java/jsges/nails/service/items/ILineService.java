package jsges.nails.service.items;

import jsges.nails.dto.items.LineaDTO;
import jsges.nails.domain.items.Line;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILineService {

    public List<Line> listar();

    public Line buscarPorId(Integer id);

    public Line guardar(Line model);

    public void eliminar(Line model);

    public List<Line> listar(String consulta);
    public Page<Line> getLineas(Pageable pageable);

    public Page<LineaDTO> findPaginated(Pageable pageable,List<LineaDTO> lineas);


    public List<Line> buscar(String consulta);

    public Line newModel(LineaDTO model);
}
