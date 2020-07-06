package sensors;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Temp sensor defaults to 60 degrees with no other input, 
 * if the temperature is set manually it is checked to ensure that
 * it is in valid range. 
 * @author Group 5
 *
 */
public class TemperatureSensor implements Sensor {
	
	private int myTemp;
	
	//Calls Callable superclass and defaults myTemp to 60
	public TemperatureSensor() {
		super();
		myTemp = rand.nextInt(191) - 40;
	}
	
	//Check if the temp is valid, if so set temp, if not default to 60
	public void setTemp(final int theInput)
	{
		this.myTemp = (theInput < 150 || theInput >-40) ? theInput : 60;
	}
	
	//Get the current temp and add/subtract a couple to it.
	public int getData() {
		setTemp(this.myTemp + ThreadLocalRandom.current().nextInt(-2, 1 + 1));
		return this.myTemp;
	}

	//Call from the callable superclass, returns the needed data
	@Override
	public String call() throws Exception {
		return "Temperature(F): " + getData();
	}
}
