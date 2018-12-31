package nsk.cath.com.nsukka.enums;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    UNKNOWN("Unknown");

    String value;
    Gender (String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Gender findByValue(String value)
    {
        Gender gender = null;
        for (Gender g : Gender.values())
        {
            if (g.value.equalsIgnoreCase(value))
            {
                gender =g;
                break;
            }
        }
       return gender == null ? UNKNOWN : gender;
    }
}
