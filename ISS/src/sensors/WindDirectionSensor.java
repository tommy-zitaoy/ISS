package sensors;

public class WindDirectionSensor extends Thread implements Sensor {

	@Override
	public int getData() {
		return Sensor.rand.nextInt(361); 
	}
	
	public WindDirectionSensor() {
		super();
	}
}
