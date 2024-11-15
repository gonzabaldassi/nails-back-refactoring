package jsges.nails.domain.items;

import jakarta.persistence.*;
import jsges.nails.domain.ObjectType;
import jsges.nails.domain.services.ServiceItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Table(name = "Line")
public class Line extends ObjectType implements Serializable {

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesItem> salesItems = new ArrayList<>();


    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Integer id;

    //@Column(columnDefinition = "TEXT")
    //String denominacion;
    //int estado;

    //@Column(columnDefinition = "TEXT")
    //String observacion;

    /*public Line() {
        // Constructor por defecto necesario para JPA
    }

    public Line(String nombre) {

        this.setDenominacion(nombre);
    }

    public Line(LineaDTO model) {
        this.setDenominacion(model.denominacion);

    }*/
}

