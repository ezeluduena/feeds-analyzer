package namedEntities.enums;

public enum Category {
    LOCATION,
    ORGANIZATION,
    OTHER,
    PERSON;

    @Override
    public String toString() {
        switch (this) {
            case LOCATION:
                return "Location";
            case ORGANIZATION:
                return "Organization";
            case OTHER:
                return "Other";
            case PERSON:
                return "Person";
            default:
                return this.name();
        }
    }

    public static Category fromString(String category) {
        switch (category.toUpperCase()) {
            case "LOCATION":
                return LOCATION;
            case "ORGANIZATION":
                return ORGANIZATION;
            case "PERSON":
                return PERSON;
            default:
                return OTHER;
        }
    }
}