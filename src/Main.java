
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import processing.core.*;
import controlP5.*;



public class Main extends PApplet {
  //	An array of circles
  private static Shape[] circles = new Shape[30]; // Max amount of circles
  
  private final int MAX_SHAPES = 10;
  private int CURRENT_CIRCLES = 9;
  private ArrayList<Shape> attractors;
  private ArrayList<Shape> reppelers;
  
  private Shape mainAttractor;
  
  P5ControlPanel controlP5;
  private boolean hide = false;
  
//  Shape attractor;

  public void setup() {
	attractors = new ArrayList<Shape>();
	reppelers  = new ArrayList<Shape>();
	
    size(600,600);
    //frameRate(1);
    //background(0);
    
    controlP5 = new P5ControlPanel(this);
    
    // Initialize all "circles"
    for (int i = 0; i < circles.length; i++) {
     circles[i] = new Circle(this);
    }
    
    mainAttractor = new Circle(this);
    mainAttractor.setPos(new PVector(width/2,height/2));
    mainAttractor.setSpeed(new PVector(0,0));
  }
  
  public void reset() {
	  attractors.clear();
	  reppelers.clear();
	  
	  for (int i = 0; i < circles.length; i++) {
		  circles[i] = new Circle(this);
	  }
	  
	  mainAttractor = new Circle(this);
	  mainAttractor.setPos(new PVector(width/2,height/2));
	  mainAttractor.setSpeed(new PVector(0,0));
  }

  public synchronized void draw() {
	  
    //background(100,10);
	// Draw a rect over all elements with some alpha to create a "trail" illusion
	pushMatrix();
	noStroke();
    fill(185,255-controlP5.getControllerValue("Trail"));
    rect(0,0,width,height);
    popMatrix();
    
    mainAttractor.run();
    
    // Move and display all circles
    for (int i = 0; i < CURRENT_CIRCLES; i++) {
      circles[i].run();
      circles[i].setR(controlP5.getControllerValue("Size"));
      circles[i].setAmount(controlP5.getControllerValue("RepelIntensity"));
      
      if(controlP5.getControllerValue("Vectors")==1)
    	  circles[i].drawVectors();
    	
    }
    
    if(controlP5.getControllerValue("Connect")==1)
    	connectShapes();
    
    
    for (Shape attrac : attractors) {
    	attrac.run();
    }
    
    for (Shape reppel : reppelers) {
    	reppel.run();
    }
  }
 
  
  public static Shape[] getShapes(){
	  return circles;
  }
  
  private void connectShapes(){
	  for (int i = 0; i < CURRENT_CIRCLES; i++) {
		  //for (int j = circles.length/2-1; j < circles.length; j++) {
		  for (int j = i; j < CURRENT_CIRCLES; j++) {
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
				mainAttractor = new AttractShape(mainAttractor);
//				for (int i = 0; i < circles.length; i++) {
//				      circles[i] = new AttractShape(circles[i]);;
//				}
			}
			if(theEvent.controller().name()=="Repel") {
			    //Add "repel" behaviour to all circles...
			    for (int i = 0; i < circles.length; i++) {
			      circles[i] = new RepelShape(circles[i],1);;
			  	}

			}
			if(theEvent.controller().name()=="Reset") {
				reset();
			}
			if(theEvent.controller().name()=="Add" && CURRENT_CIRCLES<circles.length) {
				CURRENT_CIRCLES++;
			}
			if(theEvent.controller().name()=="Remove" && CURRENT_CIRCLES>5) {
				CURRENT_CIRCLES--;
			}
		}
	}
	
	public synchronized void keyPressed(KeyEvent e) {
		Random r = new Random();

		if (e.getKeyChar() == KeyEvent.VK_SPACE && attractors.size() < MAX_SHAPES) {
	    	Shape attractor = new Circle(this);
	    	attractor = new AttractShape(attractor);
	    	attractor.setPos(new PVector(r.nextInt(500) + 50,r.nextInt(500) + 50));
	    	attractor.setR(10);
	    	attractors.add(attractor);
		}
		else if (e.getKeyChar() == KeyEvent.VK_ENTER && reppelers.size() < MAX_SHAPES) {
			Shape reppeler = new Circle(this);
	    	reppeler = new RepelShape(reppeler, 2);
	    	reppeler.setPos(new PVector(r.nextInt(550) + 50,r.nextInt(550) + 50));
	    	reppeler.setR(70);
	    	reppelers.add(reppeler);
		}
		else if (e.getKeyChar() == KeyEvent.VK_TAB) {
			if (hide){
				controlP5.hide();
				hide = false;
			}
			else{
				controlP5.show();
				hide = true;
			}
		}
		else if (e.getKeyChar() == KeyEvent.VK_2) {
			System.out.println(CURRENT_CIRCLES);
			if (CURRENT_CIRCLES<circles.length)
				CURRENT_CIRCLES++;
		}
		else if (e.getKeyChar() == KeyEvent.VK_1) {
			System.out.println(CURRENT_CIRCLES);
			if (CURRENT_CIRCLES>5)
				CURRENT_CIRCLES--;
		}
	}
	
}
