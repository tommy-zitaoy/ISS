package sensors;

/**
 * Wind speed sensor, maximum speed is 160
 * @author Group 5
 *
 */
public class WindSpeedSensor implements Sensor {

	public int getData() {
		return Sensor.rand.nextInt(160); 
	}
	
	public WindSpeedSensor() {
		super();
	}

	@Override
	public String call() throws Exception {
		return "Wind Speed(MPH) : " + getData();
	}
}
