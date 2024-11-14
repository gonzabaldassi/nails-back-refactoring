package jsges.nails.dto.servicios;

import jsges.nails.domain.services.ServiceItem;
import lombok.Data;

@Data
public class ItemServicioDTO {
    private Integer id ;
    private String tipoServicio ;
    private Integer tipoServicioId ;
    private Double precio;
    private String observaciones;

    public ItemServicioDTO(ServiceItem model) {
        this.observaciones=model.getObservacion();
        this.precio=model.getPrecio();
        this.tipoServicio=model.getTipoServicio().getDenominacion();
        this.tipoServicioId=model.getTipoServicio().getId();
        this.id=model.getId();

    }

    public ItemServicioDTO() {

    }
}
