package main;

import sensors.HumiditySensor;
import sensors.TemperatureSensor;
import sensors.WindDirectionSensor;
import sensors.WindSpeedSensor;
import sensors.IntegratedSensorSuite;

public class DriverMain {

	public static void main(String[] args) {
		TemperatureSensor temp = new TemperatureSensor();
		WindSpeedSensor windSpeed = new WindSpeedSensor();
		HumiditySensor humidity = new HumiditySensor();
		WindDirectionSensor windDirection = new WindDirectionSensor();
		IntegratedSensorSuite ISS = new IntegratedSensorSuite(temp, windSpeed, windDirection, humidity);
		int counter = 0;
		
		while(counter < 1000) {
			
			try {
				ISS.run();
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			counter++;
		}
	}
}
