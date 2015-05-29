import java.awt.Point;

import processing.core.*;
import controlP5.*;

public class AttractReppel extends PApplet {

	ControlP5 controlP5;
	boolean flagMouse = false;
	boolean flagMouse2 = false;
	boolean flagFocusPoint = false;
	int alpha = 150;

	/*
	  Using PVector class to make an array of particles
	  with repulsion/attraction forces.

	  Hold in mousebutton to repel, otherwise particles are
	  attracted to mouseX and mouseY

	  Colour controlled by distance from particle to mouse co-ords.
	 */

	//------------------------ INITIALISE VARIABLES ------------------------

	int num = 2000; //how many particles we'll have in the system. More particles = slower sketch.

	Particle[] particle = new Particle[num]; //Initialise array of particles of length "num"
	Point[] focusPointsVec = new Point[5]; //Initially allows for 5 focus points
	int focusVectorPos = 0; //to keep control of the focusPoints vector

	//------------------------ SETUP ---------------------------------------

	public void setup(){
		size(600, 400);
		smooth();  //turn on anti-aliasing
		noStroke();
		background(255);

		//fill particle array with new Particle objects
		for(int i=0; i<particle.length; i++){
			particle[i] = new Particle(new PVector(random(0, width), random(0, height)), 2, 10, 10);
		}

		controlP5 = new ControlP5(this);
		// change the default font to Verdana
		PFont p = createFont("Verdana",9); 
		controlP5.setControlFont(p);

		// change the original colors
		controlP5.setColorForeground(0xffaa0000);
		controlP5.setColorBackground(0xff660000);
		controlP5.setColorLabel(0xffdddddd);
		controlP5.setColorValue(0xffff88ff);
		controlP5.setColorActive(0xffff0000);
		
		controlP5.addTab("Tab");

		// add the elments ( see example 1 for the parameters )
		controlP5.addBang("FREEZE",10,10,20,20);    
		controlP5.addButton("ADD FOCUS",1,70,10,60,20);  
		controlP5.addToggle("FROZEN?",false,170,10,20,20);    
		controlP5.addSlider("COLOR_1",0,255,128,10,80,10,100);
		controlP5.addSlider("COLOR_2",0,255,128,10,200,10,100);
		    

		Slider s = controlP5.addSlider("TRAIL",0,255,128,70,80,100,10);
		// change sliderMode of the Slider object. The default is Slider.FIX
		s.setSliderMode(Slider.FLEXIBLE); 

		controlP5.addKnob("PARTICLE_SIZE",0,360,0,70,120,50);

//		Numberbox n = controlP5.addNumberbox("numberbox1",50,170,120,60,14);
//		// change Multiplier of the Numberbox ( default is 1 )
//		n.setMultiplier(30);
	}

	public void controlEvent(ControlEvent theEvent) {

		if(theEvent.isController()) { 

			print("control event from : "+theEvent.controller().name());
			println(", value : "+theEvent.controller().value());
			
			if(theEvent.controller().name()=="HIDE") {
				controlP5.hide();
			}

			// clicking on FREEZE sets FROZEN? value to 1 (true)      
			if(theEvent.controller().name()=="FREEZE") {

				if (controlP5.controller("FROZEN?").getValue() == 0) {
					for(int i=0; i<particle.length; i++){
						particle[i].velocityLimit = 0;
					}
					controlP5.controller("FROZEN?").setValue(1);
				}
				else {
					for(int i=0; i<particle.length; i++){
						particle[i].velocityLimit = 5;
					}
					controlP5.controller("FROZEN?").setValue(0); 
				}
			}
			// clicking on ADD FOCUS sets FROZEN? value to 0 (false)
			if(theEvent.controller().name()=="ADD FOCUS") {
				flagMouse = true;
				
			}

			// dragging COLOR_1 changes the value of TRAIL LENGTH
			if(theEvent.controller().name()=="COLOR_1") {
				//controlP5.controller("TRAIL LENGTH").setValue(theEvent.controller().value());
				for(int i=0; i<particle.length; i++){
					particle[i].color1 = theEvent.controller().value();
				}
			}
			
			if(theEvent.controller().name()=="COLOR_2") {
				//controlP5.controller("TRAIL LENGTH").setValue(theEvent.controller().value());
				for(int i=0; i<particle.length; i++){
					particle[i].color2 = theEvent.controller().value();
				}

			}
			
			if(theEvent.controller().name()=="TRAIL") {
				//controlP5.controller("PARTICLE_SIZE").setValue(theEvent.controller().value());		
				alpha = (int)theEvent.controller().value();
			}

			// changing the value of numberbox1 turns PARTICLE_SIZE
			if(theEvent.controller().name()=="PARTICLE_SIZE") {
				//controlP5.controller("PARTICLE_SIZE").setValue(theEvent.controller().value());
				for(int i=0; i<particle.length; i++){
					particle[i].sz = (int)theEvent.controller().value();
				}
			}

		}  
	}

	//------------------------ DRAW ----------------------------------------

	public void draw(){

		//draw trails, trail length can be altered by making alpha value in fill() lower
		fill(255, alpha);
		rect(0, 0, width, height);

		//run all the particles in the array every frame
		for(int i=0; i<particle.length; i++){
			if (focusPointsVec[0] != null) {
				if (dist(particle[i].loc.x, particle[i].loc.y, focusPointsVec[0].x,focusPointsVec[0].y) < 150) {
					particle[i].run(focusPointsVec[0].x,focusPointsVec[0].y); //run() method takes two arguments - x and y values to apply forces to
				}
				else
					particle[i].run(mouseX, mouseY); //run() method takes two arguments - x and y values to apply forces to
			}
			else
				particle[i].run(mouseX, mouseY); //run() method takes two arguments - x and y values to apply forces to
		}
		
		for (int i=0; i<focusPointsVec.length; i ++) {
			if (focusPointsVec[i] != null)
				ellipse(focusPointsVec[i].x, focusPointsVec[i].y, 20, 20);
		}
		
//		for(int i=0; i<particle.length; i++){
//			if (focusPointsVec[0] != null) {
//				if (dist(particle[i].loc.x, particle[i].loc.y, focusPointsVec[0].x,focusPointsVec[0].y) < 10)
//					particle[i].d; //run() method takes two arguments - x and y values to apply forces to
//			}
//		}
		
		

	}
	
	public void mouseClicked() {
		
		if (flagMouse == true && flagMouse2 == false) {
			flagMouse2 = true;
		}
		
		else if(flagMouse == true && flagMouse2 == true) {
			
			flagFocusPoint = true;
			Point newFocusPoint = new Point(mouseX,mouseY);

			focusPointsVec[focusVectorPos] = newFocusPoint;
			focusVectorPos++;
			
			if(focusVectorPos == 5)
				focusVectorPos = 0;
			
			flagMouse = false;
			flagMouse2 = false;
		}
		
	}

	//------------------------ OBJECT --------------------------------------

	class Particle{
		/*
	    PVector is a class in Processing that makes it easier to store
	    values, and make calculations based on these values. It can make
	    applying forces to objects much easier and more efficient!
		 */
		PVector loc; //location vector
		PVector vel; //velocity vector
		PVector acc; //acceleration vector
		int sz;  //size of particle
		float gravity;  //gravity variable
		float mass;  //mass variable
		int velocityLimit = 5;  //the maximum velocity a particle can travel at
		float d;  //distance variable between particle and the target co-ordinates
		float color1;  //particle color slider 1
		float color2; //particle color slider2

		//CONSTRUCTOR
		Particle(PVector _loc, int _sz, float _gravity, float _mass){
			loc = _loc.get();  //when calling loc, return current location of the selected particle
			vel = new PVector(0, 0);  //set vel and acc vectors to 0 as default
			acc = new PVector(0, 0);
			sz = _sz;
			gravity = _gravity;
			mass = _mass;
		}


		//method to render the particle. control how it looks here!
		void display(){
			ellipseMode(CENTER);
			fill(d, color1, color2,150);
			ellipse(loc.x, loc.y, sz, sz);
		}

		//math for attraction and repulsion forces
		//tx and ty are the co-ordinates attraction/repulsion will be applied to

		void forces(float tx, float ty){
			PVector targetLoc = new PVector(tx, ty);  //creating new vector for attractive/repulsive x and y values
			PVector dir = PVector.sub(loc, targetLoc);  //calculate the direction between a particle and targetLoc
			d = dir.mag();  //calculate how far away the particle is from targetLoc
			dir.normalize();  //convert the measurement to a unit vector

			//calculate the strength of the force by factoring in a gravitational constant and the mass of a particle
			//multiply by distance^2
			float force = (gravity*mass) / (d*d);

			//if the mouse is pressed, turn on repulsion by multiplying direction by 1
			if(mousePressed){
				dir.mult(1);
			}
			
			//else multiply the direction by -1 to switch the direction the particle travels in (attraction)
			else{
				dir.mult(-1);
			}

			//apply directional vector
			applyForce(dir);
		}

		//method to apply a force vector to the particle
		void applyForce(PVector force){
			force.div(mass);
			acc.add(force);
		}

		//method to update the location of the particle, and keep its velocity within a set limit
		void update(){
			vel.add(acc);
			vel.limit(velocityLimit);
			loc.add(vel);
			acc.mult(0);
		}

		//method to bounce particles of canvas edges
		void bounds(){
			if(loc.y > height || loc.y < 0){
				vel.y *= -1;
			}
			if(loc.x > width || loc.x < 0){
				vel.x *= -1;
			}
		}

		//main method that combines all previous methods, and takes two arguments
		//tx and ty are inherited from forces(), and set the attractive/repulsive co-ords
		void run(float tx, float ty){
			forces(tx, ty);
			display();
			bounds();
			update();
		}
	}


}
