package jsges.nails.dto.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceItemDTO {

    private Integer id ;
    private String observation;
    private Double price;

    private Integer serviceTypeId;
    private Integer serviceId;
}
