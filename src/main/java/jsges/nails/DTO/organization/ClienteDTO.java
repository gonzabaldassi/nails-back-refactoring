package jsges.nails.dto.organization;
import jsges.nails.domain.organization.Customer;
import lombok.Data;

import java.util.Date;
@Data
public class ClienteDTO {

    private Integer id;
    private String razonSocial;
    private String letra;
    private String contacto;
    private String celular;
    private String mail;
    private Date fechaInicio;
    private Date fechaNacimiento;

    public ClienteDTO(Customer model) {
        this.id = model.getId();
        this.razonSocial=model.getRazonSocial();
        this.celular=model.getCelular();
        this.mail=model.getMail();

    }

    public ClienteDTO( ) {

    }
}
