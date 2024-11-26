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

        @Column(columnDefinition = "TEXT")
        private String observation;

        @NotNull(message = "The line can not be empty")
        @ManyToOne
        @JoinColumn(name = "fk_line", referencedColumnName = "id")
        private Line line;
}

