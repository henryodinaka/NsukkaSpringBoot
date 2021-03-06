package nsk.cath.com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParishRequest {
    private Long id;
    private String name;
    private String parishCode;
    private Long deaneryId;
    private boolean isUpdate;
}
