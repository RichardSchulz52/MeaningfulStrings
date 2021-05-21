package de.bambussoft.meaningfulstrings;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordProbability {

    private final StringProbability stringProbability;
    private final double threshold;

    private WordProbability(StringProbability stringProbability, double threshold) {
        this.stringProbability = stringProbability;
        this.threshold = threshold;
    }

    public boolean isLegit(String str) {
        return stringProbability.evaluate(str) > threshold;
    }

    static WordProbability build(StringProbability stringProbability, Stream<String> words, float cutOff) {
        List<Double> wordProbablilities = words.map(stringProbability::evaluate).sorted().collect(Collectors.toList());
        int cutOffMark = (int) (wordProbablilities.size() * cutOff);
        double threshold = wordProbablilities.get(cutOffMark);
        return new WordProbability(stringProbability, threshold);
    }
}
