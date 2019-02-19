package nsk.cath.com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsk.cath.com.enums.Qualification;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationRequest {
    private Long id;
    private Qualification qualification;
    private String school;
    private String course;
    private Date year;
    private Long userId;
}
