package sensors;

public class TemperatureSensor extends Thread implements Sensor {

	@Override
	public int getData() {
		return (Sensor.rand.nextInt(140) - 30); 
	}
	
	public TemperatureSensor() {
		super();
	}

}
