import processing.core.*;

/**
 * 
 * Class that defines the "repel" behavior.
 * 
 * The object that's decorated with this class will repel other shapes.
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
	
	// repel behavior: will repel other shapes
	@Override
	public void run(){
		repelOtherShapes();
		super.run();

	}
	
	/*  sends the location of all other shapes
	 *	to each shape and make them repel each other */
	private void repelOtherShapes(){
		Shape[] allShapes = Main.getShapes();
		for (int i = 0; i < allShapes.length; i++) {

			if (!allShapes[i].equals(this))
				forces(allShapes[i]);
		}
	}

	/* this function applies more repelling force    
	 * every time it is called */
	public void forces(Shape targetLoc){
	    PVector dir = PVector.sub(targetLoc.getPos(),this.getPos());  //calculate the direction between a particle and targetLoc
	    float d = dir.mag();  //calculate how far away the particle is from targetLoc
	    dir.normalize();  //convert the measurement to a unit vector
	    dir.mult((float)0.5*amount);
		
		// only apply forces if in a certain distance
		if (d<targetLoc.getR()*2)
			targetLoc.getSpeed().add(dir);
	}


}
