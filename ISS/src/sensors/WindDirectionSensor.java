package sensors;

/**
 * Wind Direction Sensor gets a random number between 0 and 360
 * and then assigns it to a compass direction such as NW, SE, etc..
 * @author Group 5
 *
 */
public class WindDirectionSensor implements Sensor {

	public int getData() {
		return Sensor.rand.nextInt(361);
	}
	
	//Calls the Callable superclass inherited from Sensor
	public WindDirectionSensor() {
		super();
	}
	
	public String getDirection() {
		double direction = getData();
		// check if direction is less than 360, because 360 is max
		// if not round it to 2 decimal
		if (direction < 360) {
			direction += rand.nextDouble();
			direction = Math.round(direction * 100.0) / 100.0;
		}

		return determineDirection(direction);
	}
	
	public String determineDirection(double theValue) {
		if(theValue >= 337.5) {
			return "North";
			
		} else if (theValue <= 22.49) {
			return "North";
					
		}else if (theValue <= 67.49) {
			return "NorthEast";
			
		} else if (theValue <= 112.49) {
			return "East";
			
		} else if (theValue <= 157.49) {
			return "SouthEast";
			
		} else if (theValue <= 202.49) {
			return "South";
			
		} else if (theValue <= 247.49) {
			return "SouthWest";
			
		} else if (theValue <= 292.49) {
			return "West";
			
		} else{
			return "NorthWest";
		}
	}
	
	@Override
	public String call() throws Exception {
		return "Wind Direction(Cardinal): " + getDirection();
	}
}
