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

        @Column(columnDefinition = "INTEGER")
        private Integer code;

        @NotEmpty(message = "The denomination can not be null")
        @Column(columnDefinition = "TEXT")
        private String denomination;

        @Column(columnDefinition = "INTEGER")
        private Integer state = 0;

        @Column(columnDefinition = "TEXT")
        private String detail;
}
