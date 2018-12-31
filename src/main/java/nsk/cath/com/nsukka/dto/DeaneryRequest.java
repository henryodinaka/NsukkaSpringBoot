package nsk.cath.com.nsukka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeaneryRequest {
    private Long id;
    private String name;
    private String deaneryCode;
    private Long dioceseId;
}
