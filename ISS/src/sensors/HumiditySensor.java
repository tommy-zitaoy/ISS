package sensors;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Humidity sensor, has validation checks to ensure that the humidity falls into a
 * realistic range
 * @author Group 5
 *
 */
public class HumiditySensor implements Sensor {
	
	private int myHumidity;
	
	public HumiditySensor() {
		super();
		myHumidity = 35;
	}
	
	public int getData() {
		setHumidity(this.myHumidity + ThreadLocalRandom.current().nextInt(-1, 1 + 1));
		return this.myHumidity; 
	}
	
	public void setHumidity(final int theInput)
	{
		this.myHumidity = (theInput >= 0 || theInput <= 100)  ? theInput : 35;
	}

	@Override
	public String call() throws Exception {
		return "Humidity: " + getData() + "%";
	}
}
