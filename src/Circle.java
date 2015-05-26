import processing.core.*;

// Simple Circle Class

public class Circle {
  PApplet parent; // Necessary to access Processing's functions
  
  PVector pos;     // horizontal location of circle
  PVector speed; // speed of circle
  float r;     // radius of circle
  

  Circle(PApplet p) {
	parent = p;
    
	// random size
	r = parent.random(15,35);
	// All circles start at random position
    pos = new PVector(parent.random(0+r,parent.width-r), parent.random(0+r,parent.height-r));
    // All circles have a random positive speed
    speed = new PVector(parent.random(1,3),parent.random(1,3)); 
  }

  // Draw circle
  void display() {

   	parent.fill(255); // Color fill
    parent.noStroke();
    
    parent.ellipse(this.pos.x,this.pos.y,this.r,this.r);
  }

  // Move circle
  void move() {
    pos.x += speed.x;
    pos.y += speed.y;

    if (pos.x > parent.width - r || pos.x < 0+r) speed.x*=-1;
    if (pos.y > parent.height - r || pos.y < 0+r) speed.y*=-1;
  }

}