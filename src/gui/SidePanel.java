package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import tools.Utils;

public class SidePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SliderPanel maxSpeedSlider;
	SliderPanel cohesionSlider;
	SliderPanel sperationSlider;
	SliderPanel alignmentSlider;
	SliderPanel mouseAvoidanceSlider;
	
	JButton addWallButton;
	JButton addVortexButton;
	JButton addWindButton;
	JButton infoButton;

	public SidePanel(GUI g){
		setPreferredSize(new Dimension((int) (0.374*g.size), g.size));
		setBackground(Color.white);
		BoxLayout sideLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(sideLayout);
		
		maxSpeedSlider = new SliderPanel("Max Speed", g.MIN_SPEED, g.MAX_SPEED, g.INITIAL_SPEED);
		cohesionSlider = new SliderPanel("Cohesion", g.MIN_COHESION_CONSTANT, g.MAX_COHESION_CONSTANT, g.INITIAL_COHESION_CONSTANT);
		sperationSlider = new SliderPanel("Seperation", g.MIN_SEPERATION_CONSTANT, g.MAX_SEPERATION_CONSTANT, g.INITIAL_SEPERATION_CONSTANT);
		alignmentSlider = new SliderPanel("Alignment", g.MIN_ALIGNMENT_CONSTANT, g.MAX_ALIGNMENT_CONSTANT, g.INITIAL_ALIGNMENT_CONSTANT);
		mouseAvoidanceSlider = new SliderPanel("Mouse Avoidance", g.MIN_MOUSE_AVOID_CONSTANT, g.MAX_MOUSE_AVOID_CONSTANT, g.INITIAL_MOUSE_AVOID_CONSTANT);

		add(maxSpeedSlider);
		add(cohesionSlider);
		add(sperationSlider);
		add(alignmentSlider);
		add(mouseAvoidanceSlider);
		
		// setting up buttons 
		
		addWallButton = new JButton();
		addWallButton.setBackground(Color.white);
		addWallButton.setBorder(null);
		
		ImageIcon wallIcon = new ImageIcon("wall.png");
		wallIcon = Utils.scaleImageIcon(wallIcon, (int) (0.0654*g.size));
		
		addWallButton.setIcon(wallIcon);
		addWallButton.setToolTipText("Add walls");
		
		addVortexButton = new JButton();
		addVortexButton.setBackground(Color.white);
		addVortexButton.setBorder(null);
		
		ImageIcon vortexIcon = new ImageIcon("vortex.png");
		vortexIcon = Utils.scaleImageIcon(vortexIcon, (int) (0.0654*g.size));
		
		addVortexButton.setIcon(vortexIcon);
		addVortexButton.setToolTipText("Add vortex");
		
		addWindButton = new JButton();
		addWindButton.setBackground(Color.white);
		addWindButton.setBorder(null);
		
		ImageIcon windIcon = new ImageIcon("wind.png");
		windIcon = Utils.scaleImageIcon(windIcon, (int) (0.0654*g.size));
		
		addWindButton.setIcon(windIcon);
		addWindButton.setToolTipText("Add wind");
		
		infoButton = new JButton();
		infoButton.setBackground(Color.white);
		infoButton.setBorder(null);
		
		ImageIcon infoIcon = new ImageIcon("info.png");
		infoIcon = Utils.scaleImageIcon(infoIcon, (int) (0.0654*g.size));
		
		infoButton.setIcon(infoIcon);
		infoButton.setToolTipText("Info");
		
		add(addWallButton);
		add(addVortexButton);
		add(addWindButton);
		add(infoButton);
	}
}
