package com.epam.training.model;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.epam.training.model.animals.Frog;
import com.epam.training.model.animals.Snake;
import com.epam.training.model.animals.frogs.BlueFrog;
import com.epam.training.model.animals.frogs.GreenFrog;
import com.epam.training.model.animals.frogs.RedFrog;
import com.epam.training.model.area.Cell;
import com.epam.training.model.area.Field;

/**
 * @author Oleg_Burshinov Particular snake game model.
 */
public class SnakeModel implements SnakeModelInterface {

	private ExecutorService pool;
	private Field field;
	private Snake snake;
	private Frog[] frogs;
	private volatile boolean gameOn = false;
	private volatile boolean gamePause = false;
	private volatile boolean canGo = false;

	private Object o = new Object();
	private int FROG_SLEEP = 1;
	private volatile Cell cellNew = new Cell();
	private volatile Cell cellOld = new Cell();
	private volatile int gameScore = 0;
	private volatile int lastGameScore = 0;
	private ArrayList<ViewObserver> viewObservers = new ArrayList<ViewObserver>();

	private double BLUE_FROG_CREATE_P = 0.004;
	private double RED_FROG_CREAETE_P = 0.03;
//	private double GREEN_FROG_CREATE_P = 1 - (BLUE_FROG_CREATE_P + RED_FROG_CREAETE_P);

	private int[] initParams = new int[5];

	public Object getO() {
		return this.o;
	}

	public void setGameOn() {
		gameOn = true;
	}

	public void setGameOff() {
		gameOn = false;
		canGo = false;
		lastGameScore = gameScore;
		notifyViewObservers();
	}

	public boolean getGameState() {
		return gameOn;
	}

	public void initialize(int[] args) {
		gameScore = 0;
		field = new Field(args[1], args[2]);
		snake = new Snake(args[3], args[4], field, this);
		FROG_SLEEP = args[4] * 2;
		frogs = new Frog[args[0]];
		pool = Executors.newFixedThreadPool(args[0] + 1);
		initParams = args;
	}

	public void incScore() {
		gameScore++;
	}

	public int getScore() {
		return gameScore;
	}

	public synchronized void registerViewObserver(ViewObserver o) {
		viewObservers.add(o);
	}

	public boolean canSnakeGo() {
		return canGo;
	}

	public void removeViewObserver(ViewObserver o) {
		int i = viewObservers.indexOf(o);
		if (i >= 0) {
			viewObservers.remove(i);
		}
	}

	public void notifyViewObservers() {
		for (ViewObserver e : viewObservers) {
			e.update(cellNew, cellOld);
		}
	}

	public int fieldWidth() {
		return field.getWidth();
	}

	public int fieldHeight() {
		return field.getHeight();
	}

	public void snakeGoLeft() {
		snake.left();

	}

	public void snakeGoRight() {
		snake.right();

	}

	public void on() {

		if (gamePause) {
			gamePause = false;
			synchronized (o) {
				o.notifyAll();
			}

		} else {

			pool.submit(snake);
			ArrayList<Frog> allFrogs = new ArrayList<Frog>();
			for (int i = 0; i < frogs.length; i++) {
				double p = Math.random();
				if (p <= BLUE_FROG_CREATE_P) {
					allFrogs.add(new BlueFrog(FROG_SLEEP, field, this));
				} else if (p <= RED_FROG_CREAETE_P) {
					allFrogs.add(new RedFrog(FROG_SLEEP, field, this));
				} else {
					allFrogs.add(new GreenFrog(FROG_SLEEP, field, this));
				}
			}

			for (int i = 0; i < frogs.length; i++) {
				pool.submit(frogs[i] = allFrogs.get(i));
			}
			canGo = true;
			synchronized (o) {
				o.notifyAll();
			}
			setGameOn();

		}

	}

	public void pause() {
		gamePause = true;

	}

	public boolean isPause() {
		return gamePause;
	}

	public void off() {
		gamePause = false;
		pool.shutdownNow();
		setGameOff();
	}

	public void changedCells(Cell c1, Cell c2) {

		cellNew = c1;
		cellOld = c2;
	}

	public Snake getSnake() {
		return snake;
	}

	public Field getField() {
		return field;
	}

	public void addFrog() {

		double p = Math.random();
		if (p <= BLUE_FROG_CREATE_P) {
			pool.submit(new BlueFrog(FROG_SLEEP, field, this));
		} else if (p <= RED_FROG_CREAETE_P) {
			pool.submit(new RedFrog(FROG_SLEEP, field, this));
		} else {
			pool.submit(new GreenFrog(FROG_SLEEP, field, this));
		}

	}

	public void initialize() {
		initialize(initParams);
	}

	public int getLastScore() {
		return lastGameScore;
	}

}
