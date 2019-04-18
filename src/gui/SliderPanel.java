package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;

import tools.Utils;



public class SliderPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JSlider slider;
	private JCheckBox functionSwitch;
	private ImageIcon toggleOnIcon;
	private ImageIcon toggleOffIcon;
	
	public SliderPanel(String name, int min, int max, int initial){
		
		setBackground(Color.white);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(
							 BorderFactory.createMatteBorder(10, 10, 10, 10, Color.white), name);
		titleBorder.setTitleJustification(TitledBorder.LEFT);
		titleBorder.setTitleFont(new Font("Segoe UI", Font.PLAIN, 30));
		setBorder(titleBorder);
		
		slider = new JSlider(min, max, initial);
		slider.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		slider.setOpaque(false);
		slider.setMinimum(min);
		slider.setMaximum(max);
		
		toggleOnIcon = new ImageIcon("toggle_on_side.png");
		toggleOffIcon = new ImageIcon("toggle_off_side.png");
		toggleOnIcon = Utils.scaleImageIcon(toggleOnIcon, 40);
		toggleOffIcon = Utils.scaleImageIcon(toggleOffIcon, 40);
		
		functionSwitch = new JCheckBox();
		functionSwitch.setSelected(true);
		functionSwitch.setSelectedIcon(toggleOnIcon);
		functionSwitch.setIcon(toggleOffIcon);
		functionSwitch.setOpaque(false);

		add(slider, BorderLayout.WEST);
		add(functionSwitch, BorderLayout.EAST);

		//setBorder(BorderFactory.createLineBorder(new EmptyBorder(10, 10, 10, 10), Color.black));
		setMinimumSize(new Dimension(350, 110));
		setMaximumSize(new Dimension(350, 110));

	}
	
	
	public JSlider getSlider() {
		return slider;
	}
	
	public JCheckBox getCheckBox() {
		return functionSwitch;
	}
	
}
