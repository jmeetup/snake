package com.epam.training.model.area;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.epam.training.model.animals.Amphibia;
import com.epam.training.model.animals.Frog;
import com.epam.training.model.animals.Snake;
import com.epam.training.model.animals.frogs.BlueFrog;
import com.epam.training.model.animals.frogs.GreenFrog;
import com.epam.training.model.animals.frogs.RedFrog;

/**
 * @author Oleg_Burshinov Cell on field place to some Amphibians or empty space
 */
public class Cell {

	private Amphibia a;
	private int xPos = 0;
	private int yPos = 0;
	private StateCell state;
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private Lock readLock = rwl.readLock();
	private Lock writeLock = rwl.writeLock();

	/**
	 * New cell empty - no amphibians
	 */
	public Cell() {
		a = null;
	}

	/**
	 * @return amphibian on this cell
	 */
	public Amphibia getAmphibia() {
		readLock.lock();
		try {
			return this.a;
		} finally {
			readLock.unlock();
		}

	}

	/**
	 * @param a
	 *            Set cell statement
	 */
	public void setState(Amphibia a) {
		writeLock.lock();
		try {
			if (a != null) {
				this.a = a;
			} else {
				this.a = null;
			}

		} finally {
			writeLock.unlock();
		}

	}

	/**
	 * @return Statement
	 * @see StateCell
	 */
	public StateCell getState() {
		readLock.lock();
		try {
			state = StateCell.EMPTY;
			if (a == null) {
				state = StateCell.EMPTY;
			} else if (a instanceof Snake) {
				if (((Snake) a).isHeadCell(xPos, yPos)) {
					state = StateCell.SNAKE_HEAD;
				} else if (((Snake) a).isTailCell(xPos, yPos)) {
					state = StateCell.SNAKE_TAIL;
				} else {
					state = StateCell.SNAKE_BODY;
				}
			} else if (a instanceof RedFrog) {
				state = StateCell.FROG_RED;
			} else if (a instanceof GreenFrog) {
				state = StateCell.FROG_GREEN;
			} else if (a instanceof BlueFrog) {
				state = StateCell.FROG_BLUE;
			} else if (a instanceof Frog) {
				state = StateCell.FROG;
			}
			return state;
		} finally {
			readLock.unlock();
		}
	}

	/**
	 * @param xPos
	 *            set coordinate x on field
	 */
	public void setX(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * @return cell x coordinate
	 */
	public int getX() {
		return xPos;
	}

	/**
	 * @param yPos
	 *            set coordinate y on field
	 */
	public void setY(int yPos) {
		this.yPos = yPos;
	}

	/**
	 * @return cell y coordinate
	 */
	public int getY() {
		return yPos;
	}

}
