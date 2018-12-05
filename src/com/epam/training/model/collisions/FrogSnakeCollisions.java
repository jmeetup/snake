package com.epam.training.model.collisions;

import com.epam.training.model.SnakeModelInterface;
import com.epam.training.model.animals.Frog;
import com.epam.training.model.animals.Snake;

/**
 * @author Oleg_Burshinov
 * 
 */
public class FrogSnakeCollisions implements CollisionsInterface {

	Frog frog;

	/**
	 * @param s2
	 *            Snake
	 * @param f1
	 *            Frog
	 * @param model
	 *            Game model
	 */
	public FrogSnakeCollisions(Snake s2, Frog f1, SnakeModelInterface model) {
		this.frog = f1;
	}

	public void solve() {
		frog.setJump(false);
	}

}
