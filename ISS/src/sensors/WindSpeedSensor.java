package sensors;

import java.util.concurrent.Callable;

public class WindSpeedSensor extends Thread implements Callable<String> {

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
