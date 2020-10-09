package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

/**
 * This class is the starting point for a Kalah implementation using the test
 * infrastructure.
 */

public class Kalah {
	public static void main(String[] args) {
		MockIO io = new MockIO();
		int numberOfHousesPerPlayer = 6;
		GameBoard gameBoard = new GameBoard(2, 6, 4, io);

		int currentPlayerNumber = 1;

		// gameBoard.checkWin returns 0 if there is no winner
		while (gameBoard.checkWin(currentPlayerNumber) != 0) {
			io.print("Player " + gameBoard.getPlayer(currentPlayerNumber).getPlayerName()
					+ "'s turn - Specify house number or 'q' to quit:");
			int selectedHouseNumber = io.readInteger(" ", 1, numberOfHousesPerPlayer, -1, "q");
			// player input q
			if (selectedHouseNumber == -1) {
				io.println("Game Over");
				gameBoard.printBoard();
				break;
			}
			int nextPlayerNumber = gameBoard.move(selectedHouseNumber, currentPlayerNumber);

			if (nextPlayerNumber > 0) {
				currentPlayerNumber = nextPlayerNumber;
			} else { // if the house was already empty gameBoard.move will return -1
				io.println("House is empty. Move again.");
				gameBoard.printBoard();
				continue;
			}

			gameBoard.printBoard();

			// gameBoard.checkwin will return 0 if there is no winner yet.
			int winner = gameBoard.checkWin(currentPlayerNumber);

			if (winner != 0) {
				io.println("Game Over");
				gameBoard.printBoard();
				io.println("\tplayer 1:" + gameBoard.getScore(1));
				io.println("\tplayer 2:" + gameBoard.getScore(2));

				if (winner == 3) {
					io.println("A tie!");
					break;
				} else {
					io.println("Player " + winner + " wins!");
					break;
				}
			}

		}
	}

}
