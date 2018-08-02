package com.hoppix.imageprocessing;

import com.hoppix.imageprocessing.Controller.Controller;
import com.hoppix.imageprocessing.View.ImageGUI;

public class Main
{
	public static void main(String[] args)
	{
		ImageGUI gui = new ImageGUI();
		Controller controller = new Controller(gui, "parseMe.jpg");
		controller.run();
	}

}
