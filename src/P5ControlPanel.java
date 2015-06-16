
import java.awt.event.KeyEvent;
import java.util.Random;

import controlP5.*;
import processing.core.*;

public class P5ControlPanel extends PApplet {

	class Info {
		  float a;
		  float n = 0;
		  String txt = "";
		  Textarea label;

		  Info() {
		    label = controlP5.addTextarea("Hello\nWorld")
		               .setSize(200,200)
		               .setPosition(300,40)
		               .setColor(color(255))
		               .setColorBackground(color(100,0))
		               .setLineHeight(12);

		  }

		  void update() {
		    a += (n-a)*0.1;
		    label.setColorBackground(color(100,255*a));
		  }
		}


	// ControlP5 Singleton - Instanciation, Setup and Event Controller

	private static PApplet controlParent = null;
	private static P5ControlPanel instance = null;
	private static ControlP5 controlP5;

	protected P5ControlPanel(PApplet source) {

		CallbackListener cb;
		Info info;

		cb = new CallbackListener() {

			public void controlEvent(CallbackEvent theEvent) {
				switch(theEvent.getAction()) {
				case(ControlP5.ACTION_ENTER):
					info.n = 1;
				info.label.setText(theEvent.getController().getInfo());
				cursor(HAND);
				break;
				case(ControlP5.ACTION_LEAVE):
				case(ControlP5.ACTION_RELEASEDOUTSIDE):
					info.n = 0;
				cursor(ARROW);
				break;
				}
			}
		};

		controlP5 = new ControlP5(source);

		// change the default font to Verdana
		PFont p = createFont("Verdana",9);
		controlP5.setControlFont(p);

		// change the original colors
		controlP5.setColorForeground(0xffaa0000);
		controlP5.setColorBackground(0xff660000);
		controlP5.setColorLabel(0x00000000);
		controlP5.setColorValue(0xffff88ff);
		controlP5.setColorActive(0xffff0000);

		// adds each control element to the panel
		controlP5.addKnob("Size",10,250,30,80,10,60);
		controlP5.addKnob("RepelIntensity",1,5,1,80,90,60);
		controlP5.addSlider("Trail",0,255,10,10,220,10,100);
		controlP5.addToggle("Vectors",false,10,10,20,20);
		controlP5.addToggle("Connect",false,10,50,20,20);
		controlP5.addBang("Reset", 10,170,20,20);
		controlP5.addBang("Attract",10,90,20,20);
		controlP5.addBang("Repel",10,130,20,20);
	}

	// makes sure only one instance of the panel exists and is used
	public static P5ControlPanel getInstance(PApplet source) {
		if (instance == null) {
			instance = new P5ControlPanel(source);
			return instance;
		}
		else
			return instance;
	}

	// handles each Control Event - forwarded here from the main class
	public void controlEvent(ControlEvent theEvent) {

		if(theEvent.isController()) {

			print("control event from : "+theEvent.controller().name());
			println(", value : "+theEvent.controller().value());

			// clicking on bang1 sets toggle1 value to 1 (true)
			if(theEvent.controller().name()=="Attract") {
				System.out.println("yeap");
				controlP5.controller("toggle1").setValue(1);
			}

			if(theEvent.controller().name()=="Attract") {
				Main.getInstance().attractBehavior();
			}

			if(theEvent.controller().name()=="Repel") {
				Main.getInstance().repelBehavior();

			}
			if(theEvent.controller().name()=="Reset") {
				Main.getInstance().reset();
			}
		}

	}

	public synchronized void keyPressed(KeyEvent e) {
		Random r = new Random();

		if (e.getKeyChar() == KeyEvent.VK_SPACE ) {
			//				&& attractors.size() < MAX_SHAPES) {

			System.out.println("PEGOU?");

			/*			Shape attractor = new Circle(this);
			attractor = new AttractShape(attractor);
			attractor.setPos(new PVector(r.nextInt(500) + 50,r.nextInt(500) + 50));
			attractor.setR(10);
			attractors.add(attractor);*/
		}

		else if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			System.out.println("PEGOU?");
			//				&& reppelers.size() < MAX_SHAPES) {
			/*			Shape reppeler = new Circle(this);
			reppeler = new RepelShape(reppeler, 1);
			reppeler.setPos(new PVector(r.nextInt(550) + 50,r.nextInt(550) + 50));
			reppeler.setR(70);
			reppelers.add(reppeler);*/
		}
	}

	public float getControllerValue(String c){
		return controlP5.controller(c).value();
	}

}
