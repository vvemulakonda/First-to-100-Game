/**
 * @author Vivek Vemulakonda
 * @version 1.0
 * The Player class represents a player with a name and score.
 * The score can be updated as the player gains points.
 */
public class Player {
    /** The name of the player. */
    private final String name;

    /** The score of the player. */
    private int score;

    /**
     * Constructs a new Player with the specified name.
     * The initial score is set to 0.
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current score of the player.
     *
     * @return the current score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * Adds the specified number of points to the player's score.
     *
     * @param points the number of points to add to the score
     */
    public void addScore(int points) {
        this.score += points;
    }
}
