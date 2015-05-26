import processing.core.*;

public class MyProcessingSketch extends PApplet {
  //	An array of circles
  Circle[] circles = new Circle[50];

  public void setup() {
    size(600,600);
    // Initialize all "circles"
    for (int i = 0; i < circles.length; i++) {
      circles[i] = new Circle(this);
    }
  }

  public void draw() {
    background(100);
    // Move and display all "stripes"
    for (int i = 0; i < circles.length; i++) {
      circles[i].move();
      circles[i].display();
    }
  }
}
