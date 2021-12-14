package exception;

/**
 * The type Invalid level exception.
 */
public class InvalidLevelException extends Exception {
    /**
     * Instantiates a new Invalid level exception.
     *
     * @param minLevel the min level
     * @param maxLevel the max level
     */
    public InvalidLevelException(int minLevel, int maxLevel) {
        super("Level should be between " + minLevel + " and " + maxLevel);
    }
}
