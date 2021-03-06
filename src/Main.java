
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import processing.core.*;

public class Main extends PApplet {
	// singleton instance of the main simulation object
	private static Main mainInstanceSingleton = null;

	//	An array of circles
	private static Shape[] circles = new Shape[30];

	private final int MAX_SHAPES = 10;
	private int CURRENT_CIRCLES = 9;
	private ArrayList<Shape> attractors;
	private ArrayList<Shape> reppelers;

	//  main attractor shape (in the middle)
	private Shape mainAttractor;

	// instance of the control panel singleton
	P5ControlPanel controlP5;
	private boolean hide = true;

	static PGraphics pg;
	static PImage pi;

	public static Main getInstance() {
		/* does not need to check for existing
		 * instances; automatically instantiated
		 * at first run						  */
		return mainInstanceSingleton;
	}

	// ===========================================================
	/*	 setup @Overrides a processing function. It's charged with
	 *	 setting the whole simulation sketch up 	 			*/
	// ===========================================================
	@Override
	public void setup() {
		/* sets up singleton instance to be recovered
		 * by other classes 					   */
		mainInstanceSingleton = this;

		attractors = new ArrayList<Shape>();
		reppelers  = new ArrayList<Shape>();

		size(600,600);
		
		createCleanBackground(); //Gray BG
		
		// gets singleton
		controlP5 = P5ControlPanel.getInstance(this);
		controlP5.hide(); // start hidden

		// initialize all "circles"
		for (int i = 0; i < circles.length; i++) {
			circles[i] = new Circle(this);
		}

		// sets the main attractor in the middle
		mainAttractor = new Circle(this);
		mainAttractor.setPos(new PVector(width/2,height/2));
		mainAttractor.setSpeed(new PVector(0,0));
	}
	
	// ===========================================================
	// reset the background to plain gray
	// ===========================================================
	private void createCleanBackground() {
		pi = createImage(600, 600, RGB);
		
		for (int i = 0; i < pi.pixels.length; i++) {
			  pi.pixels[i] = color(191, 191, 191, i % pi.width * 2); 
			}
	}

	// ===========================================================
	// reset the whole sketch to factory settings
	// ===========================================================
	public void reset() {
		attractors.clear();
		reppelers.clear();

		for (int i = 0; i < circles.length; i++) {
			circles[i] = new Circle(this);
		}
		
		createCleanBackground();
		pg.image(pi,0,0);

		mainAttractor = new Circle(this);
		mainAttractor.setPos(new PVector(width/2,height/2));
		mainAttractor.setSpeed(new PVector(0,0));
	}

	// ===========================================================
	// responsible for redrawing the sketch simulation every cycle
	// ===========================================================

	private void persistDraw() {
		
		pg = createGraphics(600, 600);
		pg.beginDraw();
		pg.pushMatrix();
		pg.noStroke();
		pg.image(pi,0,0);
									
		/* Draw a rect over all elements with some alpha to create
		 * a "trail" illusion									*/
		
		tint(255,255-controlP5.getControllerValue("Trail")); // Creates trail illusion
		pg.popMatrix();
		pg.endDraw();
		
		image(pg, 0, 0); 

	}

	public synchronized void draw() {
		
		persistDraw();
			
		mainAttractor.run();

		// Display and move all circles
		for (int i = 0; i < CURRENT_CIRCLES; i++) {
			circles[i].run();
			// sets size and repel intensity according to controller
			circles[i].setR(controlP5.getControllerValue("Size"));
			circles[i].setAmount(controlP5.getControllerValue("RepelIntensity"));

			// check if each controller button is pressed
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

		if(controlP5.getControllerValue("Persist")==1)
			pi = get();
		

	}


	public static Shape[] getShapes(){
		return circles;
	}

	// ===========================================================
	// draws lines between each circle shape
	// ===========================================================
	private void connectShapes(){
		for (int i = 0; i < CURRENT_CIRCLES; i++) {
			for (int j = i; j < CURRENT_CIRCLES; j++) {
				strokeWeight(2);
				// gets color from the color picker controller
				stroke((controlP5.getPickerColor()));

				line(circles[i].getPos().x, circles[i].getPos().y, circles[j].getPos().x, circles[j].getPos().y);
			}
		}
	}

	// ===========================================================
	// handles calls to every toggle button on the painel
	// ===========================================================
	public void addButtonBehavior() {
		if(CURRENT_CIRCLES < circles.length)
			CURRENT_CIRCLES++;
	}

	public void removeButtonBehavior() {
		if (CURRENT_CIRCLES > 5)
			CURRENT_CIRCLES--;
	}

	public void freezeButtonBehavior(){
		pg = new PGraphics();
		pg = createGraphics(600, 600);
		pg.beginDraw();
		pg.pushMatrix();
		pg.noStroke();
		/* Draw a rect over all elements with some alpha to create
		 * a "trail" illusion									*/
		//pg.fill(191,255-controlP5.getControllerValue("Trail"));
		pg.rect(0,0,width,height);
		pg.popMatrix();
		pg.endDraw();
		image(pg, 0, 0);
	}

	public void attractButton() {
		//Add "attract" behavior to the middle circle
		this.mainAttractor = new AttractShape(mainAttractor);
	}

	public void repelButton() {
		//Add "repel" behavior to all circles
		for (int i = 0; i < circles.length; i++) {
			circles[i] = new RepelShape(circles[i],1);;
		}
	}

	//	 =========================================================
	/*	 listens for key press from the "spacebar" or "enter" keys
	 * 	 and handles creation of attracting or repelling shapes.*/
	//   ===========================================================
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
		else if (e.getKeyChar() == KeyEvent.VK_P) { // To use: SHIFT+P
			System.out.println("Print saved!");
			saveFrame("../prints/Screen########.png");
		}
	}

}
