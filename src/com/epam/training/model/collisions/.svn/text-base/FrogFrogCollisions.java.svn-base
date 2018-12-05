package com.epam.training.model.collisions;

import com.epam.training.model.SnakeModelInterface;
import com.epam.training.model.animals.Frog;

/**
 * @author Oleg_Burshinov
 * When frog go on frog
 */
public class FrogFrogCollisions implements CollisionsInterface {

	private Frog frog1;
	
	/**
	 * @param frog1 who want jumping frog
	 * @param frog2 frog on cell
	 * @param model  Game model
	 */
	public FrogFrogCollisions(Frog frog1, Frog frog2,
			SnakeModelInterface model) {
		this.frog1 = frog1;
	}
	public void solve() {
		frog1.setJump(false);
	}

}
