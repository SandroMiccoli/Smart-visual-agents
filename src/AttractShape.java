import processing.core.*;

/**
 * 
 * Class that defines the "attraction" behavior.
 * 
 * The object that's decorated with this class will attract other shapes.
 * 
 */

public class AttractShape extends DecoratedShape {

	/**
	 * @param shapeReference
	 */
	public AttractShape(Shape shapeReference) {
		super(shapeReference);
	}
	
	@Override
	public void run(){
		attractOtherShapes();
		super.run();
		//super.display();
		//super.bounds();

	}
	
	private void attractOtherShapes(){
		Shape[] allShapes = DecoratorTest.getShapes();
		for (int i = 0; i < allShapes.length; i++) {
			//allShapes[i].getPos().lerp(this.getPos(), (float) 0.5);
			forces(allShapes[i]);
			
		}
	}

	public void forces(Shape targetLoc){
	    PVector dir = PVector.sub(targetLoc.getPos(),this.getPos());  //calculate the direction between a particle and targetLoc
	    float d = dir.mag();  //calculate how far away the particle is from targetLoc
//	    System.out.println(d);
	    dir.normalize();  //convert the measurement to a unit vector
//	    System.out.println(dir);
	    dir.mult(-1*d);
	    
	    //calculate the strength of the force by factoring in a gravitational constant and the mass of a particle
	    //multiply by distance^2
	    float force = (this.getGravity()*this.getMass()) / (d*d);
	    //if (d<15)
	    //	force=0;
	    
	    dir.mult(force);
	    
		dir.div(targetLoc.getMass());
		targetLoc.getSpeed().add(dir);
	}
}
