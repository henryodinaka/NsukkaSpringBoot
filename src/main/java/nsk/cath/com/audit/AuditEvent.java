package nsk.cath.com.audit;

import lombok.Data;
import nsk.cath.com.model.WebAppAuditEntry;

@Data
public class AuditEvent {

    private WebAppAuditEntry auditEntry;

    public AuditEvent(WebAppAuditEntry auditEntry) {

        this.auditEntry = auditEntry;
    }
}