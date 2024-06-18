package namedEntities.categories;

import java.util.List;

import namedEntities.NamedEntity;
import namedEntities.enums.*;

public class Other extends NamedEntity {
    // Fields
    public String description;

    // Constructor
    public Other(String name, List<Topic> topics) {
        super(name, Category.OTHER, topics);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
