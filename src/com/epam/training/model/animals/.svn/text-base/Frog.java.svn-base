package com.epam.training.model.animals;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.epam.training.model.SnakeModelInterface;
import com.epam.training.model.animals.behavior.EatableBehavior;
import com.epam.training.model.animals.behavior.OptimalFrogJump;
import com.epam.training.model.area.Cell;
import com.epam.training.model.area.Field;
import com.epam.training.model.area.StateCell;
import com.epam.training.model.collisions.AmphibiaCollisions;
import com.epam.training.model.collisions.CollisionsInterface;

/**
 * @author Oleg_Burshinov
 * 
 */
public abstract class Frog extends Amphibia {

	protected EatableBehavior eat;
	private double PROBABILITY_AVOID_SNAKE = 0.8;
	private SnakeModelInterface model;
	private Field field;
	private Cell c;
	private CollisionsInterface collision;
	protected int sleep = 0;
	protected volatile boolean cancel = false;
	private volatile boolean canJump;
	private static Logger logger = Logger.getLogger("com.epam.training");
	private final int MILLIS_IN_SEC = 300;
	private static final String FROG_THREAD_ITERUPTED= "Frog thread interrupted";

	/**
	 * @param sleep
	 *            how long frog sleep between jump
	 * @param field
	 *            game field
	 * @param model
	 *            game model
	 */
	public Frog(int sleep, Field field, SnakeModelInterface model) {
		this.field = field;
		this.model = model;
		this.sleep = sleep * MILLIS_IN_SEC;
		canJump = false;
		startPosition();
	}

	/**
	 * If snake eat frog go something
	 */
	public void performEat() {
		eat.eaten();
	}

	/**
	 * @param canJump
	 *            if cell empty frog can jump there
	 */
	public synchronized void setJump(boolean canJump) {
		this.canJump = canJump;
	}

	/**
	 * Frog smart jump to empty cell or stay here
	 * 
	 */
	@Override
	void move() {
		Cell old = c;
		int x = c.getX();
		int y = c.getY();
		synchronized (field) {
			OptimalFrogJump jump = new OptimalFrogJump(x, y, this.model);
			ArrayList<Cell> cellsToJump = jump.findDirections();
			double p = Math.random();
			Random rnd = new Random();
			int rndPosition = rnd.nextInt(4);
			double randomJumpProbability = 0.99 - PROBABILITY_AVOID_SNAKE;
			if (p < randomJumpProbability) {
				switch (rndPosition) {
				case 0:
					toPosition(cellsToJump.get(0).getX(), cellsToJump.get(0)
							.getY());
					break;
				case 1:
					toPosition(cellsToJump.get(1).getX(), cellsToJump.get(1)
							.getY());
					break;
				case 2:
					toPosition(cellsToJump.get(2).getX(), cellsToJump.get(2)
							.getY());
					break;
				case 3:
					toPosition(cellsToJump.get(3).getX(), cellsToJump.get(3)
							.getY());
					break;
				}
			} else {
				toPosition(cellsToJump.get(0).getX(), cellsToJump.get(0).getY());
			}
			model.changedCells(old, c);
			model.notifyViewObservers();
		}
	}

	/**
	 * Frog reaction to changed model statements
	 * 
	 */
	public void run() {
		try {
			while (!cancel) {
				if (!model.isPause()) {
					move();
					TimeUnit.MILLISECONDS.sleep(sleep);

				} else {

					while (model.isPause()) {
						synchronized (model.getO()) {
							model.getO().wait();
						}
					}

				}
			}

		} catch (InterruptedException e) {
			logger.log(Level.FINE, FROG_THREAD_ITERUPTED);
		}

	}

	/**
	 * Frog die
	 */
	public void cancel() {
		cancel = true;
	}

	/**
	 * New frog search empty cell to place on.
	 */
	public void startPosition() {

		Random random = new Random();
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();
		synchronized (field) {

			for (int i = 0; i < field.getWidth(); i++) {
				for (int j = 0; j < field.getHeight(); j++) {
					if (StateCell.EMPTY == field.getCell(i, j).getState()) {
						emptyCells.add(field.getCell(i, j));
					}
				}
			}
			if (emptyCells.isEmpty()) {
				this.cancel();
			} else {
				int allEmptyCells = emptyCells.size();
				int rndEmpty = random.nextInt(allEmptyCells);
				c = emptyCells.get(rndEmpty);
				c.setState(this);
				canJump = true;
			}
		}
	}

	/**
	 * frog try jump. If empty jump else stay here.
	 * 
	 * @param x
	 *            X coordinate
	 * @param y
	 *            Y coordinate
	 */
	private void toPosition(int x, int y) {
		Cell place = field.getCell(x, y);
		if (place.getAmphibia() != null) {
			collision = new AmphibiaCollisions(this, place.getAmphibia(),
					this.model);
			collision.solve();
		}
		if (canJump) {
			Cell cOld = c;
			Cell cNew = field.getCell(x, y);
			c = cNew;
			cNew.setState(this);
			cOld.setState(null);
		}
		canJump = true;
	}

}
