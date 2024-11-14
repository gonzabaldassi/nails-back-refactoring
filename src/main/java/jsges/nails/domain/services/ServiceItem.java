package jsges.nails.domain.services;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Table(name = "ServiceItem")
public class ServiceItem implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Observation can not be null")
    @Column(columnDefinition = "TEXT")
    private String observation;

    @Column(columnDefinition = "INTEGER")
    private Integer state;

    @NotNull(message = "Price can not be null")
    @Column(columnDefinition = "DOUBLE")
    private Double precio;

    /*@ManyToOne(cascade = CascadeType.ALL)
    private TipoServicio tipoServicio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Servicio servicio;*/

    @NotNull(message = "The serviceType can not be empty")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_serviceType", referencedColumnName = "id")
    private ServiceType serviceType;

    @NotNull(message = "The service can not be empty")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_service", referencedColumnName = "id")
    private Service service;

    /*public void asEliminado() {
        this.setEstado(1);
    }*/

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemServicio that = (ItemServicio) o;
        return estado == that.estado && Objects.equals(id, that.id) && Objects.equals(observacion, that.observacion) && Objects.equals(tipoServicio, that.tipoServicio) && Objects.equals(servicio, that.servicio);
    }*/

    /*@Override
    public int hashCode() {
        return Objects.hash(id, estado, observacion, tipoServicio, servicio);
    }*/

    /*public ItemServicio() {
    }*/

    /*public ItemServicio(Service service, ServiceType tipo, Double precio, String observacion) {
        this.servicio = service;
        this.tipoServicio = tipo;
        this.precio = precio;
        this.observacion=observacion;
    }*/

}
