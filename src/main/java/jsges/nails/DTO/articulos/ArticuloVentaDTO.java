package jsges.nails.dto.articulos;

import jsges.nails.dto.TipoObjetoDTO;
import jsges.nails.domain.items.SalesItem;

public class ArticuloVentaDTO extends TipoObjetoDTO {

    public Integer id;
    public String denominacion;
    public Integer linea;

    public ArticuloVentaDTO( SalesItem model) {
        this.id = model.getId();
        this.denominacion=model.getDenominacion();
        this.linea=model.getLinea().getId();
    }

    public ArticuloVentaDTO( ) {

    }
}
