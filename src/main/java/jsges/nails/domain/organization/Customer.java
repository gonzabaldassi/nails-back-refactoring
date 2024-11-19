package jsges.nails.domain.organization;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jsges.nails.domain.services.ServiceClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Table(name = "Customer")
public class Customer implements Serializable {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @NotNull(message = "Business Name can not be null")
        @Column(columnDefinition = "TEXT")
        private String businessName;

        @Column(columnDefinition = "INTEGER")
        private Integer state = 0;

        @Column(columnDefinition = "TEXT")
        private String letter;

        @NotNull(message = "Contact can not be null")
        @Column(columnDefinition = "TEXT")
        private String contact;

        @NotNull(message = "Phone number can not be null")
        @Column(columnDefinition = "TEXT")
        private String phoneNumber;

        @Column(columnDefinition = "TEXT")
        private String email;

        @Column(columnDefinition = "DATE")
        private Date dateOfBirth;

        @Column(columnDefinition = "DATE")
        private Date startDate;

        @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
        private List<ServiceClass> services = new ArrayList<>();

        /*String razonSocial;
        int estado;

        @Column(columnDefinition = "TEXT")
        String letra;

        @Column(columnDefinition = "TEXT")
        String contacto;

        @Column(columnDefinition = "TEXT")
        String celular;
        @Column(columnDefinition = "TEXT")
        String mail;

        Date fechaInicio;
        Date fechaNacimiento;*/

}
