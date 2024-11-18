package jsges.nails.service.items;

import jsges.nails.dto.items.LineDTO;
import jsges.nails.domain.items.Line;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILineService {

    public List<LineDTO> getModels();

    public LineDTO getModelById(Integer id);

    public List<LineDTO> getModelByRequest(String request);

    public Page<LineDTO> findPaginated(Pageable pageable, List<LineDTO> lines);

    public LineDTO createModel(Line model);

    public LineDTO updateModel(LineDTO editedModelDTO, Line newModel);

    public void deleteModel(LineDTO model);

    public Integer verifyCode(Integer code);

    public Integer verifyCode(Integer code, Integer id);
}
