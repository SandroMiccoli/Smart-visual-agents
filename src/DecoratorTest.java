
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
    //frameRate(1);
    //background(0);
    
    controlP5 = new P5ControlPanel(this);
    
    // Initialize all "circles"
    for (int i = 0; i < circles.length; i++) {
     circles[i] = new Circle(this);
    }
    
    //Add "repel" behaviour to all circles...
    for (int i = 0; i < circles.length; i++) {
      Shape c = new RepelShape(circles[i],1);
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
      circles[i].setR(controlP5.getControllerValue("Size"));
      
      if(controlP5.getControllerValue("Vectors")==1)
    	  circles[i].drawVectors();
    	
    }
    if(controlP5.getControllerValue("Connect")==1)
    	connectShapes();
    
    //System.out.println(controlP5.getControllerValue("Attract"));

    attractor.run();
  }
 
  
	  public static Shape[] getShapes(){
		  return circles;
	  }
	  
	  private void connectShapes(){
		  for (int i = 0; i < circles.length; i++) {
			  //for (int j = circles.length/2-1; j < circles.length; j++) {
			  for (int j = 0; j < circles.length; j++) {
				  strokeWeight(1);
				  stroke(0);
				  line(circles[i].getPos().x, circles[i].getPos().y, circles[j].getPos().x, circles[j].getPos().y);
			  }
		    }
	  }
	  
	public void controlEvent(ControlEvent theEvent) {
		  
		  if(theEvent.isController()) { 
			  if(theEvent.controller().name()=="Attract") {
				  attractor = new AttractShape(attractor);
			  }
		  }
	}
}
