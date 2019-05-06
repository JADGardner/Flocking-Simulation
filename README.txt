
##########################################################################################
______ _            _    _               _____ _                 _       _   _             
|  ___| |          | |  (_)             /  ___(_)               | |     | | (_)            
| |_  | | ___   ___| | ___ _ __   __ _  \ `--. _ _ __ ___  _   _| | __ _| |_ _  ___  _ __  
|  _| | |/ _ \ / __| |/ / | '_ \ / _` |  `--. \ | '_ ` _ \| | | | |/ _` | __| |/ _ \| '_ \ 
| |   | | (_) | (__|   <| | | | | (_| | /\__/ / | | | | | | |_| | | (_| | |_| | (_) | | | |
\_|   |_|\___/ \___|_|\_\_|_| |_|\__, | \____/|_|_| |_| |_|\__,_|_|\__,_|\__|_|\___/|_| |_|
                                  __/ |                                                    
                                 |___/                                                     
###########################################################################################

This is the README for the Java program Flocking-Simulation.

Source files needed to run this program are listed below:

src->
    boids->
	Boid.java
	DynamicBoid.java
	IntelligentAgent.java
	IntelligentBoid.java
	Predator.java
    drawing->
	Canvas.java
	Portal.java
	Wall.java
    geometry->
	CartesianCoordinate.java
	LineSegment.java
	Vector.java
    gui->
	ActionListeners.java
	ButtonPanel.java
	GUI.java
	LowerPanel.java
	SidePanel.java
	SliderPanel.java
    main->
	FlockingSimulator.java
    tools->
	Utils.java
Icons->
    plus.png
    subtract.png
    toggle_off.png
    toggle_on.png
    vortex.png
    wall.png
    wind.png
		
The main entry to the program is via the FlockingSimulator Class.

The default setting is 500 Boids and 0 Predators.

###############################################################################
A reccommended setting for some of the most interesting and varied interactions 
is to add around 10 to 15 Predators.
###############################################################################

You can change the scale of the effects of each rule and turn each rule on or off. 
Be warned, if turning the max speed rule off the Boids are no longer limited in how 
fast they can go so will most likely fly straight off the screen. They will take some
time to return.

#################################################################################
To reset a rule to it's default setting, turn the rule off and on again using the 
checkbox next to the slider.
#################################################################################

Walls are placed using the mouse.

Portals are placed randomly on screen.

Newly added Boids enter from the top left.

Newly added Predators enter from the bottom right.

 