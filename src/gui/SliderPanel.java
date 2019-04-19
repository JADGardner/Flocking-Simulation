/*
 * SliderPanel.java				19/04/2019
 * Version: 1.0
 * Programmer: Y3843317
 * Company: University of York
 * 
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.FileNotFoundException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;

import tools.Utils;

/**
 * This Class is a representation of a SliderPanel. This is an
 * extension of the JPanel Class and is used to combine the 
 * functionality of JSliders and JCheckBoxes into one Class.
 * 
 * @author Y3843317
 *
 */
public class SliderPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JSlider slider;
	private JCheckBox functionSwitch;
	private ImageIcon toggleOnIcon;
	private ImageIcon toggleOffIcon;
	
	private int borderThickness = 10;
	private int defaultIconSize = 40;
	private int panelWidth = 350;
	private int panelHeight = 110;
	private int fontSize = 30;
	
	/**
	 * Default Constructor for the SliderPanel, sets 
	 * up the JSlider and JCheckbox using the default sizes
	 * and icons and the inputs from the user.
	 * 
	 * @param name What the JSlider will be called.
	 * @param min The minimum value of the JSlider.
	 * @param max The maximum value of the JSlider.
	 * @param initial The initial value of the JSlider.
	 */
	public SliderPanel(String name, int min, int max, int initial){
		
		setBackground(Color.white);
		
		/* Creating a border for the SliderPanel that 
		 * has no colour and contains only the String name
		 * that the user has provided. */
		TitledBorder titleBorder = BorderFactory.createTitledBorder(
							       BorderFactory.createMatteBorder(borderThickness, borderThickness, 
								   borderThickness, borderThickness, Color.white), name);
		/* Positioning the title, setting its font and 
		 * adding it to the SliderPanel. */
		titleBorder.setTitleJustification(TitledBorder.LEFT);
		titleBorder.setTitleFont(new Font("Segoe UI", Font.PLAIN, fontSize));
		setBorder(titleBorder);
		
		/* Instantiating the JSlider to the user provided
		 * settings. */
		slider = new JSlider(min, max, initial);
		slider.setOpaque(false);
		
		/* Importing the two custom icons to be used 
		 * on the JCheckBoxes. Handling a potential error
		 * if the path is wrong. */ 
		try {
			toggleOnIcon = Utils.importImageIcon("./Icons/toggle_on.png");
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace(); 
			System.out.println("File not found: \"" + "toggle_on.png" + "\". Terminating!");
			System.exit(0);
		}
		
		try {
			toggleOffIcon = Utils.importImageIcon("./Icons/toggle_off.png");
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace(); 
			System.out.println("File not found: \"" + "toggle_off.png" + "\". Terminating!");
			System.exit(0);
		}
		
		/* Scaling the Icons to the correct size. */
		toggleOnIcon = Utils.scaleImageIcon(toggleOnIcon, defaultIconSize);
		toggleOffIcon = Utils.scaleImageIcon(toggleOffIcon, defaultIconSize);
		
		/* Instantiating the JCehckBox and setting
		 * its Icons to the custom ones. */
		functionSwitch = new JCheckBox();
		functionSwitch.setSelected(true);
		functionSwitch.setSelectedIcon(toggleOnIcon);
		functionSwitch.setIcon(toggleOffIcon);
		functionSwitch.setOpaque(false);

		add(slider, BorderLayout.WEST);
		add(functionSwitch, BorderLayout.EAST);

		/* Forcing JPanel to set dimensions. */
		setMinimumSize(new Dimension(panelWidth, panelHeight));
		setMaximumSize(new Dimension(panelWidth, panelHeight));

	}
	
	
	public JSlider getSlider() {
		return slider;
	}
	
	public JCheckBox getCheckBox() {
		return functionSwitch;
	}
	
}
