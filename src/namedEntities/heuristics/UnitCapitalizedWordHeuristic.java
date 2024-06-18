package namedEntities.heuristics;

import java.text.Normalizer;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnitCapitalizedWordHeuristic extends Heuristic {

    public UnitCapitalizedWordHeuristic() {
        this.name = "Unit Capitalized Word";
        this.key = "unit_capitalized_word";
        this.description = "Words in uppercase alone.";
    }

    // Palabras en mayuscula solas
    public List<String> extractCandidates(String text) {
        List<String> candidates = new ArrayList<>();

        text = text.replaceAll("[-+.^:,\"]", "");
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("\\p{M}", "");

        Pattern pattern = Pattern.compile("\\b[A-Z][a-z]*\\b");

        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            candidates.add(matcher.group());
        }
        return candidates;
    }

}