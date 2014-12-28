package mobile.lynn.com.naturalhairguide.model;

public enum Frequency {
    FORTNIGHTLY, MONTHLY, QUARTERLY, YEARLY;

    @Override
    public String toString() {
        switch (this) {
            case FORTNIGHTLY:
                return "two weeks";
            case MONTHLY:
                return "month";
            case QUARTERLY:
                return "3 months";
            case YEARLY:
                return "year";
        }

        return "";
    }
}
