package namedEntities.heuristics;

import java.util.List;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PerSentenceHeuristic extends Heuristic {

    public PerSentenceHeuristic() {
        this.name = "Per Sentence";
        this.key = "per_sentence";
        this.description = "Words in uppercase together, separated by dots and commas.";
    }

    // Palabras en mayuscula juntas, separadas por puntos y comas
    public List<String> extractCandidates(String text) {
        List<String> candidates = new ArrayList<>();
        String[] segments = text.split("[.,]");

        for (String segment : segments) {
            segment = segment.replaceAll("[-+.^:\",]", "");
            segment = Normalizer.normalize(segment, Normalizer.Form.NFD);
            segment = segment.replaceAll("\\p{M}", "");

            Pattern pattern = Pattern.compile("[A-Z][a-z]+(?:\\s[A-Z][a-z]+)*");
            Matcher matcher = pattern.matcher(segment);

            while (matcher.find()) {
                candidates.add(matcher.group());
            }

        }
        return candidates;
    }

}
