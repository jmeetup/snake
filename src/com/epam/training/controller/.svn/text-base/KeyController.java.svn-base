/**
 * 
 */
package com.epam.training.controller;

import java.awt.EventQueue;

import com.epam.training.model.SnakeModelInterface;
import com.epam.training.view.SnakeView;

/**
 * @author Oleg_Burshinov
 * 
 */
public class KeyController implements ControllerInterface {

	private SnakeModelInterface model;
	private SnakeView view;
	private ControllerInterface controller;
	private int[] gameParams;
//	private static Logger logger = Logger.getLogger("com.epam.training");

	/**
	 * Create view and connect to model.
	 * @param model Game model
	 * @param args Commands from command line
	 * @param cellPix Cell pixel size
	 */
	public KeyController(final SnakeModelInterface model, int args[],
			final int cellPix) {
		this.model = model;
		gameParams = args;
		this.controller = this;
		initialize(gameParams);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
//				Handler windowHandler = new WindowHandler();
//				windowHandler.setLevel(Level.ALL);
//				logger.addHandler(windowHandler);
				view = new SnakeView(controller, model, cellPix);
				view.createView();
				view.disableStopButton();
				view.disablePauseButton();
				view.enableStartButton();
			}
		});

	}

	public void start() {
		model.on();
		view.disableStartButton();
		view.enableStopButton();
		view.enablePauseButton();
		view.updateView();
	}

	public void stop() {
		model.off();
		view.disableStopButton();
		view.disablePauseButton();
		view.enableStartButton();
		model.initialize();
		view.updateView();
	}

	public void pause() {
		model.pause();
		view.disablePauseButton();
		view.enableStartButton();
		view.enableStopButton();
	}

	public void initialize(int[] args) {
		model.initialize(args);

	}

	public void turnLeft() {
		if (!model.isPause()&& model.getGameState()) {
			model.snakeGoLeft();
		}
	}

	public void turnRight() {
		if (!model.isPause() && model.getGameState()) {
			model.snakeGoRight();
		}
	}

}
