
import processing.core.*;
import controlP5.*;



public class DecoratorTest extends PApplet {
  //	An array of circles
	
  private static Shape[] circles = new Shape[12];
  
  P5ControlPanel controlP5;
  
  Shape attractor;

  public void setup() {
    size(600,600);
    //frameRate(1);
    //background(0);
    
    controlP5 = new P5ControlPanel(this);
    
    // Initialize all "circles"
    for (int i = 0; i < circles.length; i++) {
     circles[i] = new Circle(this);
    }
    
    attractor = new Circle(this);
    attractor.setPos(new PVector(width/2,height/2));
    attractor.setSpeed(new PVector(0,0));
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
    
    attractor.run();
  }
 
  
	  public static Shape[] getShapes(){
		  return circles;
	  }
	  
	  private void connectShapes(){
		  for (int i = 0; i < circles.length; i++) {
			  //for (int j = circles.length/2-1; j < circles.length; j++) {
			  for (int j = i; j < circles.length; j++) {
				  strokeWeight(1);
				  stroke(0);
 			      //stroke(random(255),random(255),random(255));
				  line(circles[i].getPos().x, circles[i].getPos().y, circles[j].getPos().x, circles[j].getPos().y);
			  }
		    }
	  }
	  
	public void controlEvent(ControlEvent theEvent) {
		  
		if(theEvent.isController()) { 
			if(theEvent.controller().name()=="Attract") {
				attractor = new AttractShape(attractor);
			}
			if(theEvent.controller().name()=="Repel") {
			    //Add "repel" behaviour to all circles...
			    for (int i = 0; i < circles.length; i++) {
			      circles[i] = new RepelShape(circles[i],1);;
			  	}

			}
		}
	}
}
