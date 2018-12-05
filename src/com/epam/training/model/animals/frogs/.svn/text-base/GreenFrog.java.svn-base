package com.epam.training.model.animals.frogs;

import com.epam.training.model.SnakeModelInterface;
import com.epam.training.model.animals.Frog;
import com.epam.training.model.animals.behavior.Increases;
import com.epam.training.model.area.Field;

/**
 * @author Oleg_Burshinov Green frog
 */
public class GreenFrog extends Frog {

	/**
	 * @param sleep
	 *            How long frog sleep before jump to next cell
	 * @param field
	 *            Game field
	 * @param model
	 *            Game model
	 */
	public GreenFrog(int sleep, Field field, SnakeModelInterface model) {
		super(sleep, field, model);
		super.eat = new Increases(model);
	}

}
