package nsk.cath.com.nsukka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateRequest {
    private Long id;
    private String name;
    private Long countryId;
}
