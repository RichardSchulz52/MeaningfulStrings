package de.bambussoft.meaningfulstrings;

public class CharFollowData extends DoubleKeyMap<Character, Character, Integer> {

    void add(CharFollower tuple) {
        Integer old = computeIfAbsent(tuple.getBase(), tuple.getFollowing(), (c1, c2) -> 0);
        put(tuple.getBase(), tuple.getFollowing(), old + 1);
    }

}
