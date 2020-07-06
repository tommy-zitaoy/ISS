package main;

import java.util.List;
import java.util.Queue;

import sensors.IntegratedSensorSuite;
import sensors.Sensor;

/**
 * Driver class, simulates the console display.
 * Polls the sensor every 3 seconds, which can be changed by modifying the class constant in Sensor.
 * As a demonstration, it currently takes readings 1000 times.
 * @author Group 5
 *
 */
public class DriverMain {

	public static void main(String[] args) {
		IntegratedSensorSuite ISS = new IntegratedSensorSuite();
		int counter = 0;
		
		//ISS.accessArchives();
		
		while(counter < 1000) {
			try {
				List<String> readings = ISS.run();
				for(String reading : readings) {
					System.out.println(reading);
				}
				Thread.sleep(Sensor.INTERVAL);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			counter++;
		}
	}
}
