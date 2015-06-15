
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import processing.core.*;


public class DecoratorTest extends PApplet {
  //	An array of circles
	
  private static Shape[] circles = new Shape[50];
  private ArrayList<Shape> attractors;
  private ArrayList<Shape> reppelers;

  public void setup() {
	attractors = new ArrayList<Shape>();
	reppelers = new ArrayList<Shape>();
	
    size(600,600);
    //background(0);
    
    // Initialize all "circles"
    for (int i = 0; i < circles.length; i++) {
     circles[i] = new Circle(this, 255);
    }
    
    //Add "repel" behaviour to all circles...
    for (int i = 0; i < circles.length; i++) {
      Shape c = new RepelShape(circles[i]);
      circles[i] = c;
  	}
  }

  public void draw() {
    background(100,10);
    // Move and display all circles
    for (int i = 0; i < circles.length; i++) {
      circles[i].run();
    }

    for (Shape attractor : attractors) {
    	attractor.run();
    }
    
    for (Shape reppeler : reppelers) {
    	reppeler.run();
    }
  }
  
  public void mouseClicked(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON1) {
    	Shape attractor = new Circle(this, 100);
    	attractor = new AttractShape(attractor);
    	attractor.setPos(new PVector(mouseX,mouseY));
    	attractors.add(attractor);
	}
	else if (e.getButton() == MouseEvent.BUTTON3) {
		Shape reppeler = new Circle(this, 200);
    	reppeler = new RepelShape(reppeler);
    	reppeler.setPos(new PVector(mouseX,mouseY));
    	reppelers.add(reppeler);
	}
	
  }
  
  public static Shape[] getShapes(){
	  return circles;
  }
}
