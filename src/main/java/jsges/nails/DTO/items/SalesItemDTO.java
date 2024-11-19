package jsges.nails.dto.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesItemDTO {

    private Integer id;
    private String denomination;
    private Integer lineId;

}
