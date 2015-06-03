import processing.core.PVector;

/*
 * Interface for a Shape object
 */

public interface Shape {
	
	public void display();
	public void move();
	public void run();
	public void bounds();
	public PVector getPos();
	public void setPos(PVector pos);
	public PVector getSpeed();
	public void setSpeed(PVector speed);
	public float getGravity();
	public void setGravity(float gravity);
	public float getMass();
	public void setMass(float mass);
	
}
