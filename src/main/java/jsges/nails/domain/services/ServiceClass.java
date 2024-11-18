package jsges.nails.domain.services;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jsges.nails.domain.organization.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Table(name = "Service")
public class ServiceClass implements Serializable {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(columnDefinition = "INTEGER")
        private Integer state = 0;

        @Column(columnDefinition = "DATE")
        private Timestamp registrationTimestamp;

        @Column(columnDefinition = "DATE")
        private Timestamp completionTimestamp;

        @NotNull(message = "Total can not be null")
        @Column(columnDefinition = "DOUBLE")
        private Double total;

        @NotNull(message = "The customer can not be empty")
        @ManyToOne
        @JoinColumn(name = "fk_customer", referencedColumnName = "id")
        private Customer customer;

        @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<ServiceItem> serviceItems = new ArrayList<>();


        /*@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private int estado;

        @ManyToOne(cascade = CascadeType.ALL)
        private Customer customer;

        private Timestamp fechaRegistro;
        private Timestamp fechaRealizacion;
        private double total;

        public Service() { }*/


}
