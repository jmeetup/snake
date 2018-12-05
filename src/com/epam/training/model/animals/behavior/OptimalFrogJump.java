package com.epam.training.model.animals.behavior;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import com.epam.training.model.SnakeModelInterface;
import com.epam.training.model.animals.Snake;
import com.epam.training.model.area.Cell;
import com.epam.training.model.area.Field;

/**
 * @author Oleg_Burshinov Search max far cell
 */
public class OptimalFrogJump {

	private ArrayList<Cell> cellsToJump = new ArrayList<Cell>();
	private Snake snake;
	private SnakeModelInterface model;
	private Field field;

	/**
	 * @param x
	 *            Coordinate
	 * @param y
	 *            Coordinate
	 * @param model
	 *            Game model
	 */
	public OptimalFrogJump(int x, int y, SnakeModelInterface model) {

		this.model = model;
		this.snake = model.getSnake();
		this.field = model.getField();
		calculate(x, y);

	}

	private void calculate(int x, int y) {

		boolean oneOver = false;
		boolean twoOver = false;
		boolean threeOver = false;
		boolean fourOver = false;

		if ((x + 1) == field.getWidth()) {
			int xTmp = -1;
			Cell three = field.getCell(xTmp + 1, y);
			cellsToJump.add(three);
			threeOver = true;
		} else if ((x - 1) == -1) {
			int xTmp = field.getWidth();
			Cell two = field.getCell(xTmp - 1, y);
			cellsToJump.add(two);
			twoOver = true;
		}
		if ((y + 1) == field.getHeight()) {
			int yTmp = -1;
			Cell four = field.getCell(x, yTmp + 1);
			cellsToJump.add(four);
			fourOver = true;
		} else if ((y - 1) == -1) {
			int yTmp = field.getHeight();
			Cell one = field.getCell(x, yTmp - 1);
			cellsToJump.add(one);
			oneOver = true;
		}

		if (!oneOver) {
			Cell one = field.getCell(x, y - 1);
			cellsToJump.add(one);
		}
		if (!twoOver) {
			Cell two = field.getCell(x - 1, y);
			cellsToJump.add(two);
		}
		if (!threeOver) {
			Cell three = field.getCell(x + 1, y);
			cellsToJump.add(three);
		}
		if (!fourOver) {
			Cell four = field.getCell(x, y + 1);
			cellsToJump.add(four);
		}

	}

	/**
	 * @return sorted cells - first max far from snake head
	 */
	public ArrayList<Cell> findDirections() {

		LinkedList<Cell> snakeCells = snake.getSnakeCells();
		Cell snakeHead = snakeCells.getFirst();

		HeadDistCoparator hc = new HeadDistCoparator(snakeHead, model);

		Collections.sort(cellsToJump, Collections.reverseOrder(hc));

		return cellsToJump;
	}
}
