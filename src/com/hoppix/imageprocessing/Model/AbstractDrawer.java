package com.hoppix.imageprocessing.Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class AbstractDrawer
{
	protected ArrayList<Color> imageColors;

	public AbstractDrawer(BufferedImage original)
	{
		imageColors = getImageColors(original);
	}

	private ArrayList<Color> getImageColors(BufferedImage image)
	{
		ArrayList<Color> colors = new ArrayList<>();

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				colors.add(new Color(image.getRGB(x, y)));
			}
		}
		return colors;
	}

	protected Color getRandomImageColor()
	{
		Color noAlphaColor = getRandomColor(imageColors);
		return new Color(noAlphaColor.getRed(),noAlphaColor.getGreen(),noAlphaColor.getBlue(),127);
	}

	protected Color getRandomColor(ArrayList<Color> colors)
	{
		int index = (int)(Math.random()*colors.size()-1);
		return colors.get(index);
	}
}
