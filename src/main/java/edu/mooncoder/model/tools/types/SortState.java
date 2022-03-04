package edu.mooncoder.model.tools.types;

public enum SortState {
    NO_SORTED, SORTED_BY_NAME, SORTED_BY_SCORE;

    public static SortState getSortStateBy(boolean byScore) {
        return (byScore) ? SORTED_BY_SCORE : SORTED_BY_NAME;
    }
}
