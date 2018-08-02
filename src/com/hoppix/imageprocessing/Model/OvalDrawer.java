package com.hoppix.imageprocessing.Model;

import java.awt.*;
import java.util.Random;

public class OvalDrawer implements Drawable
{
	@Override
	public void drawRandom(Graphics g, int x, int y)
	{
		drawRandomOval(g, x, y);
	}


	private void drawRandomOval(Graphics gA, int x, int y)
	{
		/**
		 * Create random values for Image
		 */
		int x1 = (int) (0 + (Math.random() * (x)));
		int y1 = (int) (0 + (Math.random() * (y)));
		int x2 = (int) (0 + (Math.random() * (x)));
		int y2 = (int) (0 + (Math.random() * (y)));

		double r = new Random().nextFloat() * (1.0 + 0.0000000001);
		double g = new Random().nextFloat() * (1.0 + 0.0000000001);
		double b = new Random().nextFloat() * (1.0 + 0.0000000001);
		Color c = new Color((float) r, (float) g, (float) b, 0.5F);
		gA.setColor(c);

		drawOval(gA, x1, y1, x2, y2);
	}
	private void drawOval(Graphics g, int x1, int y1, int x2, int y2)
	{
		g.fillOval(x1, y1, Math.abs(x2 - x1), Math.abs(y2 - y1));
	}


}
