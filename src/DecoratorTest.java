import processing.core.*;

public class DecoratorTest extends PApplet {
  //	An array of circles
  private static Shape[] circles = new Circle[5];
  
  Shape attractor;

  public void setup() {
    size(600,600);
    //background(0);
    
    // Initialize all "circles"
    for (int i = 0; i < circles.length; i++) {
      circles[i] = new Circle(this);
    }
    
    attractor = new Circle(this);
    attractor.setPos(new PVector(width/2,20));
  }

  public void draw() {
    background(100,10);
    // Move and display all "stripes"
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
