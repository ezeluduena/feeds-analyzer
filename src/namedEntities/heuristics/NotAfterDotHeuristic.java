package namedEntities.heuristics;

import java.util.List;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotAfterDotHeuristic extends Heuristic {

    public NotAfterDotHeuristic() {
        this.name = "Not After Dot";
        this.key = "not_after_dot";
        this.description = "Words in uppercase not following a dot.";
    }

    // Palabras en mayúsculas solas que no siguen de un punto
    public List<String> extractCandidates(String text) {
        List<String> candidates = new ArrayList<>();

        text = text.replaceAll("[-+^:,\"]", "");
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("\\p{M}", "");

        Pattern pattern = Pattern.compile("(?<!\\.\\s)\\b[A-Z][a-z]*\\b");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            candidates.add(matcher.group());
        }
        return candidates;
    }

}
