package jsges.nails.dto.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Integer id;
    private String businessName;
    private String letter;
    private String contact;
    private String phoneNumber;
    private String email;
    private Date startDate;
    private Date dateOfBirth;
}
