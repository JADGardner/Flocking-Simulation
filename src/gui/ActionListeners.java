/*
 * ActionListeners.java				19/04/2019
 * Version: 1.0
 * Programmer: Y3843317
 * Company: University of York
 * 
 */

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import boids.IntelligentBoid;
import boids.Predator;
import drawing.Portal;
import drawing.Wall;
import main.FlockingSimulator;
import tools.Utils;

/**
 * This Class represents an ActionListeners Object
 * that builds and contains all the ActionListeners 
 * required by the program.
 * 
 * @author Y3843317
 *
 */
public class ActionListeners {
	
	/* The GUI elements these action listeners
	 * are referring too. */
	private GUI gui;
	private SidePanel sidePanel;
	private LowerPanel lowerPanel;
	
	/* Lists of Objects that will be affected by 
	 * the ActionListeners. */
	private List<IntelligentBoid> boids;
	private List<Predator> predators;
	private List<Portal> portals;
	private List<Wall> walls; 
	
	/**
	 * The default constructor pulls in everything
	 * it requires from the FlockingSimulator 
	 * Object and splits the ActionListeners between
	 * two methods, one for each panel.
	 * 
	 * @param FS A FlockingSimulator Object.
	 */
	public ActionListeners(FlockingSimulator FS){
		
		gui = FS.getGUI();
		sidePanel = gui.getSidePanel();
		lowerPanel = gui.getLowerPanel();
		boids = FS.getBoids();
		predators = FS.getPredators();
		portals = FS.getPortals();
		walls = FS.getWalls();
		
		sidePanelActionListeners(FS, gui, sidePanel);
		lowerPanelActionListeners(FS, gui, lowerPanel);
	}
	
	/**
	 * This method contains all the ActionListeners required by the SidePanel 
	 * of the GUI.
	 * 
	 * @param FS A FlockingSimulator Object.
	 * @param g A GUI Object.
	 * @param sp A SidePanel Object.
	 */
	private void sidePanelActionListeners(FlockingSimulator FS, GUI g, SidePanel sp){
		
		/* This gives the user control over the maximum speed of the 
		 * Boids and predators. */
		sp.maxSpeedSlider.getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setMaxSpeed(sp.maxSpeedSlider.getSlider().getValue());
					}
				}
				
				synchronized (predators) {
					for(Predator predator : predators) {
						predator.setMaxSpeed(sp.maxSpeedSlider.getSlider().getValue());
					}
				}
			}
		});
		
		/* Setting the checkBox to selected turns the affect of controlling 
		 * the maximum speed on. If it is not the Boids will continue to 
		 * speed up until they no longer remain on screen. Turning the 
		 * checkBox back to selected will reset the MaxSpeedSlider back
		 * to its default setting. */
		sp.maxSpeedSlider.getCheckBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(sp.maxSpeedSlider.getCheckBox().isSelected()) {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setMaxSpeedOn(true);
							intelligentBoid.setMaxSpeed(g.INITIAL_SPEED);
							sp.maxSpeedSlider.getSlider().setValue(g.INITIAL_SPEED);
						}
					}
					
					synchronized (predators){
						for(Predator predator : predators) {
							predator.setMaxSpeedOn(true);
							predator.setMaxSpeed(g.INITIAL_SPEED);
							sp.maxSpeedSlider.getSlider().setValue(g.INITIAL_SPEED);
						}
					}
					
				} else {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setMaxSpeedOn(false);
						}
					}
					
					synchronized (predators){
						for(Predator predator : predators) {
							predator.setMaxSpeedOn(false);
						}
					}
				}


			}
		});

		/* This gives the user control over affect of cohesion. */
		sp.cohesionSlider.getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println(sp.cohesionSlider.getSlider().getValue());
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setCohesion((sp.cohesionSlider.getSlider().getValue()*0.1));
					}
				}
			}
		});
		
		/* Setting the checkBox to not-selected turns the affect of cohesion
		 * off. Turning the checkBox back to selected will reset the Slider back
		 * to its default setting. */
		sp.cohesionSlider.getCheckBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(sp.cohesionSlider.getCheckBox().isSelected()) {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setCohesionOn(true);
							intelligentBoid.setCohesion((g.INITIAL_COHESION_CONSTANT*0.1));
							sp.cohesionSlider.getSlider().setValue(g.INITIAL_COHESION_CONSTANT);
						}
					}
				} else {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setCohesionOn(false);
						}
					}
				}


			}
		});

		/* This gives the user control over affect of separation. */
		sp.separationSlider.getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setSeparation(sp.separationSlider.getSlider().getValue()*0.1);
					}
				}
			}
		});
		
		/* Setting the checkBox to not-selected turns the affect of separation
		 * off. Turning the checkBox back to selected will reset the Slider back
		 * to its default setting. */
		sp.separationSlider.getCheckBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(sp.separationSlider.getCheckBox().isSelected()) {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setSeparationOn(true);
							intelligentBoid.setSeparation(g.INITIAL_SEPERATION_CONSTANT*0.1);
							sp.separationSlider.getSlider().setValue(g.INITIAL_SEPERATION_CONSTANT);
						}
					}
				} else {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setSeparationOn(false);
						}
					}
				}


			}
		});

		/* This gives the user control over affect of alignment. */
		sp.alignmentSlider.getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setAlignment((sp.alignmentSlider.getSlider().getValue()*0.001));
					}
				}
			}
		});

		/* Setting the checkBox to not-selected turns the affect of alignment
		 * off. Turning the checkBox back to selected will reset the Slider back
		 * to its default setting. */
		sp.alignmentSlider.getCheckBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(sp.alignmentSlider.getCheckBox().isSelected()) {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setAlignmentOn(true);
							intelligentBoid.setAlignment((g.INITIAL_ALIGNMENT_CONSTANT*0.001));
							sp.alignmentSlider.getSlider().setValue(g.INITIAL_ALIGNMENT_CONSTANT);
						}
					}
				} else {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setAlignmentOn(false);
						}
					}
				}


			}
		});
		
		/* This gives the user control over affect of the mouse avoidance rule. */
		sp.mouseAvoidanceSlider.getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setAvoidMouseConstant(sp.mouseAvoidanceSlider.getSlider().getValue());
					}
				}

			}
		});
		
		/* Setting the checkBox to not-selected turns the affect of mouse avoidance
		 * off. Turning the checkBox back to selected will reset the Slider back
		 * to its default setting. */
		sp.mouseAvoidanceSlider.getCheckBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(sp.mouseAvoidanceSlider.getCheckBox().isSelected()) {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setMouseAvoidOn(true);
							intelligentBoid.setAvoidMouseConstant(g.INITIAL_MOUSE_AVOID_CONSTANT);
							sp.mouseAvoidanceSlider.getSlider().setValue(g.INITIAL_MOUSE_AVOID_CONSTANT);
						}
					}
				} else {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setMouseAvoidOn(false);
						}
					}
				}


			}
		});
		
		/* This adds or removes two portals to the canvas in two 
		 * random locations and changes the JLabel text to display
		 * the new action of the button. If two Portals are on
		 * screen then the button will remove those portals, else 
		 * it will add two. */
		sp.addVortexButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized (portals){
					if(portals.size() == 0){
						/* Change the text to the new action the button will perform. */
						sp.getVortexButtonPanel().getButtonLabel().setText("Remove Vortex ");
						
						/* Place two portals on the canvas. */
						int x = Utils.randomInt(gui.getCanvas().getWidth());
						int y = Utils.randomInt(gui.getCanvas().getHeight());
						portals.add(new Portal(x, y));
						x = Utils.randomInt(gui.getCanvas().getWidth() - 100);
						y = Utils.randomInt(gui.getCanvas().getHeight() - 100);
						portals.add(new Portal(x, y));
						
					} else {
						/* Change the text to the new action the button will perform. */
						sp.getVortexButtonPanel().getButtonLabel().setText("Add Vortex ");
						
						/* Remove the portals and clear them from the Canvas. */
						portals.remove(portals.size() - 1);
						portals.remove(portals.size() - 1);
						g.getCanvas().removePortals();
					}
					
				}
			}
		});
		
		/* This allows the user to place, stop placing or clear walls that 
		 * they have placed. Each press of the button changes the action
		 * the button will perform. This is made clear by changing 
		 * the text the buttons display. */
		sp.addWallButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/* Get the current state of the button. */
				int wallButtonPressCount = FS.getWallButtonPressCount();
				switch (wallButtonPressCount) {
		            case 0:
		            	/* Allow the user to place walls on the canvas. */
		            	FS.setWallButtonPressCount(wallButtonPressCount + 1);
		            	FS.setWallPlace();
		            	sp.getWallButtonPanel().getButtonLabel().setText("Stop ");
		            	break;	
		            case 1:
		            	/* Stop allowing the user to place walls on the canvas. */
		            	FS.setWallButtonPressCount(wallButtonPressCount + 1);
		            	FS.setWallPlace();
		            	sp.getWallButtonPanel().getButtonLabel().setText("Clear Walls ");		            	
		                break;
		            case 2:
		            	/* Clear walls from the canvas by emptying the list and 
		            	 * removing them from the canvas object. */
		            	FS.setWallButtonPressCount(0);
		            	sp.getWallButtonPanel().getButtonLabel().setText("Place Walls ");		            	
		            	walls.clear();
		            	g.getCanvas().removeWalls();
		                break;
				}
			}
		});
		
		/* Allows the user to turn on or off the affect of wind 
		 * on the Boids and predators. */
		sp.addWindButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				synchronized (boids){
					for(IntelligentBoid intelligentBoid : boids) {
						/* Swaps the current windOn State. */
						intelligentBoid.setWindOn();
					}
				}

				if(!FS.isWindState()) {
					sp.getWindButtonPanel().getButtonLabel().setText("Turn Wind Off ");
				} else {
					sp.getWindButtonPanel().getButtonLabel().setText("Turn Wind On ");
				}
				
				FS.swapWindState();
			}
		});
		
	}
	
	
	
	/**
	 * This method contains all the ActionListeners required by the LowerPanel 
	 * of the GUI.
	 * 
	 * @param FS A FlockingSimulator Object.
	 * @param g A GUI Object.
	 * @param sp A SidePanel Object.
	 */
	private void lowerPanelActionListeners(FlockingSimulator FS, GUI g, LowerPanel lp){
		
		/* Adds a new Boid object to the list of Boids. Instantiates the 
		 * Boids off screen so that it smoothly joins the group. Increases 
		 * the count keeping track of the number of Boids. */
		lp.addBoidButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized (boids){
					boids.add(new IntelligentBoid(g.getCanvas(), -100, -100, FS.getBoidSize()));
				}
				
				g.numberOfBoids++;
				lp.boidCounter.setText("Boids: " + g.numberOfBoids);

			}
		});

		/* Removes a Boid from the list of Boids. unDrawing 
		 * the boid first is necessary so no ghost of the boids remains. */
		lp.removeBoidButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized (boids){
					if(boids.size() > 0){
						boids.get(boids.size() - 1).unDraw();
						boids.remove(boids.size() - 1);
						g.numberOfBoids--;
					}
				}
				
				lp.boidCounter.setText("Boids: " + g.numberOfBoids);
			}
		});
		
		/* Adds a new Predator object to the list of Predators. Instantiates the 
		 * Predator off screen so that it smoothly joins the group. */
		lp.addPredatorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized (predators){
					predators.add(new Predator(g.getCanvas(), gui.getCanvas().getWidth() + 100, gui.getCanvas().getHeight() + 100, FS.getPredatorSize()));
				}
				
				g.numberOfPredators++;
				lp.predatorCounter.setText("Predators: " + g.numberOfPredators);

			}
		});

		/* Removes a Boid from the list of Predator. unDrawing 
		 * the Predator first is necessary so no ghost of the Predator remains. */
		lp.removePredatorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized (predators){
					if(predators.size() > 0){
						predators.get(predators.size() - 1).unDraw();
						predators.remove(predators.size() - 1);
						g.numberOfPredators--;
					}
				}
				lp.predatorCounter.setText("Predators: " + g.numberOfPredators);
			}
		});
	}
}
