package sensors;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Sensor interface provides the basic outline for the sensors,
 * it extends callable so that we can control the threading
 * @author Group 5
 *
 */
public interface Sensor extends Callable<String> {
	//Static random object for the sensors
	static Random rand = new Random();
	
	//The interval for the console to poll the ISS
	static int INTERVAL = 3000;
	
	//Method to return data for that specific sensor
	public int getData();
	
	//Default class for sensor type, I figured it will be useful for the next
	//part of the project.
	public default String sensorType(){
		return this.getClass().getSimpleName();
	};
}
