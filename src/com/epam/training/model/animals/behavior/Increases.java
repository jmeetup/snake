package com.epam.training.model.animals.behavior;

import com.epam.training.model.SnakeModelInterface;

/**
 * @author Oleg_Burshinov
 *	Snake grow up
 */
public class Increases implements EatableBehavior {

	private SnakeModelInterface model;
	
	/**
	 * @param model Game model
	 */
	public Increases(SnakeModelInterface model) {
		this.model = model;
	}
	
	/**
	 *	when snake eat frog - snake grow up
	 */
	public void eaten() {
		model.getSnake().growup();
		model.incScore();
	}

}
