package namedEntities.categories;

import java.util.List;

import namedEntities.NamedEntity;
import namedEntities.enums.*;

public class Organization extends NamedEntity {
    // Fields
    public String organizationType;

    // Constructor
    public Organization(String name, List<Topic> topics) {
        super(name, Category.ORGANIZATION, topics);
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public String getOrganizationType() {
        return organizationType;
    }

}
