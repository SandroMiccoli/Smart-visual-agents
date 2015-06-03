import processing.core.PVector;

/*
 * Reference to original object and works as an interface to the Shape interface.
 * 
 */

public class DecoratedShape implements Shape {
	
	// private reference to the shape being decorated
	private Shape privateShape = null;
	
	public DecoratedShape(Shape shapeReference){
		this.privateShape = shapeReference;
	}

	@Override
	public void display() {
		privateShape.display();
	}

	@Override
	public void move() {
		privateShape.move();
	}

	@Override
	public void run() {
		privateShape.run();
	}

	@Override
	public void bounds() {
		privateShape.bounds();
	}

	@Override
	public PVector getPos() {
		return privateShape.getPos();
	}

	@Override
	public void setPos(PVector pos) {
		privateShape.setPos(pos);
		
	}

	@Override
	public PVector getSpeed() {
		return privateShape.getSpeed();
	}

	@Override
	public void setSpeed(PVector speed) {
		privateShape.setSpeed(speed);
	}

	@Override
	public float getGravity() {
		return privateShape.getGravity();
	}

	@Override
	public void setGravity(float gravity) {
		privateShape.setGravity(gravity);
		
	}

	@Override
	public float getMass() {
		return privateShape.getMass();
	}

	@Override
	public void setMass(float mass) {
		privateShape.setMass(mass);
	}

}
