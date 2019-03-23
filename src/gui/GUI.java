package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import drawing.Canvas;

public class GUI {
	private JFrame frame;
	private Canvas canvas;
	
	public GUI() {
		frame = new JFrame();
		frame.setTitle("Flocking Simulation");
		
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int size = (int) (0.5*screenDimension.getWidth());
		
		frame.setSize(size, size);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		
		canvas = new Canvas();

	}

}
