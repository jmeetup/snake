package com.epam.training.model.animals.behavior;

import java.util.Comparator;

import com.epam.training.model.SnakeModelInterface;
import com.epam.training.model.area.Cell;

/**
 * @author Oleg_Burshinov
 * Compare cell how far snake
 */
public class HeadDistCoparator implements Comparator<Cell> {

	private Cell snakeHead;

	/**
	 * @param snakeHead
	 *            Snake head cell
	 * @param model
	 *            Game model
	 */
	public HeadDistCoparator(Cell snakeHead, SnakeModelInterface model) {
		this.snakeHead = snakeHead;
	}

	public int compare(Cell c1, Cell c2) {

		int result = -1;

		double d1 = Math.sqrt((snakeHead.getX() - c1.getX())
				* (snakeHead.getX() - c1.getX())
				+ (snakeHead.getY() - c1.getY())
				* (snakeHead.getY() - c1.getY()));

		double d2 = Math.sqrt((snakeHead.getX() - c2.getX())
				* (snakeHead.getX() - c2.getX())
				+ (snakeHead.getY() - c2.getY())
				* (snakeHead.getY() - c2.getY()));

		if ((int) d1 < (int) d2) {
			result = -1;
		}
		if ((int) d1 > (int) d2) {
			result = 1;
		} else {
			result = 0;
		}

		return result;
	}

}
