package nsk.cath.com.errorHandler;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsk.cath.com.enums.Constants;
import nsk.cath.com.utils.JsonDateSerializer;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

//  @JsonView(View.ThirdParty.class)
  @JsonSerialize(using = JsonDateSerializer.class)
  private Date timestamp;

//  @JsonView(View.ThirdParty.class)
  private String message;

//  @JsonView(View.ThirdParty.class)
  private List<String> errors;

  public ErrorDetails(Date timestamp, String message, String error) {
    this.timestamp = timestamp;
    this.message = message;
    this.errors = Arrays.asList(error);
  }

  public ErrorDetails(String error) {
    this.timestamp = new Date();
    this.message = Constants.ERROR_MESSAGE;
    this.errors = Arrays.asList(error);
  }


  public static ResponseEntity<?> setUpErrors(String topic, List<String> errors, String code)
  {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), topic, errors);
    return ResponseEntity.status(Integer.valueOf(code)).body(errorDetails);

  }
}