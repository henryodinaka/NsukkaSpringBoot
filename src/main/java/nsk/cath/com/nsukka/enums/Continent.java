package nsk.cath.com.nsukka.enums;

public enum Continent {
    AFRICA ("Africa"),
    ASIA("Asia"),
    NORTH_AMERICA("North America"),
    SOUTH_AMERICA("South Amrica"),
    ANTARCTICA("Antarctica"),
    EUROPE("Europe"),
    AUSTRALIA("Australia"),
    UNKNOWN("Unknown");

    String value;
    Continent (String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public Continent findByValue(String value)
    {
        Continent continent = null;
        for (Continent c: Continent.values())
        {
            if (c.value.equalsIgnoreCase(value))
            {
                continent = c;
                break;
            }
        }
        return continent == null ?UNKNOWN : continent;
    }
}
