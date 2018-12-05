package com.epam.training.model.collisions;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.epam.training.model.SnakeModelInterface;
import com.epam.training.model.animals.Snake;
import com.epam.training.model.area.Cell;

/**
 * @author Oleg_Burshinov
 * 
 */
public class SnakeSnakeCollisons implements CollisionsInterface {

	private Snake snake1;
	private LinkedList<Cell> snake1Cells;
	private Snake snake2;
	// private LinkedList<Cell> snake2Cells; // if more then one snake in game
	private SnakeModelInterface model;
	private static Logger logger = Logger.getLogger("com.epam.training");

	/**
	 * @param snake1
	 *            snake one
	 * @param snake2
	 *            snake two
	 * @param model
	 *            Game model
	 */
	public SnakeSnakeCollisons(Snake snake1, Snake snake2,
			SnakeModelInterface model) {
		this.snake1 = snake1;
		this.snake2 = snake2;
		this.model = model;
	}

	public void solve() {
		if (snake1.equals(snake2)) {
			// one snake collision
			snake1Cells = snake1.getSnakeCells();
			for (int z = snake1Cells.size(); z > 0; z--) {

				if ((z > 4)
						&& (snake1Cells.getFirst().getX() == snake1Cells.get(
								z - 1).getX())
						&& (snake1Cells.getFirst().getY() == snake1Cells.get(
								z - 1).getY())) {
					logger.log(Level.FINE, "SNAKE EAT SNAKE");
					model.getSnake().cancel();
					model.setGameOff();
				}
			}
		}
	}

}
