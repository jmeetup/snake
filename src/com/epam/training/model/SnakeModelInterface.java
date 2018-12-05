package com.epam.training.model;

import com.epam.training.model.animals.Snake;
import com.epam.training.model.area.Cell;
import com.epam.training.model.area.Field;

/**
 * @author Oleg_Burshinov
 * 
 */
public interface SnakeModelInterface {
	/**
	 * Set game score to 0. Create game field W x H. Create snake. Set frogs
	 * sleep time (snake sleep * 2) Create frogs array. Create thread pool =
	 * frogs + snake Save all initial parameters.
	 * 
	 * @param args
	 *            Commands from command line.
	 * 
	 */
	void initialize(int[] args);

	/**
	 * Change model game statement to ON. If between statement PAUSE wake up
	 * model If between statement STOP go initialize and ON
	 * 
	 * @see #initialize(int[] args)
	 */
	
	
	
	public void on();

	/**
	 * Initialize model when hot start game 
	 */
	void initialize();
	
	
	/**
	 * Change model game statement to OFF. Interrupt all thread in pool
	 */
	public void off();

	/**
	 * Change game statement variable to true
	 */
	public void setGameOn();

	/**
	 * Change game statement variable to false Snake can't go
	 */
	public void setGameOff();

	/**
	 * Add random type frog to tread pool
	 */
	public void addFrog();

	/**
	 * @return game statement variable
	 */
	public boolean getGameState();

	/**
	 * add some observer to listen this model
	 * 
	 * @param o
	 *            observer
	 */
	public void registerViewObserver(ViewObserver o);

	/**
	 * remove observer
	 * 
	 * @param o
	 *            observer
	 */
	public void removeViewObserver(ViewObserver o);

	/**
	 * Notify observers when model statement changed.
	 */
	public void notifyViewObservers();

	/**
	 * @return field width
	 */
	public int fieldWidth();

	/**
	 * @return field height
	 */
	public int fieldHeight();

	/**
	 * Change snake move direction to left
	 */
	public void snakeGoLeft();

	/**
	 * Change snake move direction to right
	 */
	public void snakeGoRight();

	/**
	 * @return link to Snake object
	 */
	public Snake getSnake();

	/**
	 * increase game score to one
	 */
	public void incScore();

	/**
	 * @return game score
	 */
	public int getScore();

	/**
	 * @return game last score
	 */
	public int getLastScore();
	
	/**
	 * @return variable snake can move
	 */
	public boolean canSnakeGo();

	/**
	 * @return link to game field
	 */
	public Field getField();

	/**
	 * variable statement game pause
	 */
	void pause();

	/**
	 * @return variable statement game pause
	 */
	public boolean isPause();

	/**
	 * @return object to synchronize
	 */
	public Object getO();

	/**
	 * Set changed cells in model to update
	 * 
	 * @see #notifyViewObservers()
	 * @param c1
	 *            changed cell
	 * @param c2
	 *            changed cell
	 */
	void changedCells(Cell c1, Cell c2);

}
