/**
 * MVC pattern structure
 */
package com.epam.training;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.epam.training.controller.ControllerInterface;
import com.epam.training.controller.KeyController;
import com.epam.training.model.SnakeModel;
import com.epam.training.model.SnakeModelInterface;

/**
 * ValidateSnakeGameRunner get other parameters for initialize game with some state like: how
 * many frogs, what size of field, snake size, snake sleep time (seconds)
 * 
 * @author Oleg_Burshinov
 * 
 */
public class ValidateSnakeGameRunner {

	/**
	 * @param args commands from command line [0] all frogs [1] field w 
	 * [2] field h [3] snake length [4] snake sleep time
	 * 
	 */

	public static void main(String[] args) {



		int[] initial = new int[5];
		if (args.length != 5) {
			defaultState();
			initial = defaultParams;
			start(initial);
		} else {
			try {
				for (int i = 0; i < initial.length; i++) {
					initial[i] = Integer.parseInt(args[i]);
				}
			} catch (NumberFormatException e) {
				defaultState();
				initial = defaultParams;
			}
			boolean isConsolParamsGood = isInitialParamGood(initial);
			if (isConsolParamsGood) {
				start(initial);
			} else {
				defaultState();
				initial = defaultParams;
				start(initial);
			}
		}
	}

	/**
	 * Show warning message.
	 */
	private static void defaultState() {
		System.out.println(WRONG_START_PARAMETERS);
		System.out.println(START_DEFAULT_PARAMS);
	}

	/**
	 * Create game model with and controller
	 * 
	 * @param initial
	 *            game start parameters
	 * 
	 */
	private static void start(int[] initial) {
		SnakeModelInterface model = new SnakeModel();
		@SuppressWarnings("unused")
		ControllerInterface controller = new KeyController(model, initial,
				DEFAULT_CELL_PIX_SIZE);
	}

	/**
	 * Validate user input parameters with game logic
	 * 
	 * @param args
	 *            user game start parameters
	 * @return true if valid false if not valid
	 */
	private static boolean isInitialParamGood(int[] args) {
		boolean result = true;
		int w = args[1];
		int h = args[2];
		int allFrogs = args[0];
		int snakeSize = args[3];
		int snakeSleep = args[4];

		if ((w < 0 || w > 200) || (h < 0 || h > 200)) {
			logger.log(Level.INFO, WRONG_FIELD);
			result = false;
		}

		if (snakeSize < 2 || snakeSize > w) {

			logger.log(Level.WARNING, WRONG_SNAKE_1 + w + CELLL_X + h
					+ WRONG_SNAKE_2 + w + ".");
			result = false;
		}

		if (snakeSleep < 1 || snakeSleep > 10) {
			logger.log(Level.INFO, WRONG_SLEEP);
			result = false;
		}
		int allCellsOnField = w * h;
		int maxFrog = (allCellsOnField - snakeSize);
		if (allFrogs >= 0 && allFrogs <= maxFrog) {
		} else {
			logger.log(Level.INFO, WRONG_FROGS_A1 + w + CELLL_X + h
					+ WRONG_FROGS_A3 + snakeSize + WRONG_FROGS_A4 + maxFrog
					+ ".");
			result = false;
		}
		return result;
	}

	private static Logger logger = Logger.getLogger("com.epam.training");
	private static final String WRONG_SNAKE_2 = " cells). Min snake size 2. Maximum snake size on this field ";
	private static final String WRONG_SNAKE_1 = "Bad snake size. yours settings field (";
	private static final String WRONG_FROGS_A4 = ". On this field min forgs 0, max frogs: ";
	private static final String WRONG_FROGS_A3 = " cells). Snake legth ";
	private static final String CELLL_X = " cells x ";
	private static final String WRONG_FROGS_A1 = "Wrong frogs amount: yours settings field (";
	private static final String WRONG_FIELD = "Wrong field size. Maximum (200 cells x 200 cells).";
	private static final String WRONG_SLEEP = "Wrong snake sleep time. Min 1 sec. Max 10 sec.";
	private static final String WRONG_START_PARAMETERS = "Parameters error.";
	private static final String START_DEFAULT_PARAMS = "Game start with default parameters";
	private static final int DEFAULT_CELL_PIX_SIZE = 14;
	private static int[] defaultParams = { 40, 10, 10, 3, 1 };
}
