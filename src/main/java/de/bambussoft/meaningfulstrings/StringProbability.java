package de.bambussoft.meaningfulstrings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringProbability extends DoubleKeyMap<Character, Character, Double> {

    private StringProbability() {
        super();
    }

    public static StringProbability of(StringAnalysis stringAnalysis) {
        StringProbability stringProbability = new StringProbability();

        for(Map.Entry<Character, Long> e : stringAnalysis.charOccurrences.entrySet()) {
            Character baseChar = e.getKey();
            Long occurrences = e.getValue();
            HashMap<Character, Integer> followCount = stringAnalysis.charFollowData.get(baseChar);
            for(Map.Entry<Character, Integer> followingEntry : followCount.entrySet()) {
                Character following = followingEntry.getKey();
                Integer followingCount = followingEntry.getValue();
                double probability = (double) followingCount / (double) occurrences;

                stringProbability.put(baseChar, following, probability);
            }
        }

        return stringProbability;
    }

    public double evaluate(String test) {
        List<CharFollower> charFollowers = StringAnalysis.toCharTuple(test);
        double probabilitySum = charFollowers.stream().mapToDouble(c -> computeIfAbsent(c.getBase(), c.getFollowing(), (k1, k2) -> 0d)).sum();
        return probabilitySum / (test.length() - 1);
    }
}
