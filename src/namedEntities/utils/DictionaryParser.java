package namedEntities.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import namedEntities.enums.*;

public class DictionaryParser {

    public static List<DictionaryEntity> parseJSON(String dictionaryFilePath) throws IOException {
        String jsonData = new String(Files.readAllBytes(Paths.get(dictionaryFilePath)));
        List<DictionaryEntity> dictionaryList = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(jsonData);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            DictionaryEntity dictEntity = new DictionaryEntity(jsonObject.getString("label"),
                    Category.fromString(jsonObject.getString("Category")));

            JSONArray topics = jsonObject.getJSONArray("Topics");
            for (int j = 0; j < topics.length(); j++) {
                Topic topic = Topic.fromString(topics.getString(j));
                dictEntity.addTopic(topic);
            }

            JSONArray keywords = jsonObject.getJSONArray("keywords");
            for (int j = 0; j < keywords.length(); j++) {
                dictEntity.addKeyword(keywords.getString(j));
            }

            dictionaryList.add(dictEntity);

        }
        return dictionaryList;
    }

    public static void printEntitiesDictionary(List<DictionaryEntity> dictionary) {
        for (DictionaryEntity entity : dictionary) {
            System.out.println("Label: " + entity.getLabel());
            System.out.println("Category: " + entity.getCategory());
            System.out.println("Topics: " + entity.getTopics());
            System.out.println("Keywords: " + entity.getKeywords());
            System.out.println();
        }
    }
}
