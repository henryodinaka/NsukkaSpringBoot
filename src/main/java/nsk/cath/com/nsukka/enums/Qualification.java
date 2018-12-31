package nsk.cath.com.nsukka.enums;

public enum Qualification {
    A_LEVEL("A Level"),
    WAEC ("WAEC"),
    NCE("NCE"),
    ND("ND"),
    HND("HND"),
    BSC("B.sc"),
    MSC("M.Sc"),
    DR("Dr"),
    UNKNOWN("Unknown");
    String value;
    Qualification (String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Qualification findByValue(String value)
    {
        Qualification qualification = null;
        for (Qualification q : Qualification.values())
        {
            if (q.value.equalsIgnoreCase(value))
            {
                qualification =q;
                break;
            }
        }
        return qualification == null ? UNKNOWN : qualification;
    }
}
