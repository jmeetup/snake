package com.epam.training.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.epam.training.controller.ControllerInterface;
import com.epam.training.model.SnakeModelInterface;
import com.epam.training.model.ViewObserver;
import com.epam.training.model.area.Cell;

/**
 * @author Oleg_Burshinov Game view frame
 */
@SuppressWarnings("serial")
public class SnakeView extends JFrame implements ViewObserver {

	SnakeModelInterface model;
	ControllerInterface controller;
	boolean onlyUpdate = false;
	Cell newCell = new Cell();
	Cell oldCell = new Cell();
	JPanel buttonPanel;
	JLabel scoreLabel;
	JButton startBtn;
	JButton pauseBtn;
	JButton stopBtn;
	SnakeLabel viewLabel;
	int pixSize = 0;

	/**
	 * @param controller
	 *            game controller
	 * @param model
	 *            game model
	 * @param pixSize
	 *            Size cell in pixel
	 */
	public SnakeView(ControllerInterface controller, SnakeModelInterface model,
			int pixSize) {
		this.controller = controller;
		this.model = model;
		this.pixSize = pixSize;

		model.registerViewObserver(this);

	}

	/**
	 * Constructor initialize frame
	 * 
	 * @see #createView()
	 */
	public SnakeView() {
		createView();
	}

	/**
	 * Change painting from paint all to paint only update cell
	 */
	public void updateView() {
		onlyUpdate = false;
	}

	/**
	 * Create view stuff
	 */
	public void createView() {

		viewLabel = new SnakeLabel(this.model, this.controller, this, pixSize);
		viewLabel.setBackground(Color.black);
		viewLabel.setOpaque(true);
		JScrollPane scrollPane = new JScrollPane(viewLabel);
		buttonPanel = new JPanel(new GridLayout(1, 3));
		startBtn = new JButton("Start");
		pauseBtn = new JButton("Pause");
		stopBtn = new JButton("Stop");
		scoreLabel = new JLabel(" SCORE: ");
		buttonPanel.add(startBtn);
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.start();

			}
		});
		buttonPanel.add(pauseBtn);
		pauseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.pause();

			}
		});
		buttonPanel.add(stopBtn);
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.stop();
			}
		});
		buttonPanel.add(scoreLabel);
		setTitle("Snake game");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		screenSize.height = screenSize.height / 2;
		screenSize.width = screenSize.width / 2;
		setSize(screenSize);
		setLocationByPlatform(true);
		add(buttonPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	/**
	 * enable Pause Button
	 */
	public void enablePauseButton() {
		pauseBtn.setEnabled(true);
	}

	/**
	 * disable Pause Button
	 */
	public void disablePauseButton() {
		pauseBtn.setEnabled(false);
	}

	/**
	 * enable Start Button
	 */
	public void enableStartButton() {
		startBtn.setEnabled(true);
	}

	/**
	 * disable Start Button
	 */
	public void disableStartButton() {
		startBtn.setEnabled(false);
	}

	/**
	 * enable Stop Button
	 */
	public void enableStopButton() {
		stopBtn.setEnabled(true);
	}

	/**
	 * disable Stop Button
	 */
	public void disableStopButton() {
		stopBtn.setEnabled(false);
	}

	public void update(Cell cellNew, Cell cellOld) {

		this.oldCell = cellOld;
		this.newCell = cellNew;
		viewLabel.update(cellNew, cellOld);
		Integer score = model.getScore();
		scoreLabel.setText(" SCORE: " + score.toString());
		buttonPanel.repaint();
	}

}
