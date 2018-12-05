package com.epam.training.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

/**
 * @author Oleg_Burshinov Capture mouse press event and invoke controller.
 */
public class MousPressAdapter extends MouseAdapter {

	/**
	 * @param controller
	 *            game controller
	 */
	public MousPressAdapter(ControllerInterface controller) {
		this.controller = controller;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		boolean left = SwingUtilities.isLeftMouseButton(e);
		boolean right = SwingUtilities.isRightMouseButton(e);

		if (left) {
			controller.turnLeft();
		} else if (right) {
			controller.turnRight();
		}
	}

	private ControllerInterface controller;

}
