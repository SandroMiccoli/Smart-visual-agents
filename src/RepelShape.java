import processing.core.*;

/**
 * 
 * Class that defines the "repel" behavior.
 * 
 * The object that's decorated with this class will attract other shapes.
 * 
 */

public class RepelShape extends DecoratedShape {
	
	Shape ref;
	float amount=1;

	/**
	 * @param shapeReference
	 */
	public RepelShape(Shape shapeReference, float amount) {
		super(shapeReference);
		ref = shapeReference;
		this.amount = amount;
	}
	
	@Override
	public void run(){
		repelOtherShapes();
		super.run();

	}
	
	private void repelOtherShapes(){
		Shape[] allShapes = DecoratorTest.getShapes();
		for (int i = 0; i < allShapes.length; i++) {
			//allShapes[i].getPos().lerp(this.getPos(), (float) 0.5);
			if (!allShapes[i].equals(this))
				forces(allShapes[i]);
		}
	}

	public void forces(Shape targetLoc){
	    PVector dir = PVector.sub(targetLoc.getPos(),this.getPos());  //calculate the direction between a particle and targetLoc
	    float d = dir.mag();  //calculate how far away the particle is from targetLoc
	    dir.normalize();  //convert the measurement to a unit vector
	    dir.mult((float)0.5*amount);
	    
	    //calculate the strength of the force by factoring in a gravitational constant and the mass of a particle
	    //multiply by distance^2
	    //float force = (this.getGravity()*this.getMass()) / (d*d);
	    //dir.div(force);
	    
	    //dir.div(targetLoc.getMass());
		
		// only apply forces if in a certain distance
		if (d<targetLoc.getR())
			targetLoc.getSpeed().add(dir);
	}


}
