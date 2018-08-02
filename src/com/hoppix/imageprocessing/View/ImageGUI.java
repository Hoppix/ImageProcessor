package com.hoppix.imageprocessing.View;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class ImageGUI
{
	private JFrame frame;
	private JPanel panel;

	public ImageGUI()
	{
		frame = new JFrame();
		panel = new JPanel();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void updatePanel(BufferedImage iterationImage)
	{
		JLabel label = new JLabel(new ImageIcon(iterationImage));

		panel = new JPanel();
		panel.add(label);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

}
