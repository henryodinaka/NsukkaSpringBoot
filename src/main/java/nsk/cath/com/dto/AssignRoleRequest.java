package nsk.cath.com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsk.cath.com.enums.RoleName;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignRoleRequest {
    private Long userId;
    private RoleName roleName;
}
