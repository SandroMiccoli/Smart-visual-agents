import java.util.Random;

import processing.core.*;

// Simple Circle Class

public class Circle implements Shape{

	private PApplet parent; // Necessary to access Processing's functions
	private PVector pos;     // horizontal location of circle
	private PVector speed; // speed of circle
	private PVector acc; // acceleration speed
	int velocityLimit = 3;  //the maximum velocity a circle can travel at
	private float r;     // radius of circle

	private  float gravity; // circle gravity
	private float mass; // circle mass
	float d;  //distance variable between particle and the target co-ordinates

	private int colourR;
	private int colourG;
	private int colourB;


	Circle(PApplet p) {
		this.parent = p;

		this.r = 5;

		// All circles start at random position
		this.pos = new PVector(parent.random(0+r,parent.width-r), parent.random(0+r,parent.height-r));
		// All circles have a random positive speed
		this.speed = new PVector(parent.random(-2,2),parent.random(-2,2));
		//this.speed = new PVector(0,0);
		this.acc = new PVector(0,0);
		// For now, all circles have same mass and gravity
		this.setGravity(10);
		this.setMass(1*this.r);

		Random r = new Random();
		colourR = r.nextInt(255);
		colourG = r.nextInt(255);
		colourB = r.nextInt(255);
	}

	// Draw ellipse (circle)
	public void display() {

		  // color fill
	  	parent.fill(colourR, colourG, colourB, 150); // Color fill

		  // stroke border
	    parent.strokeWeight((float) 0.5);
	    parent.stroke(233);

	    // draw ellipse
	    parent.ellipse(this.pos.x,this.pos.y,this.r,this.r);

	}

	public void drawVectors(){
		int magnify = 15; // increase size of speed to visualize better
	    parent.stroke(255,0,0,150);
	    parent.strokeWeight(3);
		parent.line(this.pos.x, this.pos.y, this.pos.x+this.speed.x*magnify, this.pos.y+this.speed.y*magnify);
	}

    // Move circle
	public void move() {
		speed.add(acc);
		speed.limit(velocityLimit);
		pos.add(speed);
		acc.mult(0);
	}

	// check for boundaries and bounces
	public void bounds(){
	  if (pos.x > parent.width - r || pos.x < 0+r) speed.x*=-1;
	  if (pos.y > parent.height - r || pos.y < 0+r) speed.y*=-1;
	}

	//main method that combines all previous methods and makes the simulation happen
	public void run(){
		display();
		move();
		bounds();
	}

	// getters and setters

	public PVector getPos() {
		return pos;
	}

	public void setPos(PVector pos) {
		this.pos = pos;
	}

	public PVector getSpeed() {
		return speed;
	}

	public void setSpeed(PVector speed) {
		this.speed = speed;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	@Override
	public void setAmount(float amount) {
		// TODO Auto-generated method stub

	}

}
