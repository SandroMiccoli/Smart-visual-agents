
import processing.core.*;


public class DecoratorTest extends PApplet {
  //	An array of circles
	
  private static Shape[] circles = new Shape[5];
  
  Shape attractor;  

  public void setup() {
    size(600,600);
    //background(0);
    
    // Initialize all "circles"
    for (int i = 0; i < circles.length; i++) {
     circles[i] = new Circle(this);
    }
    
    //Add "repel" behaviour to all circles...
    for (int i = 0; i < circles.length; i++) {
      Shape c = new RepelShape(circles[i]);
      circles[i] = c;
  	}

    attractor = new Circle(this);
    attractor.setPos(new PVector(width/2,height/2));
  }

  public void draw() {
    background(100,10);
    // Move and display all circles
    for (int i = 0; i < circles.length; i++) {
      circles[i].run();
    }

    attractor.run();
  }
  
  public void mouseClicked(){
	attractor = new AttractShape(attractor);
  }
  
  public static Shape[] getShapes(){
	  return circles;
  }
}
