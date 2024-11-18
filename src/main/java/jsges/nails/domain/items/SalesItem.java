package jsges.nails.domain.items;

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

@Table(name = "SalesItem")
public class SalesItem implements Serializable {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @NotNull(message = "Denomination can not be null")
        @Column(columnDefinition = "TEXT")
        private String denomination;

        @Column(columnDefinition = "INTEGER")
        private Integer state = 0;

        @NotNull(message = "Observation can not be null")
        @Column(columnDefinition = "TEXT")
        private String observation;

        @NotNull(message = "The line can not be empty")
        @ManyToOne
        @JoinColumn(name = "fk_line", referencedColumnName = "id")
        private Line line;

        /*@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;*/

        /*@Column(columnDefinition = "TEXT")
        String denomination;
        int estado;*/

        /*@ManyToOne(cascade = CascadeType.ALL)
        private Linea linea;*/

        /*
        public void asEliminado() {
               this.setEstado(1);
        }*/

        ///LOS ATRIBUTOS NO ESTABAN DEFINIDOS COMO PRIVATE, NO RESPETA ENCAPSULAMIENTO
}

