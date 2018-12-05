package com.epam.training.model.animals;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.epam.training.model.SnakeModelInterface;
import com.epam.training.model.area.Cell;
import com.epam.training.model.area.Field;
import com.epam.training.model.collisions.AmphibiaCollisions;
import com.epam.training.model.collisions.CollisionsInterface;

/**
 * @author Oleg_Burshinov
 * 
 */
public class Snake extends Amphibia {

	private int size = 0;
	private int sleep = 0;
	private SnakeModelInterface model;
	private final int MILLIS_IN_SEC = 300; // default 1000
	private boolean horizontal = true;
	private boolean vertical = false;
	private boolean forward = true;
	private boolean backward = false;
	private boolean up = false;
	private boolean down = false;
	private volatile boolean left = false;
	private volatile boolean right = false;
	private CollisionsInterface collision;
	private volatile boolean cancel = false;
	private Field field;
	private LinkedList<Cell> snakeCells = new LinkedList<Cell>();
	private static Logger logger = Logger.getLogger("com.epam.training");
	private static final String SNAKE_THREAD_ITERUPTED = "Snake thread interrupted";

	/**
	 * Initialize snake
	 * 
	 * @param size
	 *            Snake length
	 * @param sleep
	 *            How long snake sleep (seconds)
	 * @param field
	 *            Game field
	 * @param model
	 *            Game model
	 */
	public Snake(int size, int sleep, Field field, SnakeModelInterface model) {
		this.size = size;
		this.sleep = sleep * MILLIS_IN_SEC;
		this.field = field;
		this.model = model;
		startPosition();
	}

	/**
	 * 
	 * @param x
	 *            X coordinate
	 * @param y
	 *            Y coordinate
	 * @return true if this snake head
	 */
	public boolean isHeadCell(int x, int y) {
		boolean result = false;
		if (!snakeCells.isEmpty()) {
			int headX = snakeCells.get(0).getX();
			int headY = snakeCells.get(0).getY();
			if ((headX == x) && (headY == y)) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param x
	 *            X coordinate
	 * @param y
	 *            Y coordinate
	 * @return true if this snake tail
	 */
	public boolean isTailCell(int x, int y) {
		boolean result = false;
		if (!snakeCells.isEmpty()) {
			int headX = snakeCells.getLast().getX();
			int headY = snakeCells.getLast().getY();
			if ((headX == x) && (headY == y)) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Snake turn left
	 */
	public void left() {
		this.left = true;
	}

	/**
	 * Snake turn right
	 */
	public void right() {
		this.right = true;
	}

	/**
	 * @return all snake cells on field
	 */
	public synchronized LinkedList<Cell> getSnakeCells() { // synchronized
		return snakeCells;
	}

	/**
	 * Snake move and user control direction
	 * 
	 */
	@Override
	void move() {
		Cell headOld = snakeCells.getFirst();
		Cell body1Old = snakeCells.get(1);
		Cell tailOld = snakeCells.getLast();
		int last = snakeCells.indexOf(tailOld);
		Cell body2Old = snakeCells.get(last - 1);
		synchronized (field) {

			if (horizontal && right && forward) {
				vertical = true;
				horizontal = false;
				right = false;
				down = true;
				up = false;
				int headX = snakeCells.get(0).getX();
				int headY = snakeCells.get(0).getY();
				if ((headY + 1) == field.getHeight()) {
					headY = -1;
				}
				toPosition(headX, headY + 1);
			} else if (horizontal && right && backward) {
				vertical = true;
				horizontal = false;
				right = false;
				down = false;
				up = true;
				int headX = snakeCells.get(0).getX();
				int headY = snakeCells.get(0).getY();
				if ((headY - 1) == -1) {
					headY = field.getHeight();
				}
				toPosition(headX, headY - 1);
			} else if (horizontal && left && forward) {
				vertical = true;
				horizontal = false;
				left = false;
				up = true;
				down = false;
				int headX = snakeCells.get(0).getX();
				int headY = snakeCells.get(0).getY();
				if ((headY - 1) == -1) {
					headY = field.getHeight();
				}
				toPosition(headX, headY - 1);
			} else if (horizontal && left && backward) {
				vertical = true;
				horizontal = false;
				left = false;
				down = true;
				up = false;
				int headX = snakeCells.get(0).getX();
				int headY = snakeCells.get(0).getY();
				if ((headY + 1) == field.getHeight()) {
					headY = -1;
				}
				toPosition(headX, headY + 1);
			} else if (horizontal && forward) {
				int headX = snakeCells.get(0).getX();
				int headY = snakeCells.get(0).getY();
				if ((headX + 1) == field.getWidth()) {
					headX = -1;
				}
				toPosition(headX + 1, headY);
			} else if (horizontal && backward) {
				int headX = snakeCells.get(0).getX();
				int headY = snakeCells.get(0).getY();
				if ((headX - 1) == -1) {
					headX = field.getWidth();
				}
				toPosition(headX - 1, headY);
			} else if (vertical && right && down) {
				horizontal = true;
				vertical = false;
				right = false;
				backward = true;
				forward = false;
				int headX = snakeCells.get(0).getX();
				int headY = snakeCells.get(0).getY();
				if ((headX - 1) == -1) {
					headX = field.getWidth();
				}
				toPosition(headX - 1, headY);
			} else if (vertical && right && up) {
				horizontal = true;
				vertical = false;
				right = false;
				backward = false;
				forward = true;
				int headX = snakeCells.get(0).getX();
				int headY = snakeCells.get(0).getY();
				if ((headX + 1) == field.getWidth()) {
					headX = -1;
				}
				toPosition(headX + 1, headY);
			} else if (vertical && left && down) {
				horizontal = true;
				vertical = false;
				left = false;
				forward = true;
				backward = false;
				int headX = snakeCells.get(0).getX();
				int headY = snakeCells.get(0).getY();
				if ((headX + 1) == field.getWidth()) {
					headX = -1;
				}
				toPosition(headX + 1, headY);
			} else if (vertical && left && up) {
				horizontal = true;
				vertical = false;
				left = false;
				backward = true;
				forward = false;
				int headX = snakeCells.get(0).getX();
				int headY = snakeCells.get(0).getY();
				if ((headX - 1) == -1) {
					headX = field.getWidth();
				}
				toPosition(headX - 1, headY);
			} else if (vertical && down) {
				int headX = snakeCells.get(0).getX();
				int headY = snakeCells.get(0).getY();
				if ((headY + 1) == field.getHeight()) {
					headY = -1;
				}
				toPosition(headX, headY + 1);
			} else if (vertical && up) {
				int headX = snakeCells.get(0).getX();
				int headY = snakeCells.get(0).getY();
				if ((headY - 1) == -1) {
					headY = field.getHeight();
				}
				toPosition(headX, headY - 1);
			}

			Cell newHead = snakeCells.getFirst();
			model.changedCells(headOld, newHead);
			model.notifyViewObservers();
			Cell body1 = snakeCells.get(1);
			model.changedCells(body1Old, body1);
			model.notifyViewObservers();
			Cell tail = snakeCells.getLast();
			model.changedCells(tailOld, tail);
			model.notifyViewObservers();
			int last2 = snakeCells.indexOf(tail);
			Cell body2 = snakeCells.get(last2 - 1);
			model.changedCells(body2Old, body2);
			model.notifyViewObservers();
		}

	}

	/**
	 * Snake reaction to changed model statements
	 * 
	 */
	public void run() {
		try {
			while (!cancel) {
				if (!model.isPause()) {

					if (model.canSnakeGo()) {
						move();
						TimeUnit.MILLISECONDS.sleep(sleep);
					} else {
						while (!model.canSnakeGo()) {
							synchronized (model.getO()) {
								model.getO().wait();
							}
						}
					}
				} else {

					while (model.isPause()) {
						// logger.log(Level.FINE, "Snake go pause");
						synchronized (model.getO()) {
							model.getO().wait();
						}
					}
				}
			}
			// logger.log(Level.FINE,"Snake die");
			model.off();
		} catch (InterruptedException e) {
			logger.log(Level.FINE, SNAKE_THREAD_ITERUPTED);
		}
	}

	/**
	 * Snake thread release
	 */
	public void cancel() {
		cancel = true;
	}

	/**
	 * Snake grow up to one cell
	 */
	public void growup() {
		size++;
		Cell c = new Cell();
		c.setState(this);
		snakeCells.add(c);
	}

	/**
	 * Snake grow down to one cell
	 */
	public void growdown() {
		if (size > 3) {
			Cell tmp = snakeCells.getLast();
			size--;
			Cell cOld = snakeCells.getLast();
			int index = snakeCells.indexOf(cOld);
			synchronized (field) {
				cOld.setState(null);
				model.changedCells(cOld, tmp);
				model.notifyViewObservers();
			}
			snakeCells.remove(index);

		}

	}

	/**
	 * Snake go to cell
	 */
	private void toPosition(int x, int y) {

		boolean tail = false;
		if (isTailCell(x, y)) {
			tail = true;
		}
		Cell place = new Cell();
		place = field.getCell(x, y);
		if (place.getAmphibia() != null) {
			collision = new AmphibiaCollisions(this, place.getAmphibia(),
					this.model);
			collision.solve();
		}
		int tmpX = 0;
		int tmpY = 0;
		Cell cOld = new Cell();
		cOld = snakeCells.get(size - 1);
		Cell cNew = new Cell();
		for (int i = 0; i < size; i++) {
			tmpX = snakeCells.get(i).getX();
			tmpY = snakeCells.get(i).getY();
			cNew = field.getCell(x, y);
			snakeCells.set(i, cNew);
			cNew.setState(this);
			x = tmpX;
			y = tmpY;
		}
		if (tail) {
			cOld.setState(this);
		} else {
			cOld.setState(null);
		}
		collision = new AmphibiaCollisions(this, this, this.model);
		collision.solve();
	}
	
	/**
	 * Snake setup on game field
	 */
	private void startPosition() {
		horizontal = true;
		int fWidth = field.getWidth();
		if (size <= fWidth) {
			synchronized (field) {
				for (int i = size; i > 0; i--) {
					snakeCells.add(field.getCell(i - 1, 0));
					field.getCell(i - 1, 0).setState(this);
				}
			}
		}

	}
}
