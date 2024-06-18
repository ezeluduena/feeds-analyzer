package namedEntities.categories;

import java.util.List;

import namedEntities.NamedEntity;
import namedEntities.enums.*;

public class Person extends NamedEntity {
    public int age;

    public Person(String name, List<Topic> topics) {
        super(name, Category.PERSON, topics);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
