package jsges.nails.dto.services;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO {

    private Integer id;
    private Timestamp registrationTimestamp;
    private Timestamp completionTimestamp;
    private Double total;

    private Integer customerId;
    private String customerBusinessName;

    private List<ServiceItemDTO> serviceItems = new ArrayList<>();
}
