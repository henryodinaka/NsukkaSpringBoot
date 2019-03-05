package nsk.cath.com.errorHandler;

import lombok.Data;

@Data
public class NSKException extends Exception {
    private String code;
    private String nskErrorCode;
    public NSKException(String message, String code, String nskErrorCode)
    {
        super(message);
        this.code = code;
        this.nskErrorCode = nskErrorCode;
    }
}
