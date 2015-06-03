import processing.core.*;

public class DecoratorTest extends PApplet {
  //	An array of circles
  private static Shape[] circles = new Circle[5];
  
  Shape attractor;  
  Shape repeller;

  public void setup() {
    size(600,600);
    //background(0);
    
    // Initialize all "circles"
    for (int i = 0; i < circles.length; i++) {
      circles[i] = new Circle(this);
    }
    
    for (int i = 0; i < circles.length; i++) {
      Shape c = new RepelShape(circles[i]);
  	  circles[i] = c;
  	}

    attractor = new Circle(this);
    attractor.setPos(new PVector(width/2,height/3));
    
    repeller = new Circle(this);
    repeller.setPos(new PVector(width/2,height/3*2));
  }

  public void draw() {
    background(100,10);
    // Move and display all "stripes"
    for (int i = 0; i < circles.length; i++) {
      circles[i].run();
    }

    attractor.run();
    repeller.run();
  }
  
  public void mouseClicked(){
	attractor = new AttractShape(attractor);
	//repeller = new RepelShape(repeller);
	//circles[1] = new RepelShape(circles[1]);
	
  }
  
  public static Shape[] getShapes(){
	  return circles;
  }
}
