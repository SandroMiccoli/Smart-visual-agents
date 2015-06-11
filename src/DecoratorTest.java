
import processing.core.*;
import controlP5.*;



public class DecoratorTest extends PApplet {
  //	An array of circles
	
  private static Shape[] circles = new Shape[10];
  
  P5ControlPanel controlP5;
  
  Shape attractor;  
  Shape repeller;

  public void setup() {
    size(600,600);
    //background(0);
    
    controlP5 = new P5ControlPanel(this);
    
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
    repeller = new Circle(this);
    attractor.setPos(new PVector(width/2,height/2));
  }

  public void draw() {
    background(100,10);
    // Move and display all circles
    for (int i = 0; i < circles.length; i++) {
      circles[i].run();
    }

    attractor.run();
    repeller.run();
  }
  
  public void mouseClicked(){
	attractor = new AttractShape(attractor);
	repeller = new RepelShape(repeller);
	
  }
  
  public static Shape[] getShapes(){
	  return circles;
  }
}
