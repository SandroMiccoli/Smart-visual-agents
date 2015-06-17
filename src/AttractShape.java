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
	
	// attract behavior: attracts other shapes
	@Override
	public void run(){
		attractOtherShapes();
		super.run();
	}
	
	/*  sends the location of all other shapes
	 *	to each shape and make them attract each other */
	private void attractOtherShapes(){
		
		Shape[] allShapes = Main.getShapes();
		
		for (int i = 0; i < allShapes.length; i++) {
			forces(allShapes[i]);
			
		}
	}

	/* this function applies more attraction force    
	 * every time it is called */
	public void forces(Shape targetLoc){
		
	    PVector dir = PVector.sub(targetLoc.getPos(),this.getPos());  //calculate the direction between a particle and targetLoc
	    float d = dir.mag();  //calculate how far away the particle is from targetLoc
	    dir.normalize();  //convert the measurement to a unit vector
	    dir.mult((float)-0.5);

		targetLoc.getSpeed().add(dir);
	}


}
