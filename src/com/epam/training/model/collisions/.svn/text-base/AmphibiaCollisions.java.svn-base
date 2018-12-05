package com.epam.training.model.collisions;

import com.epam.training.model.SnakeModelInterface;
import com.epam.training.model.animals.Amphibia;
import com.epam.training.model.animals.Frog;
import com.epam.training.model.animals.Snake;

/**
 * @author Oleg_Burshinov Amphibian interaction on field
 */
public class AmphibiaCollisions implements CollisionsInterface {

	private Amphibia a1;
	private Amphibia a2;
	private SnakeModelInterface model;

	/**
	 * @param a1
	 *            Amphibian
	 * @param a2
	 *            Amphibian
	 * @param model
	 *            Game model
	 */
	public AmphibiaCollisions(Amphibia a1, Amphibia a2,
			SnakeModelInterface model) {
		this.a1 = a1;
		this.a2 = a2;
		this.model = model;
	}

	public void solve() {
		Snake s1 = null;
		Snake s2 = null;
		Frog f1 = null;
		Frog f2 = null;
		if ((a1 instanceof Snake)) {
			s1 = (Snake) a1;
		} else if (a1 instanceof Frog) {
			f1 = (Frog) a1;
		}
		if (a2 instanceof Snake) {
			s2 = (Snake) a2;
		} else if (a2 instanceof Frog) {
			f2 = (Frog) a2;
		}

		if ((s1 != null) && (s2 != null)) {
			new SnakeSnakeCollisons(s1, s2, this.model).solve();
		} else if ((f1 != null) && (f2 != null)) {
			new FrogFrogCollisions(f1, f2, this.model).solve(); // frog go on
			// frog
		} else if ((s1 != null) && (f2 != null)) {// snake eat frog
			new SnakeFrogCollisions(s1, f2, this.model).solve();
		} else if ((f1 != null) && (s2 != null)) { // frog go on snake
			new FrogSnakeCollisions(s2, f1, this.model).solve();
		}

	}

}
