package vinnsla;


/**
 * Represents a simple countdown timer for the game.
 * This class manages the countdown timing logic, decrementing the timer in one-second intervals.
 */
public class Klukka {
    /**
     * The current time remaining on the clock in seconds.
     */
    private int time;
    /**
     * Constructs a new countdown timer with a specified starting time.
     *
     * @param time The initial time in seconds for the countdown timer.
     */
    public Klukka(int time) {
        this.time = time;
    }
    /**
     * Retrieves the current time remaining on the countdown timer.
     *
     * @return The current time remaining in seconds.
     */
    public int getTime() {
        return time;
    }
    /**
     * Decreases the current time by one second. This method should be called periodically
     * to represent the passage of time in the game.
     */
    public void tick() {
        time--;
    }

    public void reset() {
        time = 0;
    }
}
