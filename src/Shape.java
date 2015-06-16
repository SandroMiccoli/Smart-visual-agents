import processing.core.PVector;

/*
 * Interface for every Shape object
 */

public interface Shape {
	
	public void display();
	public void move();
	public void run();
	public void bounds();
	public void drawVectors();
	public PVector getPos();
	public void setPos(PVector pos);
	public PVector getSpeed();
	public void setSpeed(PVector speed);
	public float getR();
	public void setR(float r);
	public float getGravity();
	public void setGravity(float gravity);
	public float getMass();
	public void setMass(float mass);
	
}
