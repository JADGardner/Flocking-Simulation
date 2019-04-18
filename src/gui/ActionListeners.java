package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import boids.IntelligentBoid;
import boids.Predator;
import drawing.Portal;
import main.FlockingSimulator;
import tools.Utils;

public class ActionListeners {

	GUI gui;
	SidePanel sidePanel;
	LowerPanel lowerPanel;
	List<IntelligentBoid> boids;
	private List<Predator> predators;
	List<Portal> portals;
	int wallButtonPressCount = 0;
	
	public ActionListeners(FlockingSimulator FS){
		
		gui = FS.getGUI();
		sidePanel = gui.getSidePanel();
		lowerPanel = gui.getLowerPanel();
		boids = FS.getBoids();
		predators = FS.getPredators();
		portals = FS.getPortals();
		
		sidePanelActionListeners(FS, gui, sidePanel);
		lowePanelActionListeners(FS, gui, lowerPanel);
	}
	
	private void sidePanelActionListeners(FlockingSimulator FS, GUI g, SidePanel sp){
		
		sp.maxSpeedSlider.getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setMaxSpeed(sp.maxSpeedSlider.getSlider().getValue());
					}
				}
			}
		});
		
		sp.maxSpeedSlider.getCheckBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(sp.maxSpeedSlider.getCheckBox().isSelected()) {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setAlignmentOn(true);
							intelligentBoid.setMaxSpeed(g.INITIAL_SPEED);
							sp.maxSpeedSlider.getSlider().setValue(g.INITIAL_SPEED);
						}
					}
				} else {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setMaxSpeed(0);
						}
					}
				}


			}
		});

		sp.cohesionSlider.getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setCohesion((sp.cohesionSlider.getSlider().getValue()/10));
					}
				}
			}
		});
		
		sp.cohesionSlider.getCheckBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(sp.cohesionSlider.getCheckBox().isSelected()) {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setCohesionOn(true);
							intelligentBoid.setCohesion((g.INITIAL_COHESION_CONSTANT/10));
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

		sp.sperationSlider.getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setSeperation(sp.sperationSlider.getSlider().getValue()/10);
					}
				}
			}
		});
		
		sp.sperationSlider.getCheckBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(sp.sperationSlider.getCheckBox().isSelected()) {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setSeperationOn(true);
							intelligentBoid.setSeperation(1);
							sp.sperationSlider.getSlider().setValue(g.INITIAL_SEPERATION_CONSTANT);
						}
					}
				} else {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setSeperationOn(false);
						}
					}
				}


			}
		});

		sp.alignmentSlider.getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setAlignment((sp.alignmentSlider.getSlider().getValue()/1000));
					}
				}
			}
		});

		
		sp.alignmentSlider.getCheckBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(sp.alignmentSlider.getCheckBox().isSelected()) {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setAlignmentOn(true);
							intelligentBoid.setAlignment((g.INITIAL_ALIGNMENT_CONSTANT/1000));
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
		
		sp.addVortexButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized (portals){
					if(portals.size() == 0){
						int x = Utils.randomInt(gui.getCanvas().getWidth());
						int y = Utils.randomInt(gui.getCanvas().getHeight());
						portals.add(new Portal(x, y));
						x = Utils.randomInt(gui.getCanvas().getWidth());
						y = Utils.randomInt(gui.getCanvas().getHeight());
						portals.add(new Portal(x, y));
					} else {
						portals.remove(portals.size() - 1);
						portals.remove(portals.size() - 1);
						g.getCanvas().removePortal();
					}
					
				}
			}
		});
		
		sp.addWallButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FS.setWallPlace();
				wallButtonPressCount++;
			}
		});
		
		
	}
	
	
	
	
	private void lowePanelActionListeners(FlockingSimulator FS, GUI g, LowerPanel lp){
		
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
		
		lp.addPredatorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized (predators){
					predators.add(new Predator(g.getCanvas(), gui.getCanvas().getWidth()/2, gui.getCanvas().getHeight()/2, FS.getPredatorSize()));
				}
				
				g.numberOfPredators++;
				lp.predatorCounter.setText("Predators: " + g.numberOfPredators);

			}
		});

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
