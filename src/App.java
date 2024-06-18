import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import feed.*;
import namedEntities.NamedEntity;
import namedEntities.heuristics.*;
import namedEntities.utils.EntitiesClassifier;
import namedEntities.utils.Stats;
import utils.*;

public class App {

    public static void main(String[] args) {
        List<FeedsData> feedsDataArray = new ArrayList<>();
        try {
            feedsDataArray = JSONParser.parseJsonFeedsData("src/data/feeds.json");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        UserInterface ui = new UserInterface();
        Config config = ui.handleInput(args, feedsDataArray);
        HashMap<String, FeedsData> feedsDataMap = new HashMap<>();
        for (FeedsData feedData : feedsDataArray) {
            feedsDataMap.put(feedData.getLabel(), feedData);
        }
        run(config, feedsDataMap);
    }

    private static void run(Config config, HashMap<String, FeedsData> feedsDataMap) {

        List<Article> allArticles = fetchFeeds(feedsDataMap, config);

        printFeeds(allArticles, config.getPrintFeed());

        computeNamedEntities(allArticles, config);

    }

    private static Heuristic setSelectedHeuristic(Config config) {
        // if computedNamedEntities is true, the respective heuristic is returned
        HeuristicsHashMap availableHeuristics = Heuristic.availableHeuristics();
        if (config.getHeuristic() == null) {
            System.out.println("No heuristic selected");
            System.exit(1);
        }
        if (!availableHeuristics.containsKey(config.getHeuristic())) {
            System.out.println("Error: Unknown heuristic. Heuristic with '" + config.getHeuristic()
                    + "' key not found.\n");
            System.out.println("Available heuristics:\n");
            availableHeuristics.print();
            System.exit(1);
        }

        Heuristic selectedHeuristic = availableHeuristics.get(config.getHeuristic());
        if (selectedHeuristic == null) {
            System.out.println("Heuristic not found");
            System.exit(1);
        }
        return selectedHeuristic;
    }

    private static List<Article> fetchFeeds(HashMap<String, FeedsData> feedsDataMap, Config config) {
        // Fetch selected feed(s) and returns a list of their respective articles
        if (feedsDataMap == null || feedsDataMap.isEmpty()) {
            System.out.println("No feeds data found");
            System.exit(1);
        }

        List<Article> allArticles = new ArrayList<>();
        try {
            String fetchOutput = "";
            if (config.getFeedKey().equals("all")) {
                for (FeedsData feedData : feedsDataMap.values()) {
                    fetchOutput = FeedParser.fetchFeed(feedData.getUrl());
                    allArticles.addAll(FeedParser.parseXML(fetchOutput));
                }
            } else {
                if (!feedsDataMap.containsKey(config.getFeedKey())) {
                    System.out.println("Feed key not found");
                    System.exit(1);
                }
                fetchOutput = FeedParser.fetchFeed(feedsDataMap.get(config.getFeedKey()).getUrl());
                allArticles.addAll(FeedParser.parseXML(fetchOutput));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return allArticles;
    }

    private static void printFeeds(List<Article> allArticles, Boolean printFeed) {
        if (printFeed) {
            System.out.println("Printing feed(s) ");
            for (Article article : allArticles) {
                article.print();
            }
            System.out.println("-".repeat(80));
        }
    }

    private static void computeNamedEntities(List<Article> allArticles, Config config) {
        // Compute named entities and print the respective stats by config
        if (config.getComputeNamedEntities()) {
            Heuristic selectedHeuristic = setSelectedHeuristic(config);
            System.out.print("Computing named entities using " + selectedHeuristic.getName() + " heuristic");

            // Extract candidates
            List<String> candidates = new ArrayList<>();
            for (Article article : allArticles) {
                candidates.addAll(selectedHeuristic.extractCandidates(article.getTitle()));
                candidates.addAll(selectedHeuristic.extractCandidates(article.getDescription()));
            }

            try {
                // Classify candidates
                List<NamedEntity> namedEntities = EntitiesClassifier.classifyCandidates(candidates);
                // Compute stats
                Stats stats = new Stats(namedEntities);
                // Print stats
                System.out.println("\nStats: ");
                stats.printStatsByConfig(config);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("-".repeat(80));
        }
    }

}
