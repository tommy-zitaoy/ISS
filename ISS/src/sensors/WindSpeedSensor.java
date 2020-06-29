package sensors;

public class WindSpeedSensor extends Thread implements Sensor {

	@Override
	public int getData() {
		return Sensor.rand.nextInt(160); 
	}
	
	public WindSpeedSensor() {
		super();
	}
	
}
