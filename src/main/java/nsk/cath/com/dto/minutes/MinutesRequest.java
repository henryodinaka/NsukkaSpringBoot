package nsk.cath.com.dto.minutes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsk.cath.com.enums.Status;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MinutesRequest {
    private Long id;

    private Status status;

    private String topic;

    private String content;

    private String meetingNumber;
    private Date meetingDate;

}
