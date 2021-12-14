package exception;

public class InvalidLevelException extends Exception {
    public InvalidLevelException(int minLevel, int maxLevel) {
        super("Level should be between " + minLevel + " and " + maxLevel);
    }
}
