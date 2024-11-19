package jsges.nails.service.items;

import jsges.nails.dto.items.SalesItemDTO;
import jsges.nails.domain.items.SalesItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISalesItemService {

    public List<SalesItemDTO> getModels();

    public SalesItemDTO getModelById(Integer id);

    public List<SalesItemDTO> getModelByRequest(String request);

    public Page<SalesItemDTO> findPaginated(Pageable pageable, List<SalesItemDTO> models);

    public SalesItemDTO createModel(SalesItem model);

    public SalesItemDTO updateModel(SalesItemDTO editedModelDTO, SalesItem newModel);

    public void deleteModel(SalesItemDTO model);
}
