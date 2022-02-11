package de.bambussoft.meaningfulstrings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StringAnalysis {

    final CountMap<Integer> wordLength;
    final CountMap<Character> charOccurrences;
    final CharFollowData charFollowData;

    private StringAnalysis(CountMap<Integer> wordLength, CountMap<Character> charOccurrences, CharFollowData charFollowData) {
        this.wordLength = wordLength;
        this.charOccurrences = charOccurrences;
        this.charFollowData = charFollowData;
    }

    static StringAnalysis of(Stream<String> lines) {
        CountMap<Integer> wordLength = new CountMap<>();
        CountMap<Character> charOccurrences = new CountMap<>();
        CharFollowData charFollowData = new CharFollowData();

        lines.forEach(l -> {
            wordLength.add(l.length());
            List<CharFollower> charFollowers = toCharTuple(l);
            charFollowers.forEach(t -> {
                charOccurrences.add(t.getBase());
                charFollowData.add(t);
            });
        });
        return new StringAnalysis(wordLength, charOccurrences, charFollowData);
    }

    static List<CharFollower> toCharTuple(String l) {
        List<CharFollower> charFollowers = new ArrayList<>();
        char[] chars = l.toLowerCase().toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            charFollowers.add(new CharFollower(chars[i], chars[i + 1]));
        }
        return charFollowers;
    }
}
