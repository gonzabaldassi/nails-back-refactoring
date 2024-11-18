package jsges.nails.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class ObjectType implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "objectType_id_seq")
        @SequenceGenerator(name = "objectType_id_seq", sequenceName = "objectType_id_seq", allocationSize = 1)
        private Integer id;

        @NotEmpty(message = "The code can not be null")
        @Column(columnDefinition = "INTEGER")
        private Integer code;

        @NotEmpty(message = "The denomination can not be null")
        @Column(columnDefinition = "TEXT")
        private String denomination;

        @Column(columnDefinition = "INTEGER")
        private Integer state = 0;

        @Column(columnDefinition = "TEXT")
        private String detail;


        /*public void asEliminado() {
            this.setEstado(1);
        }*/

        /*@Override
        public int hashCode() {
            int hash = 7;
            return hash;
        }*/

        /*@Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ObjectType other = (ObjectType) obj;
            return Objects.equals(this.id, other.id);
        }*/
}
