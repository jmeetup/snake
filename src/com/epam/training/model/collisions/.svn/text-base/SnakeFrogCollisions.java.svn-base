package com.epam.training.model.collisions;

import com.epam.training.model.SnakeModelInterface;
import com.epam.training.model.animals.Frog;
import com.epam.training.model.animals.Snake;

/**
 * @author Oleg_Burshinov
 * 
 */
public class SnakeFrogCollisions implements CollisionsInterface {

	private Frog frog;
	private SnakeModelInterface model;

	/**
	 * @param snake
	 *            Snake
	 * @param frog
	 *            Frog
	 * @param model
	 *            game model
	 */
	public SnakeFrogCollisions(Snake snake, Frog frog, SnakeModelInterface model) {
		this.frog = frog;
		this.model = model;
	}

	public void solve() {
		frog.performEat();
		frog.cancel();
		model.addFrog();
	}

}
