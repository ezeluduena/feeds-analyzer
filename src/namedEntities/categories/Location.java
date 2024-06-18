package namedEntities.categories;

import java.util.List;

import namedEntities.NamedEntity;
import namedEntities.enums.*;

public class Location extends NamedEntity {
    // Fields
    public int population;
    public int latitude;
    public int longitude;

    // Constructor
    public Location(String name, List<Topic> topics) {
        super(name, Category.LOCATION, topics);
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLongitude() {
        return longitude;
    }

}
