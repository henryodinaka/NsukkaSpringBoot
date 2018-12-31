package nsk.cath.com.nsukka.enums;

public enum Title {

    CHIEF("Chief"),
    BAR("Bar"),
    DR ("Dr"),
    ENGR("Engr"),
    MISS("Miss"),
    MR("Mr"),
    MRS("Mrs"),
    MS("Ms"),
    PROF("Prof"),
    UNKNOWN("Uknown");

    String value;
    Title (String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Title findByValue(String value)
    {
        Title title = null;
        for (Title t : Title.values())
        {
            if (t.value.equalsIgnoreCase(value))
            {
                title =t;
                break;
            }
        }
        return title == null ? UNKNOWN : title;
    }
}
