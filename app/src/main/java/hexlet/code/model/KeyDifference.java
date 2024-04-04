package hexlet.code.model;

import hexlet.code.EqualityCheckResult;

public final class KeyDifference {

    private final String key;
    private final Object leftValue;
    private final Object rightValue;
    private final EqualityCheckResult difference;

    public KeyDifference(String key, Object leftValue, Object rightValue, EqualityCheckResult difference) {
        this.key = key;
        this.leftValue = leftValue;
        this.rightValue = rightValue;
        this.difference = difference;
    }

    public String getKey() {
        return key;
    }

    public Object getLeftValue() {
        return leftValue;
    }

    public Object getRightValue() {
        return rightValue;
    }

    public EqualityCheckResult getDifference() {
        return difference;
    }
}
