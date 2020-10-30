package kalah;

import com.qualitascorpus.testsupport.IO;
import java.util.List;
import java.util.ArrayList;
import Containers.Container;
import Containers.House;
import Containers.Store;

public class GameBoard {
    List<Player> players;
    List<Container> containers;
    int numberOfHousesPerPlayer;
    private IO io;

    public GameBoard(int NumberOfPlayers, int NumberOfHouses, int startingSeedNumber, IO ioInput) {
        players = new ArrayList<Player>();
        this.containers = new ArrayList<Container>();
        this.numberOfHousesPerPlayer = NumberOfHouses;
        this.initHouses(startingSeedNumber);
        io = ioInput;

    }

    private void initHouses(int startingSeedNumber) {

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);

            for (int j = 0; j < numberOfHousesPerPlayer; j++) {
                this.containers.add(new House(player, startingSeedNumber));
            }
            this.containers.add(new Store(player));
        }
    }

    public int move(int houseNumber, int playerNumber) {
        Player player = players.get(playerNumber - 1);
        int housePosition = 0;

        if (player.getPlayerNumber() == 1) {
            housePosition = houseNumber - 1;
        } else {
            housePosition = houseNumber + this.numberOfHousesPerPlayer;
        }

        if (containers.get(housePosition).getSeedCount() == 0) {
            return -1;
        }
        Container currentHouse = containers.get(housePosition);

        int count = currentHouse.emptyHouse();

        while (count > 0) {
            if (currentHouse instanceof Store) {
                if (((Store) currentHouse).getOwner().equals(currentHouse)) {
                    currentHouse.incrementSeed();
                    count--;
                    if (count == 0) {
                        return player.getPlayerNumber();
                    }
                }
            } else {
                count--;
                if (count == 0 && currentHouse.getSeedCount() == 0 && currentHouse.getOwner().equals(player)) {
                    Container opponent = containers.get(containers.size() - 2 - housePosition);

                    if (opponent.getSeedCount() != 0) {

                        int capturedSeeds = opponent.emptyHouse();
                        Container playerStore = containers
                                .get(((containers.size() / 2) * player.getPlayerNumber()) - 1);
                        playerStore.addSeeds(capturedSeeds + 1);
                        return player.getPlayerNumber() % 2 + 1;
                    }
                }
                currentHouse.incrementSeed();
            }
        }
        return player.getPlayerNumber() % 2 + 1;
    }

    public int checkWin(int nextPlayerCount) {
        boolean playerTurnFished = true;
        int startHouseIndex = numberOfHousesPerPlayer * (nextPlayerCount - 1) + (nextPlayerCount - 1);
        int lastHouseIndex = numberOfHousesPerPlayer * nextPlayerCount + (nextPlayerCount - 1);
        for (int i = startHouseIndex; i < lastHouseIndex; i++) {
            if (containers.get(i).getSeedCount() != 0)
                playerTurnFished = false;
        }
        int winner = 0;
        if (playerTurnFished) {
            winner = getWinner();
        }

        return winner;

    }

    public Player getPlayer(int playerNum) {
        return this.players.get(playerNum - 1);
    }

    public int getWinner() {
        int player1Score = getScore(1);
        int player2Score = getScore(2);

        // return 3 if match tied
        int winner = 3;
        if (player1Score > player2Score) {
            winner = 1;
        } else if (player1Score < player2Score) {
            winner = 2;
        }

        return winner;

    }

    public int getScore(int playerCount) {
        int playerScore = 0;

        int startHouseIndex = numberOfHousesPerPlayer * (playerCount - 1) + (playerCount - 1);
        int lastHouseIndex = numberOfHousesPerPlayer * playerCount + (playerCount - 1);
        int i = startHouseIndex;
        for (i = startHouseIndex; i < lastHouseIndex; i++) {
            playerScore += containers.get(i).emptyHouse();
        }
        playerScore += containers.get(i).getSeedCount();

        return playerScore;
    }

    public void printBoard() {
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        StringBuilder builder = new StringBuilder();
        builder.append("| ");
        // add player name
        builder.append(players.get(1).getPlayerName());
        // go through second players houses first
        for (int i = numberOfHousesPerPlayer * 2; i > numberOfHousesPerPlayer; i--) {
            int count = containers.get(i).getSeedCount();
            builder.append(" | ");
            builder.append(i - numberOfHousesPerPlayer);
            builder.append("[");
            // if count is single digit add space
            if (count <= 9) {
                builder.append(" ");
            }
            builder.append(count);
            builder.append("]");
        }
        builder.append(" | ");
        // Check end of board count for adding space
        if (containers.get(numberOfHousesPerPlayer).getSeedCount() <= 9) {
            builder.append(" ");
        }
        builder.append(containers.get(numberOfHousesPerPlayer).getSeedCount());
        builder.append(" |");
        io.println(builder.toString());

        io.println("|    |-------+-------+-------+-------+-------+-------|    |");

        builder = new StringBuilder();
        builder.append("| ");
        if (containers.get(numberOfHousesPerPlayer * 2 + 1).getSeedCount() <= 9) {
            builder.append(" ");
        }
        builder.append(containers.get(numberOfHousesPerPlayer * 2 + 1).getSeedCount());
        for (int i = 0; i < numberOfHousesPerPlayer; i++) {
            int count = containers.get(i).getSeedCount();
            builder.append(" | ");
            builder.append(i + 1);
            builder.append("[");
            if (count <= 9) {
                builder.append(" ");
            }
            builder.append(count);
            builder.append("]");
        }
        builder.append(" | ");
        builder.append(players.get(0).getPlayerName());
        builder.append(" |");
        io.println(builder.toString());
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");

    }

}
