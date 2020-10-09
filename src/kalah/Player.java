package kalah;

public class Player {
    private String playerName;
    private int playerNumber;

    public Player(String name, int playerNum) {
        playerName = name;
        playerNumber = playerNum;

    }

    public String getPlayerName() {
        return this.playerName;
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }
}
