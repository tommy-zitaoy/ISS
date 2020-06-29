package sensors;

public class HumiditySensor extends Thread implements Sensor {

	@Override
	public int getData() {
		return Sensor.rand.nextInt(101); 
	}
	
	public HumiditySensor() {
		super();
	}
	
}
