package de.bambussoft.meaningfulstrings;

public class CharFollower {

    private final char base;
    private final char following;

    public CharFollower(char base, char following) {
        this.base = base;
        this.following = following;
    }

    public char getBase() {
        return base;
    }

    public char getFollowing() {
        return following;
    }

    @Override
    public String toString() {
        return "" + base + following;
    }
}
