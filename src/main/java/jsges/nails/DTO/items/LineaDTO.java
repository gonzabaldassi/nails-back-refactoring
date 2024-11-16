package jsges.nails.dto.items;

import jsges.nails.dto.TipoObjetoDTO;
import jsges.nails.domain.items.Line;

public class LineaDTO extends TipoObjetoDTO {

    public LineaDTO() {
       super();
    }

    public LineaDTO(Line line) {
        this.id= line.getId();
        this.denominacion= line.getDenominacion();
    }
}
