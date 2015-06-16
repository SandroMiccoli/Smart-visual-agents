
import controlP5.*;
import processing.core.*;

public class P5ControlPanel extends PApplet {

	// ControlP5 Singleton - Instanciation, Setup and Event Controller

	private static PApplet controlParent = null;
	private static P5ControlPanel instance = null;
	private static ControlP5 controlP5;

	protected P5ControlPanel(PApplet source) {

		controlP5 = new ControlP5(source);
		controlP5.addCallback(createCallBackListener());

		// change the default font to Verdana
		PFont p = createFont("Verdana",9);
		controlP5.setControlFont(p);

		// change the original colors
		controlP5.setColorForeground(0xffaa0000);
		controlP5.setColorBackground(0xff660000);
		controlP5.setColorLabel(0x00000000);
		controlP5.setColorValue(0xffff88ff);
		controlP5.setColorActive(0xffff0000);

		controlP5.addKnob("Size",10,100,30,80,10,60);
		controlP5.addKnob("RepelIntensity",1,10,1,80,90,60);
		controlP5.addSlider("Trail",100,255,100,10,220,10,100);
		controlP5.addToggle("Vectors",false,10,10,20,20);
		controlP5.addToggle("Connect",false,10,50,20,20);
		controlP5.addBang("Reset", 10,170,20,20);
		controlP5.addBang("Attract",10,90,20,20);
		controlP5.addBang("Repel",10,130,20,20);
    controlP5.addBang("Add",source.width-100,10,20,20);
		controlP5.addBang("Remove",source.width-50,10,20,20);
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

	//returns values of controller elements.
	public float getControllerValue(String c){
		return controlP5.controller(c).value();
	}


	/*	 creates a callback listener so the panel knows about incoming
	 *   control events												*/
	private CallbackListener createCallBackListener() {
		CallbackListener cb;

		cb = new CallbackListener() {

			public void controlEvent(CallbackEvent theEvent) {

				if(theEvent.getAction()==controlP5.ACTION_PRESSED) {

					switch (theEvent.getController().getLabel()) {

					case("Reset"):
						Main.getInstance().reset();
					break;
					case("Attract"):
						Main.getInstance().attractButton();
					break;
					case("Repel"):
						Main.getInstance().repelButton();
					break;
					}
				}
			}
		};

		return cb;
  }

	public void hide(){
		controlP5.hide();
	}

	public void show(){
		controlP5.show();
	}

}
