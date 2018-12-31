package nsk.cath.com.nsukka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequest {
    private Long id;
    private String houseAddress;
    private String town;
    private Long parishId;
    private boolean isHomeAddress;
}
