package jsges.nails.domain.services;

import jakarta.persistence.*;
import jsges.nails.domain.organization.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@ToString
public class Service {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private int estado;

        @ManyToOne(cascade = CascadeType.ALL)
        private Customer customer;

        private Timestamp fechaRegistro;
        private Timestamp fechaRealizacion;
        private double total;


    public Service() {

    }



}
