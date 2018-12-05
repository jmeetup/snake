package com.epam.training.model.area;

/**
 * @author Oleg_Burshinov Game field - all cells with coordinates
 */
public class Field {
	private int width;
	private int height;
	private Cell[][] cells;

	/**
	 * Set field w|h
	 * 
	 * @param width
	 *            field
	 * @param height
	 *            field
	 */
	public Field(int width, int height) {

		this.width = width;
		this.height = height;
		setField();
	}

	/**
	 * @param x
	 *            coordinate
	 * @param y
	 *            coordinate
	 * @return cell this coordinates
	 */
	public Cell getCell(int x, int y) {

		return cells[x][y];

	}

	/**
	 * @return field width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return field height
	 */
	public int getHeight() {
		return height;
	}

	private void setField() {
		cells = new Cell[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				cells[i][j] = new Cell();
				cells[i][j].setX(i);
				cells[i][j].setY(j);
			}
		}
	}
}
