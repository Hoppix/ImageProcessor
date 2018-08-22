package com.hoppix.imageprocessing.Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class TriangleDrawer extends AbstractDrawer implements Drawable
{
	public TriangleDrawer(BufferedImage original)
	{
		super(original);
	}

	@Override
	public void drawRandom(Graphics g, int x, int y)
	{
		drawRandomTriangle(g, x, y);
	}

	private void drawRandomTriangle(Graphics gA, int x, int y)
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
		Color c = getRandomImageColor();
		gA.setColor(c);

		drawTriangle(gA, x1, y1, x2, y2);
	}

	private void drawTriangle(Graphics g, int x1, int y1, int x2, int y2)
	{
		int[] arrayX = {x1, x2, (x1 + x2) / 2};
		int[] arrayY = {y2, y2, y1};

		Polygon p = new Polygon(arrayX, arrayY, 3);
		g.fillPolygon(p);
	}
}
