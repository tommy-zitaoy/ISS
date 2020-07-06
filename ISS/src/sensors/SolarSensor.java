package sensors;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Solar sensor shows watts per square meter, range is 0-1800 according
 * to technical specifications
 * @author Group 5
 */
public class SolarSensor implements Sensor {
	
	private int myWatts;
	
	//Calls Callable superclass and gets random wattage
	public SolarSensor() {
		super();
		myWatts = rand.nextInt(1801);
	}
	
	//Check if the watts is valid, if so set watts, if not default to 200
	public void setWatts(final int theInput)
	{
		this.myWatts = (theInput > 0 || theInput < 1800) ? theInput : 200;
	}
	
	//Get the current watts and add/subtract a couple to it.
	public int getData() {
		setWatts(this.myWatts + ThreadLocalRandom.current().nextInt(-20, 20 + 20));
		return this.myWatts;
	}

	//Call from the callable superclass, returns the needed data
	@Override
	public String call() throws Exception {
		return "Solar Radiation(Watts/sq meter): " + getData();
	}
}
