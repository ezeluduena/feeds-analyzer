
package namedEntities.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import namedEntities.NamedEntity;
import namedEntities.categories.*;
import namedEntities.enums.Topic;
import utils.Config;

public class Stats {
    private Map<String, Integer> locations;
    private Map<String, Integer> persons;
    private Map<String, Integer> organizations;
    private Map<String, Integer> others;
    private Map<String, Map<String, Integer>> topics;

    public Stats(List<NamedEntity> namedEntities) {
        this.locations = new HashMap<String, Integer>();
        this.persons = new HashMap<String, Integer>();
        this.organizations = new HashMap<String, Integer>();
        this.others = new HashMap<String, Integer>();
        this.topics = new HashMap<String, Map<String, Integer>>();

        setCategoriesStats(namedEntities);

        setTopicsStats(namedEntities);

    }

    private void setCategoriesStats(List<NamedEntity> namedEntities) {
        for (NamedEntity namedEntity : namedEntities) {
            if (namedEntity instanceof Location) {
                String name = namedEntity.getNombre();
                if (locations.containsKey(name)) {
                    locations.put(name, locations.get(name) + 1);
                } else {
                    locations.put(name, 1);
                }
            } else if (namedEntity instanceof Person) {
                String name = namedEntity.getNombre();
                if (persons.containsKey(name)) {
                    persons.put(name, persons.get(name) + 1);
                } else {
                    persons.put(name, 1);
                }
            } else if (namedEntity instanceof Organization) {
                String name = namedEntity.getNombre();
                if (organizations.containsKey(name)) {
                    organizations.put(name, organizations.get(name) + 1);
                } else {
                    organizations.put(name, 1);
                }
            } else {
                String name = namedEntity.getNombre();
                if (others.containsKey(name)) {
                    others.put(name, others.get(name) + 1);
                } else {
                    others.put(name, 1);
                }
            }
        }
    }

    public void printCategoriesStats() {
        System.out.println("Category: ORGANIZATION");
        for (Map.Entry<String, Integer> entry : organizations.entrySet()) {
            System.out.println(entry.getKey() + " (" + entry.getValue() + ")");
        }
        System.out.println();

        System.out.println("Category: LOCATION");
        for (Map.Entry<String, Integer> entry : locations.entrySet()) {
            System.out.println(entry.getKey() + " (" + entry.getValue() + ")");
        }
        System.out.println();

        System.out.println("Category: OTHER");
        for (Map.Entry<String, Integer> entry : others.entrySet()) {
            System.out.println(entry.getKey() + "(" + entry.getValue() + ")");
        }
        System.out.println();

        System.out.println("Category: PERSON");
        for (Map.Entry<String, Integer> entry : persons.entrySet()) {
            System.out.println(entry.getKey() + "(" + entry.getValue() + ")");
        }
    }

    private void setTopicsStats(List<NamedEntity> namedEntities) {

        for (NamedEntity namedEntity : namedEntities) {
            for (Topic topic : namedEntity.getTopics()) {
                if (topics.containsKey(topic.toString())) {
                    Map<String, Integer> entities = topics.get(topic.toString());
                    String name = namedEntity.getNombre();
                    if (entities.containsKey(name)) {
                        entities.put(name, entities.get(name) + 1);
                    } else {
                        entities.put(name, 1);
                    }
                } else {
                    Map<String, Integer> entities = new HashMap<String, Integer>();
                    entities.put(namedEntity.getNombre(), 1);
                    topics.put(topic.toString(), entities);
                }
            }
        }
    }

    public void printTopicsStats() {
        for (Map.Entry<String, Map<String, Integer>> entry : topics.entrySet()) {
            System.out.println("Topic: " + entry.getKey());
            for (Map.Entry<String, Integer> entity : entry.getValue().entrySet()) {
                System.out.println(entity.getKey() + " (" + entity.getValue() + ")");
            }
            System.out.println();
        }
    }

    public void printStatsByConfig(Config config) {
        if (config.getStatsFormat() == null) {
            System.out.println("No stats format found");
            return;
        } else if (config.getStatsFormat().equals("cat")) {
            printCategoriesStats();
        } else if (config.getStatsFormat().equals("topic")) {
            printTopicsStats();
        }
    }

}
