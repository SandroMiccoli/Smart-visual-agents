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
  
	
	Circle(PApplet p) {
		this.parent = p;
		    
		// random size
		this.r = parent.random(15,35);
		// All circles start at random position
		this.pos = new PVector(parent.random(0+r,parent.width-r), parent.random(0+r,parent.height-r));
		// All circles have a random positive speed
		//this.speed = new PVector(parent.random(1,3),parent.random(1,3));
		this.speed = new PVector(0,0);
		this.acc = new PVector(0,0);
		// For now, all circles have same mass and gravity
		this.setGravity(10);
		this.setMass(1*this.r);
	}

	// Draw circle
	public void display() {
	
		//parent.fill(255); // Color fill
	    parent.noFill();
		//parent.noStroke();
	    parent.strokeWeight(1);
	    parent.stroke(255);
	    
	    parent.ellipse(this.pos.x,this.pos.y,this.r,this.r);
	    //parent.point(this.pos.x,this.pos.y);
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

	//main method that combines all previous methods, and takes two arguments
	//tx and ty are inherited from forces(), and set the attractive/repulsive co-ords
	public void run(){
		display();
		drawVectors();
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

}