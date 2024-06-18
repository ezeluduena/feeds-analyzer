package namedEntities.utils;

import namedEntities.*;
import namedEntities.enums.*;
import namedEntities.categories.*;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;

public class EntitiesClassifier {

    public static List<NamedEntity> classifyCandidates(List<String> candidates) throws IOException {
        List<DictionaryEntity> dictionary = DictionaryParser.parseJSON("src/data/dictionary.json");
        List<NamedEntity> classifiedCandidates = new ArrayList<>();

        for (String namedEntity : candidates) {
            try {
                NamedEntity classifiedNamedEntity = classifyCandidate(namedEntity, dictionary);
                classifiedCandidates.add(classifiedNamedEntity);
            } catch (IllegalArgumentException e) {
                // Do nothing
            }
        }

        return classifiedCandidates;
    }

    private static NamedEntity classifyCandidate(String candidate, List<DictionaryEntity> dictionary)
            throws IOException {

        for (DictionaryEntity entity : dictionary) {
            if (entity.getKeywords().contains(candidate)) {
                if (entity.getCategory() == Category.PERSON) {
                    return new Person(entity.getLabel(), entity.getTopics());
                } else if (entity.getCategory() == Category.LOCATION) {
                    return new Location(entity.getLabel(), entity.getTopics());
                } else if (entity.getCategory() == Category.ORGANIZATION) {
                    return new Organization(entity.getLabel(), entity.getTopics());
                } else {
                    return new Other(entity.getLabel(), entity.getTopics());
                }
            }
        }
        throw new IllegalArgumentException("No matching entity found for candidate: " + candidate);
    }
}
