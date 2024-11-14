package jsges.nails.dto.servicios;

import jsges.nails.domain.services.ServiceItem;
import jsges.nails.domain.services.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServicioDTO{

    public Integer id;
    public Integer cliente;
    public Timestamp fechaDocumento;
    public Set<ItemServicioDTO> listaItems = new HashSet<>();
    public Double total;
    public String clienteRazonSocial;
    public ServicioDTO() {

    }

    public ServicioDTO(Service elemento, List<ServiceItem>list) {

        this.id = elemento.getId();
        this.cliente = elemento.getCustomer().getId();
        this.clienteRazonSocial = elemento.getCustomer().getRazonSocial();
        this.fechaDocumento = elemento.getFechaRealizacion();
        this.total= elemento.getTotal();

        list.forEach((model) -> {
           listaItems.add(new ItemServicioDTO(model));
        });
    }

    public ServicioDTO(Service elemento){

        this.id = elemento.getId();
        this.cliente = elemento.getCustomer().getId();
        this.clienteRazonSocial = elemento.getCustomer().getRazonSocial();
        this.fechaDocumento = elemento.getFechaRealizacion();
        this.total= elemento.getTotal();


    }
}
