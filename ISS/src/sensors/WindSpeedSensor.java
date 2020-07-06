package sensors;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Wind speed sensor, maximum speed is 200 according to specifications
 * @author Group 5
 *
 */
public class WindSpeedSensor implements Sensor {
	
	int myWindSpeed;
	
	public WindSpeedSensor() {
		super();
		myWindSpeed = rand.nextInt(200);
	}
	
	public int getData() {
		setWindSpeed(this.myWindSpeed + ThreadLocalRandom.current().nextInt(-10, 10));
		return this.myWindSpeed; 
	}
	
	public void setWindSpeed(final int theInput)
	{
		this.myWindSpeed = (theInput >= 0 || theInput <= 160) ? theInput : 25;
	}

	@Override
	public String call() throws Exception {
		return "Wind Speed(MPH) : " + getData();
	}
}
