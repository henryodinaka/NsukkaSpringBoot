package nsk.cath.com.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BranchRequest {
    private Long id;
    private String name;
    private String address;
    private String city;
    private Long stateId;
    private Long countryId;
    private boolean isUpdate;
}
