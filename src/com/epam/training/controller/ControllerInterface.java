package com.epam.training.controller;


/**
 * @author Oleg_Burshinov
 * This interface use to connect and change game model state.
 */
public interface ControllerInterface {
	/**
	 * Initialize model
	 * @param args commands from command line
	 */
	void initialize(int[] args);
	
	
	/**
	 *  Set model state to start game.  
	 */
	void start();

	/**
	 * Set model state to pause game. 
	 */
	void pause();
	
	/**
	 * Set model state to stop game.  
	 */
	void stop();
	
	/**
	 * When user want turn snake left.   
	 */
	void turnLeft();
	
	/**
	 * When user want turn snake right.  
	 */
	void turnRight();
}
