package namedEntities.heuristics;

import java.util.List;

public abstract class Heuristic {
    protected String name;
    protected String key;
    protected String description;

    public String getName() {
        return name;
    }

    public String getKey() { return key; }

    public String getDescription() {
        return description;
    }

    public void print() {
        System.out.println("    Name: " + name);
        System.out.println("    Key: " + key);
        System.out.println("    Description: " + description);
    }

    public List<String> extractCandidates(String text){
        // This method should be implemented by the subclasses
        throw new UnsupportedOperationException("Method not implemented.");
    }

    public static HeuristicsHashMap availableHeuristics() {
        HeuristicsHashMap availableHeuristics = new HeuristicsHashMap();

        availableHeuristics.putHeuristic(new CapitalizedWordHeuristic());
        availableHeuristics.putHeuristic(new NotAfterDotHeuristic());
        availableHeuristics.putHeuristic(new PerSentenceHeuristic());
        availableHeuristics.putHeuristic(new UnitCapitalizedWordHeuristic());

        return availableHeuristics;
    }

}
