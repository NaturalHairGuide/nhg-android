package mobile.lynn.com.naturalhairguide.model;

public enum HairType {
    
    THREE_A("3A"),
    THREE_B("3B"),
    THREE_C("3C"),
    FOUR_A("4A"),
    FOUR_B("4B"),
    FOUR_C("4C"),
    NONE("None");

    private final String type;

    HairType(String type) {
        this.type = type;
    }

    public static HairType getHairType(String value) {
        HairType[] values = HairType.values();
        String enumValue;

        for(HairType type : values){
            enumValue = type.toString();
            if(enumValue.equals(value)){
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
