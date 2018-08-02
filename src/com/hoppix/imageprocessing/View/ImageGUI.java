package com.hoppix.imageprocessing.View;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class ImageGUI
{
	private JFrame frame;
	private JPanel panel;
	private JLabel label;

	public ImageGUI()
	{
		frame = new JFrame();
		panel = new JPanel();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void updatePanel(BufferedImage iterationImage)
	{
		panel = new JPanel();
		label = new JLabel(new ImageIcon(iterationImage));
		panel.add(label);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

}
