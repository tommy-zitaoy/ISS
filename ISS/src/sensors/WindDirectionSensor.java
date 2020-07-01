package sensors;

import java.util.concurrent.Callable;

public class WindDirectionSensor extends Thread implements Callable<String> {

	public int getData() {
		return Sensor.rand.nextInt(361);
	}
	
	public WindDirectionSensor() {
		super();
	}
	
	public String getDirection() {
		int direction = getData();
		String cardinalPoint = "North";
		
		//Hyeong: hard-coded for now, not a brilliant solution but it does the job 
		if(direction >= 337.5 && direction <= 22.49) {
			cardinalPoint = "North";
			
		} else if (direction >= 22.5 && direction <= 67.49) {
			cardinalPoint = "NorthEast";
			
		} else if (direction >= 67.5 && direction <= 112.49) {
			cardinalPoint = "East";
			
		} else if (direction >= 112.5 && direction <= 157.49) {
			cardinalPoint = "SouthEast";
			
		} else if (direction >= 157.5 && direction <= 202.49) {
			cardinalPoint = "South";
			
		} else if (direction >= 202.5 && direction <= 247.49) {
			cardinalPoint = "SouthWest";
			
		} else if (direction >= 247.5 && direction <= 292.49) {
			cardinalPoint = "West";
			
		} else if (direction >= 292.5 && direction <= 337.49) {
			cardinalPoint = "NorthWest";
		}
		
		return cardinalPoint;
	}
	
	public synchronized void run() {
		System.out.println("Wind Direction(Cardinal): " + getDirection());
	}

	@Override
	public String call() throws Exception {
		return "Wind Direction(Cardinal): " + getDirection();
	}
}
