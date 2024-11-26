package jsges.nails.DTO.services;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jsges.nails.dto.services.ServiceItemDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceClassDTO {

    private Integer id;
    private Timestamp registrationTimestamp;
    private Timestamp completionTimestamp;
    private Double total;

    private Integer customerId;
    private String customerBusinessName;

    private List<ServiceItemDTO> serviceItems = new ArrayList<>();
}
