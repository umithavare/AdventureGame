package MaceraOyunu;

public interface GameOutput {
    void showMessage(String message);
    void showPlayerStats(Player player);
    void showObstacleStats(Obstacle obstacle, int count);
    void clearScreen();
}
