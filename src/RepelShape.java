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

	/**
	 * @param shapeReference
	 */
	public RepelShape(Shape shapeReference) {
		super(shapeReference);
		ref = shapeReference;
	}
	
	@Override
	public void run(){
		repelOtherShapes();
		super.run();
		//super.display();
		//super.bounds();

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
	    dir.mult((float)1.5);
	    
	    //calculate the strength of the force by factoring in a gravitational constant and the mass of a particle
	    //multiply by distance^2
	    //float force = (this.getGravity()*this.getMass()) / (d*d);
	     
		dir.div(targetLoc.getMass());
		targetLoc.getSpeed().add(dir);
	}


}
