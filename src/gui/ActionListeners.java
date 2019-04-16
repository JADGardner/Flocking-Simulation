package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import boids.IntelligentBoid;
import main.FlockingSimulator;

public class ActionListeners {

	GUI gui;
	SidePanel sidePanel;
	LowerPanel lowerPanel;
	List<IntelligentBoid> boids;
	
	public ActionListeners(FlockingSimulator FS){
		
		gui = FS.getGUI();
		sidePanel = gui.getSidePanel();
		lowerPanel = gui.getLowerPanel();
		boids = FS.getBoids();
		
		sidePanelActionListeners(gui, sidePanel);
		lowePanelActionListeners(FS, gui, lowerPanel);
	}
	
	private void sidePanelActionListeners(GUI g, SidePanel s){
		
		s.maxSpeedSlider.getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setMaxSpeed(s.maxSpeedSlider.getSlider().getValue());
					}
				}
			}
		});
		
		s.maxSpeedSlider.getCheckBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(s.maxSpeedSlider.getCheckBox().isSelected()) {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setAlignmentOn(true);
							intelligentBoid.setMaxSpeed(g.INITIAL_SPEED);
							s.maxSpeedSlider.getSlider().setValue(g.INITIAL_SPEED);
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

		s.cohesionSlider.getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setCohesion((s.cohesionSlider.getSlider().getValue()/10));
					}
				}
			}
		});
		
		s.cohesionSlider.getCheckBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(s.cohesionSlider.getCheckBox().isSelected()) {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setCohesionOn(true);
							intelligentBoid.setCohesion((g.INITIAL_COHESION_CONSTANT/10));
							s.cohesionSlider.getSlider().setValue(g.INITIAL_COHESION_CONSTANT);
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

		s.sperationSlider.getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setSeperation(s.sperationSlider.getSlider().getValue()/10);
					}
				}
			}
		});
		
		s.sperationSlider.getCheckBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(s.sperationSlider.getCheckBox().isSelected()) {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setSeperationOn(true);
							intelligentBoid.setSeperation(1);
							s.sperationSlider.getSlider().setValue(g.INITIAL_SEPERATION_CONSTANT);
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

		s.alignmentSlider.getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setAlignment((s.alignmentSlider.getSlider().getValue()/1000));
					}
				}
			}
		});

		
		s.alignmentSlider.getCheckBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(s.alignmentSlider.getCheckBox().isSelected()) {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setAlignmentOn(true);
							intelligentBoid.setAlignment((g.INITIAL_ALIGNMENT_CONSTANT/1000));
							s.alignmentSlider.getSlider().setValue(g.INITIAL_ALIGNMENT_CONSTANT);
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
		
		s.mouseAvoidanceSlider.getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setAvoidMouseConstant(s.mouseAvoidanceSlider.getSlider().getValue());
					}
				}

			}
		});
		
		s.mouseAvoidanceSlider.getCheckBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(s.mouseAvoidanceSlider.getCheckBox().isSelected()) {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setMouseAvoidOn(true);
							intelligentBoid.setAvoidMouseConstant(g.INITIAL_MOUSE_AVOID_CONSTANT);
							s.mouseAvoidanceSlider.getSlider().setValue(g.INITIAL_MOUSE_AVOID_CONSTANT);
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
	}
	
	
	
	
	private void lowePanelActionListeners(FlockingSimulator FS, GUI g, LowerPanel l){
		
		l.addBoidButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized (boids){
					boids.add(new IntelligentBoid(g.getCanvas(), -100, -100, FS.getBoidSize()));
				}
				
				g.numberOfBoids++;
				l.boidCounter.setText("Boids: " + g.numberOfBoids);

			}
		});

		l.removeBoidButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized (boids){
					if(boids.size() > 0){
						boids.get(boids.size() - 1).unDraw();
						boids.remove(boids.size() - 1);
					}
				}
				g.numberOfBoids--;
				l.boidCounter.setText("Boids: " + g.numberOfBoids);
			}
		});
	}
}
