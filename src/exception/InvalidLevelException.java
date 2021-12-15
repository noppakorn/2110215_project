package exception;

/**
 * Exception that will be used when the user input level is invalid.
 */
public class InvalidLevelException extends Exception {
    /**
     * Instantiates a new Invalid level exception.
     *
     * @param minLevel the min level that should be
     * @param maxLevel the max level that should be
     */
    public InvalidLevelException(int minLevel, int maxLevel) {
        super("Level should be between " + minLevel + " and " + maxLevel);
    }
}
