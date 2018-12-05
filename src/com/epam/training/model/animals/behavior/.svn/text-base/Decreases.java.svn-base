package com.epam.training.model.animals.behavior;

import com.epam.training.model.SnakeModelInterface;

/**
 * @author Oleg_Burshinov
 * Decreases Behavior 
 */
public class Decreases implements EatableBehavior {

	private SnakeModelInterface model;
	
	/**
	 * @param model Game model
	 */
	public Decreases(SnakeModelInterface model) {
		this.model = model;
	}
	
	/**
	 * when snake eat frog - snake grow down 
	 * and game score inc twice  
	 */
	public void eaten() {
		model.getSnake().growdown();
		model.incScore();
		model.incScore();
	}

}
