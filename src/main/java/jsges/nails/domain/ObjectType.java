package jsges.nails.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
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
        protected Integer id;

        @Column(columnDefinition = "INTEGER")
        protected Integer code;

        @NotEmpty(message = "The denomination can not be null")
        @Column(columnDefinition = "TEXT")
        protected String denomination;

        @Column(columnDefinition = "INTEGER")
        protected Integer state = 0;

        @Column(columnDefinition = "TEXT")
        protected String detail;
}
