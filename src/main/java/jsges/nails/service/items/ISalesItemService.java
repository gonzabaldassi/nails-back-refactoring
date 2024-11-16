package jsges.nails.service.items;

import jsges.nails.dto.items.SalesItemDTO;
import jsges.nails.domain.items.SalesItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISalesItemService {

    public List<SalesItem> listar();

    public SalesItem buscarPorId(Integer id);

    public SalesItem guardar(SalesItem model);

    public void eliminar(SalesItem model);

    public List<SalesItem> listar(String consulta);

    public Page<SalesItem> getArticulos(Pageable pageable);

    public Page<SalesItemDTO> findPaginated(Pageable pageable, List<SalesItemDTO> list);
}
