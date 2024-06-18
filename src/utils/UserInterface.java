package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import namedEntities.heuristics.Heuristic;
import namedEntities.heuristics.HeuristicsHashMap;

public class UserInterface {

    private HashMap<String, String> optionDict;
    private List<Option> options;
    private HeuristicsHashMap availableHeuristics;

    public UserInterface() {
        options = new ArrayList<Option>();
        options.add(new Option("-h", "--help", 0));
        options.add(new Option("-f", "--feed", 1));
        options.add(new Option("-ne", "--named-entity", 1));
        options.add(new Option("-pf", "--print-feed", 0));
        options.add(new Option("-sf", "--stats-format", 1));

        optionDict = new HashMap<String, String>();
        this.availableHeuristics = Heuristic.availableHeuristics();
    }

    public Config handleInput(String[] args, List<FeedsData> feedsDataArray) {

        for (int i = 0; i < args.length; i++) {
            for (Option option : options) {
                if (option.getName().equals(args[i]) || option.getLongName().equals(args[i])) {
                    if (option.getnumValues() == 0) {
                        optionDict.put(option.getName(), null);
                    } else {
                        if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                            optionDict.put(option.getName(), args[i + 1]);
                            i++;
                        } else {
                            System.out.println("Invalid inputs");
                            printHelp(feedsDataArray, availableHeuristics);
                            System.exit(1);
                        }
                    }
                }
            }
        }

        boolean printFeed = optionDict.containsKey("-pf");
        boolean computeNamedEntities = optionDict.containsKey("-ne");
        boolean helpMessage = optionDict.containsKey("-h");
        if (helpMessage) {
            printHelp(feedsDataArray, availableHeuristics);
            System.exit(0);
        }

        String heuristic = optionDict.get("-ne");
        String feedKey = optionDict.get("-f");
        String statsFormat = optionDict.get("-sf");

        return new Config(printFeed, computeNamedEntities, feedKey, heuristic, statsFormat);
    }

    private static void printHelp(List<FeedsData> feedsDataArray, HeuristicsHashMap availableHeuristics) {
        System.out.println();
        System.out.println("Usage: make run ARGS=\"[OPTION]\"");
        System.out.println("Options:");
        System.out.println("  -h, --help: Show this help message and exit");
        System.out.println();
        System.out.println("  -f, --feed <feedKey>:                Fetch and process the feed with");
        System.out.println("                                       the specified key");
        System.out.println("                                       Available feed keys are: ");
        for (FeedsData feedData : feedsDataArray) {
            System.out.println("                                       " + feedData.getLabel());
        }
        System.out.println();
        System.out.println("  -ne, --named-entity <heuristicName>: Use the specified heuristic to extract");
        System.out.println("                                       named entities");
        System.out.println("                                       Available heuristic names are: ");
        for (Heuristic heuristic : availableHeuristics.values()) {
            System.out.println("                                       " + heuristic.getName() + ": "
                    + heuristic.getDescription());
        }
        System.out.println(
                "                                       Capitalized Word Heuristic: Extracts named entities based on capitalized words");

        System.out.println();
        System.out.println("  -pf, --print-feed:                   Print the fetched feed");
        System.out.println();
        System.out.println("  -sf, --stats-format <format>:        Print the stats in the specified format");
        System.out.println("                                       Available formats are: ");
        System.out.println("                                       cat: Category-wise stats");
        System.out.println("                                       topic: Topic-wise stats");
    }

}
