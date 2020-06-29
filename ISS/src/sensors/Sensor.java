package sensors;

import java.util.Random;

public interface Sensor {
	static Random rand = new Random();
	static int INTERVAL = 3000;
	public int getData();
	
	public default String sensorType(){
		return this.getClass().getSimpleName();
	};
}
