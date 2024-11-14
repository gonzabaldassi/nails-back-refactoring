package jsges.nails.service.articulos;

import jsges.nails.dto.articulos.ArticuloVentaDTO;
import jsges.nails.domain.items.SalesItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IArticuloVentaService {

    public List<SalesItem> listar();

    public SalesItem buscarPorId(Integer id);

    public SalesItem guardar(SalesItem model);

    public void eliminar(SalesItem model);

    public List<SalesItem> listar(String consulta);

    public Page<SalesItem> getArticulos(Pageable pageable);

    public Page<ArticuloVentaDTO> findPaginated(Pageable pageable, List<ArticuloVentaDTO> list);
}
