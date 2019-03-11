package nsk.cath.com.enums;

public enum  Status {
    ACTIVE("Active"),
    INACTIVE("Active"),
    UNAUTHORIZED_CREATE("Unauthorized create"),
    UNAPPROVED_POST("Unapproved post"),
    SUSPENDEND("Suspended"),
    BLOCKED("Blocked"),
    UNKNOWN("Unknown"),
    SEC_APPROVED("Secretary approved"), //first stage of minutes when posted by the secretary
    PRO_APPROVED("PRO approved"), //first stage of announcement when posted by the PRO
    FIN_SEC_APPROVED("Fin Sec approved"), //first stage of financial report when posted by the financial secretary
    TREASURER_APPROVED("Treasurer approved"),//Last stage of financial report when approved by the Treasurer
    PRESIDENT_APPROVED("Presodent approved");//Last stage of Announcement or MinutesRepo when approved by the President

    String value;
    Status (String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public Status findByValue(String value)
    {
        Status status =null;
        for (Status s : Status.values())
        {
            if (s.value.equalsIgnoreCase(value))
            {
                status = s;
                break;
            }
        }
        return status == null ? UNKNOWN : status;
    }
    public Status find(String value)
    {
        try {
            return Status.valueOf(value);
        }
        catch (Exception e)
        {
            return findByValue(value);
        }
    }
}
