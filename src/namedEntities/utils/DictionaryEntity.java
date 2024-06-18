package namedEntities.utils;

import java.util.List;
import java.util.ArrayList;

import namedEntities.enums.*;;

public class DictionaryEntity {
    public String label;
    public Category category;
    public List<Topic> topics;
    public List<String> keywords;

    public DictionaryEntity(String label, Category category) {
        this.label = label;
        this.category = category;
        this.topics = new ArrayList<>();
        this.keywords = new ArrayList<>();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category categoria) {
        this.category = categoria;
    }

    public void addTopic(Topic topico) {
        topics.add(topico);
    }

    public void addAllTopics(List<Topic> topicos) {
        topics.addAll(topicos);
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void addKeyword(String keyword) {
        keywords.add(keyword);
    }

    public void addAllKeywords(List<String> keywords) {
        this.keywords.addAll(keywords);
    }

    public List<String> getKeywords() {
        return keywords;
    }

}
