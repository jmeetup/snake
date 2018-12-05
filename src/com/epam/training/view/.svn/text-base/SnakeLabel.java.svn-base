package com.epam.training.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.epam.training.controller.ControllerInterface;
import com.epam.training.controller.MousPressAdapter;
import com.epam.training.model.SnakeModelInterface;
import com.epam.training.model.area.Cell;
import com.epam.training.model.area.StateCell;

/**
 * @author Oleg_Burshinov Paint all statements in game
 * @see StateCell
 */
@SuppressWarnings("serial")
public class SnakeLabel extends JLabel {

	private SnakeModelInterface model;
	private ControllerInterface controller;
	private SnakeView view;
	private BufferedImage offscreen = new BufferedImage(1, 1,
			BufferedImage.TYPE_INT_RGB);
	private int CELL_PIXEL_SIZE = 28; // 28
	private int OFFSET = CELL_PIXEL_SIZE / 2 - 5;
	private static final String YOUR_SCORE = "Your score ";
	private static final String GAME_OVER = "Game over";

	/**
	 * @param model
	 *            Game model
	 * @param controller
	 *            Game controller
	 * @param view
	 *            Frame
	 * @param pixSize
	 *            Size cell in pixel
	 */
	public SnakeLabel(SnakeModelInterface model,
			final ControllerInterface controller, SnakeView view, int pixSize) {
		CELL_PIXEL_SIZE = pixSize;
		OFFSET = CELL_PIXEL_SIZE / 2 - 5;
		this.model = model;
		this.controller = controller;
		this.view = view;
		MousPressAdapter mousPressAdapter = new MousPressAdapter(controller);
		addMouseListener(mousPressAdapter);
	}

	public void paintAll(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int w = model.fieldWidth() * CELL_PIXEL_SIZE;
		int h = model.fieldHeight() * CELL_PIXEL_SIZE;
		offscreen = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D offGr = offscreen.createGraphics();
		offGr.setColor(getBackground());
		offGr.fillRect(0, 0, w, h);
		offGr.setColor(getForeground());
		for (int i = 0; i < model.fieldWidth(); i++) {
			for (int j = 0; j < model.fieldHeight(); j++) {
				Cell c = model.getField().getCell(i, j);
				offGr = fillBkg(offGr, c);
				offGr = drawCellGrid(offGr, c);
				offGr = paintCell(offGr, c);
			}
		}
		g2.drawImage(offscreen, 0, 0, null);
		Toolkit.getDefaultToolkit().sync();
		g2.dispose();
	}

	private Graphics2D drawCellGrid(Graphics2D offGr, Cell c) {
		int x = c.getX();
		int y = c.getY();
		offGr.setColor(Color.DARK_GRAY);
		offGr.drawRect(CELL_PIXEL_SIZE * x - 1, CELL_PIXEL_SIZE * y - 1,
				CELL_PIXEL_SIZE, CELL_PIXEL_SIZE);
		return offGr;
	}

	/**
	 * When need repaint some cells
	 * 
	 * @param c1
	 *            cell to paint
	 * @param c2
	 *            cell to paint
	 */
	public void update(Cell c1, Cell c2) {
		Graphics2D offGr = (Graphics2D) offscreen.getGraphics();
		Cell oldC = c1;
		Cell newC = c2;
		if (oldC.equals(newC)) {
			offGr = fillBkg(offGr, c1);
			offGr = paintCell(offGr, oldC);
		} else {
			offGr = fillBkg(offGr, c1);
			offGr = fillBkg(offGr, c2);
			offGr = paintCell(offGr, oldC);
			offGr = paintCell(offGr, newC);
		}
		repaint();
	}

	@Override
	public void paint(Graphics g) {

		boolean game = model.getGameState();
		if (!view.onlyUpdate) {
			paintAll(g);
			view.onlyUpdate = true; // CPU warning: if false repaint all field
		}
		if (!game && !view.startBtn.isEnabled()) {
			controller.stop();
			new Thread(new Runnable() {
				public void run() {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, YOUR_SCORE 
							+ model.getLastScore(), GAME_OVER,
							JOptionPane.INFORMATION_MESSAGE);
				}
			}).start();
		}
		if (offscreen != null) {
			g.drawImage(offscreen, 0, 0, null);
			Toolkit.getDefaultToolkit().sync();
			g.dispose();
		}
	}

	private Graphics2D fillBkg(Graphics2D offGr, Cell c) {
		int x = c.getX();
		int y = c.getY();
		offGr.setColor(Color.BLACK);
		offGr.fillRect(CELL_PIXEL_SIZE * x, CELL_PIXEL_SIZE * y,
				CELL_PIXEL_SIZE - 1, CELL_PIXEL_SIZE - 1);
		return offGr;
	}

	private Graphics2D paintCell(Graphics2D offGr, Cell c) {
		int x = c.getX();
		int y = c.getY();
		if (c.getState().equals(StateCell.EMPTY)) {
			fillBkg(offGr, c);
		} else if (c.getState().equals(StateCell.SNAKE_BODY)) {
			offGr.setColor(Color.YELLOW);
			offGr.fillOval((OFFSET) + CELL_PIXEL_SIZE * x, (OFFSET)
					+ CELL_PIXEL_SIZE * y, CELL_PIXEL_SIZE / 3,
					CELL_PIXEL_SIZE / 3);
		} else if (c.getState().equals(StateCell.SNAKE_HEAD)) {
			offGr.setColor(Color.YELLOW);
			offGr.fillOval((OFFSET) + CELL_PIXEL_SIZE * x, (OFFSET)
					+ CELL_PIXEL_SIZE * y, CELL_PIXEL_SIZE / 2,
					CELL_PIXEL_SIZE / 2);
		} else if (c.getState().equals(StateCell.SNAKE_TAIL)) {
			offGr.setColor(Color.YELLOW);
			offGr.fillOval((OFFSET) + CELL_PIXEL_SIZE * x, (OFFSET)
					+ CELL_PIXEL_SIZE * y, CELL_PIXEL_SIZE / 4,
					CELL_PIXEL_SIZE / 4);
		} else if (c.getState().equals(StateCell.FROG_BLUE)) {
			offGr.setColor(Color.BLUE);
			offGr.fillOval((OFFSET) + CELL_PIXEL_SIZE * x, (OFFSET)
					+ CELL_PIXEL_SIZE * y, CELL_PIXEL_SIZE / 3,
					CELL_PIXEL_SIZE / 3);
		} else if (c.getState().equals(StateCell.FROG_RED)) {
			offGr.setColor(Color.RED);
			offGr.fillOval((OFFSET) + CELL_PIXEL_SIZE * x, (OFFSET)
					+ CELL_PIXEL_SIZE * y, CELL_PIXEL_SIZE / 3,
					CELL_PIXEL_SIZE / 3);
		} else if (c.getState().equals(StateCell.FROG_GREEN)) {
			offGr.setColor(Color.GREEN);
			offGr.fillOval((OFFSET) + CELL_PIXEL_SIZE * x, (OFFSET)
					+ CELL_PIXEL_SIZE * y, CELL_PIXEL_SIZE / 3,
					CELL_PIXEL_SIZE / 3);
		}
		return offGr;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(model.fieldWidth() * CELL_PIXEL_SIZE, model
				.fieldHeight()
				* CELL_PIXEL_SIZE);

	}

}
