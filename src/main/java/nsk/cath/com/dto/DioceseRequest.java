package nsk.cath.com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DioceseRequest {
    private Long id;
    private String name;
    private String dioceseCode;
    private Long stateId;
}
