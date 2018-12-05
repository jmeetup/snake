package com.epam.training.model.animals.behavior;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.epam.training.model.SnakeModelInterface;

/**
 * @author Oleg_Burshinov
 * Die Behavior
 */
public class Die implements EatableBehavior {

	private SnakeModelInterface model;
	private static Logger logger = Logger.getLogger("com.epam.training");
	
	/**
	 * @param model Game model
	 */
	public Die(SnakeModelInterface model){
		this.model = model;
		
	}
	
	/**
	 * When snake eat fog - snake die
	 */
	public void eaten() {
		model.getSnake().cancel();
		logger.log(Level.FINE,"Blue frog kill snake");
	}

}
