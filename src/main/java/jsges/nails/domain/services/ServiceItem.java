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
    private Integer state = 0;

    @NotNull(message = "Price can not be null")
    @Column(columnDefinition = "DOUBLE")
    private Double price;

    @NotNull(message = "The serviceType can not be empty")
    @ManyToOne
    @JoinColumn(name = "fk_serviceType", referencedColumnName = "id")
    private ServiceType serviceType;

    @NotNull(message = "The service can not be empty")
    @ManyToOne
    @JoinColumn(name = "fk_service", referencedColumnName = "id")
    private ServiceClass service;
}
