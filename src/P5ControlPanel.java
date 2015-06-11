
import controlP5.*;
import processing.core.*;

public class P5ControlPanel extends PApplet {
	
	// ControlP5 Example 2 : Style UI elements and setValue
	// ControlP5 by Andreas Schlegel : http://www.sojamo.de/libraries/controlP5/
	 

	ControlP5 controlP5;
	
	
	 
	public P5ControlPanel(PApplet source) {
//	  size(300,260);
//	  smooth();
	  
		controlP5 = new ControlP5(source);
		// change the default font to Verdana
		PFont p = createFont("Verdana",9); 
		controlP5.setControlFont(p);

		// change the original colors
		controlP5.setColorForeground(0xffaa0000);
		controlP5.setColorBackground(0xff660000);
		controlP5.setColorLabel(0xffdddddd);
		controlP5.setColorValue(0xffff88ff);
		controlP5.setColorActive(0xffff0000);

		// add the elments ( see example 1 for the parameters )
		controlP5.addBang("FREEZE",10,10,20,20);    
		controlP5.addButton("ADD FOCUS",1,70,10,60,20);  
		controlP5.addToggle("FROZEN?",false,170,10,20,20);    
		controlP5.addSlider("COLOR_1",0,255,128,10,80,10,100);
		controlP5.addSlider("COLOR_2",0,255,128,10,200,10,100);

		Slider s = controlP5.addSlider("TRAIL",0,255,128,70,80,100,10);
		// change sliderMode of the Slider object. The default is Slider.FIX
		s.setSliderMode(Slider.FLEXIBLE); 

		controlP5.addKnob("PARTICLE_SIZE",0,360,0,70,120,50);

//		Numberbox n = controlP5.addNumberbox("numberbox1",50,170,120,60,14);
//		// change Multiplier of the Numberbox ( default is 1 )
//		n.setMultiplier(30);
	}
	 
	public void controlEvent(ControlEvent theEvent) {
	  
	  if(theEvent.isController()) { 
	    
	    print("control event from : "+theEvent.controller().name());
	    println(", value : "+theEvent.controller().value());
	    
	    // clicking on bang1 sets toggle1 value to 1 (true)      
	    if(theEvent.controller().name()=="bang1") {
	     controlP5.controller("toggle1").setValue(1);     
	    }
	    
	    // clicking on button1 sets toggle1 value to 0 (false)
	    if(theEvent.controller().name()=="button1") {
	     controlP5.controller("toggle1").setValue(0);     
	    }
	    
	    // dragging slider1 changes the value of slider2
	    if(theEvent.controller().name()=="slider1") {
	     controlP5.controller("slider2").setValue(theEvent.controller().value());
	    }
	    
	    // changing the value of numberbox1 turns knob1
	    if(theEvent.controller().name()=="numberbox1") {
	     controlP5.controller("knob1").setValue(theEvent.controller().value());
	    }
	    
	  }  
	}

}