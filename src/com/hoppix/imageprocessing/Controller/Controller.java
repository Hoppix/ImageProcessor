package com.hoppix.imageprocessing.Controller;

import com.hoppix.imageprocessing.Model.Drawable;
import com.hoppix.imageprocessing.Model.TriangleDrawer;
import com.hoppix.imageprocessing.View.ImageGUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Controller
{
	private ImageGUI gui;
	private final int iterations = 25000;

	private int imgOriginalHeight;
	private int imgOriginalWidth;

	private BufferedImage imgOriginal;
	private BufferedImage iterationImage;

	private Drawable draw;

	public Controller(ImageGUI gui, String filename)
	{
		this.gui = gui;

		imgOriginal = null;

		try
		{
			imgOriginal = ImageIO.read(new File(filename));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		draw = new TriangleDrawer(imgOriginal);
		imgOriginalHeight = imgOriginal.getHeight();
		imgOriginalWidth = imgOriginal.getWidth();
		createIterationImage(Color.gray);
	}

	public void run()
	{
		BufferedImage processImage = new BufferedImage(imgOriginalWidth, imgOriginalHeight, BufferedImage.TYPE_INT_RGB);
		Graphics processImageGraphics = processImage.createGraphics();

		Graphics iterationImaggeGraphics = iterationImage.createGraphics();

		/*
		  ITERATION START
		 */
		for (int i = 0; i < iterations; i++)
		{
			processImageGraphics.clearRect(0,0, imgOriginalWidth, imgOriginalHeight);
			processImageGraphics.drawImage(iterationImage, 0, 0, null);

			draw.drawRandom(processImageGraphics, imgOriginalWidth, imgOriginalHeight);

			double percentageNew = getDifferencePercent(imgOriginal, processImage);
			double percentageOld = getDifferencePercent(imgOriginal, iterationImage);

			if (percentageNew < percentageOld)
			{
				iterationImaggeGraphics.drawImage(processImage, 0, 0, null);

				printStatus(i);
				gui.updatePanel(iterationImage);
			}
		}
		/*
		  ITERATION END
		 */

		/*
		  FINISHED
		 */
		saveImage(iterationImage);
	}

	private double getDifferencePercent(BufferedImage img1, BufferedImage img2)
	{
		int width = img1.getWidth();
		int height = img1.getHeight();
		int width2 = img2.getWidth();
		int height2 = img2.getHeight();
		if (width != width2 || height != height2)
		{
			throw new IllegalArgumentException(String.format("Images must have the same dimensions: (%d,%d) vs. (%d,%d)", width, height, width2, height2));
		}

		long diff = 0;
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				diff += pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y));
			}
		}
		long maxDiff = 3L * 255 * width * height;

		return 100.0 * diff / maxDiff;
	}

	private int pixelDiff(int rgb1, int rgb2)
	{
		int r1 = (rgb1 >> 16) & 0xff;
		int g1 = (rgb1 >> 8) & 0xff;
		int b1 = rgb1 & 0xff;
		int r2 = (rgb2 >> 16) & 0xff;
		int g2 = (rgb2 >> 8) & 0xff;
		int b2 = rgb2 & 0xff;
		return Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
	}

	private void printStatus(int iteration)
	{
		System.out.println("Done: " + (int) ((double) iteration / iterations * 100) + "%");
		System.out.println("Memory left: " + Runtime.getRuntime().freeMemory());
		System.gc();
	}

	private void createIterationImage(Color bg)
	{
		iterationImage = new BufferedImage(imgOriginalWidth, imgOriginalHeight, BufferedImage.TYPE_INT_RGB);

		Graphics2D graphic = iterationImage.createGraphics();
		graphic.setPaint(bg);
		graphic.fillRect(0, 0, iterationImage.getWidth(), iterationImage.getHeight());
	}

	private void saveImage(BufferedImage image)
	{
		File outputfile = new File("output.jpg");
		try
		{
			ImageIO.write(image, "jpg", outputfile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
