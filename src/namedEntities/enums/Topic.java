package namedEntities.enums;

public enum Topic {
    POLITICS,
    SPORTS,
    ECONOMICS,
    HEALTH,
    TECHNOLOGY,
    CULTURE,
    BUSINESS,
    ENTERTAINMENT,
    OTHER;

    @Override
    public String toString() {
        switch (this) {
            case POLITICS:
                return "Politics";
            case SPORTS:
                return "Sports";
            case ECONOMICS:
                return "Economics";
            case HEALTH:
                return "Health";
            case TECHNOLOGY:
                return "Technology";
            case CULTURE:
                return "Culture";
            case BUSINESS:
                return "Business";
            case ENTERTAINMENT:
                return "Entertainment";
            default:
                return "Other";
        }
    }

    public static Topic fromString(String topic) {
        switch (topic.toUpperCase()) {
            case "POLITICS":
                return POLITICS;
            case "SPORTS":
                return SPORTS;
            case "ECONOMICS":
                return ECONOMICS;
            case "HEALTH":
                return HEALTH;
            case "TECHNOLOGY":
                return TECHNOLOGY;
            case "CULTURE":
                return CULTURE;
            case "BUSINESS":
                return BUSINESS;
            case "ENTERTAINMENT":
                return ENTERTAINMENT;
            default:
                return OTHER;
        }
    }
}