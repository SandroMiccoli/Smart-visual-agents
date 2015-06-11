
import java.awt.event.MouseEvent;

import processing.core.*;


public class DecoratorTest extends PApplet {
  //	An array of circles
	
  private static Shape[] circles = new Shape[50];
  
  Shape attractor;
  Shape reppeler;

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
//    attractor.setPos(new PVector(width/2,height/2));
    
    reppeler = new Circle(this);
//    reppeler.setPos(new PVector(width/2,height/2));
  }

  public void draw() {
    background(100,10);
    // Move and display all circles
    for (int i = 0; i < circles.length; i++) {
      circles[i].run();
    }

    attractor.run();
    reppeler.run();
  }
  
  public void mouseClicked(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON1) {
		attractor = new AttractShape(attractor);
//		attractor = new Circle(this);
	    attractor.setPos(new PVector(mouseX,mouseY));
	}
	else if (e.getButton() == MouseEvent.BUTTON3) {
		reppeler = new RepelShape(reppeler);
//		reppeler = new Circle(this);
	    reppeler.setPos(new PVector(mouseX,mouseY));
	}
	
  }
  
  public static Shape[] getShapes(){
	  return circles;
  }
}
