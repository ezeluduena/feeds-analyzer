package namedEntities.heuristics;

import java.util.HashMap;

public class HeuristicsHashMap extends HashMap<String, Heuristic>{

    public void putHeuristic(Heuristic heuristic) {
        this.put(heuristic.getKey(), heuristic);
    }

    public void print() {
        for (Heuristic heuristic : this.values()) {
            heuristic.print();
            System.out.println();
        }
    }
}
