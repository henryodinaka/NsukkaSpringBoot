package nsk.cath.com.enums;

public enum RoleName {
    PRESIDENT("President"),
    SEC("Secretary"),
    FIN_SEC("Financial Secretary"),
    PRO("Pro"),
    TREASURER("Treasure"),
    USER("User"),
    //unknown should never be used. This is only returned if on
    //if the find method below does not return a value
    UNKNOWN("Unknown");

    String value;

    RoleName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static synchronized RoleName find(String roleName) {
        try {
            return RoleName.valueOf(roleName);
        } catch (Exception e) {
            return findByValue(roleName);
        }
    }

    private static RoleName findByValue(String value) {
        RoleName type = null;

        for (RoleName roleName : RoleName.values()) {
            if( roleName.value.equalsIgnoreCase(value)) {
                type = roleName;
                break;
            }
        }
        return  type == null ? UNKNOWN : type;

    }


}