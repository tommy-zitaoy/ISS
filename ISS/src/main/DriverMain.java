package main;

import sensors.HumiditySensor;
import sensors.TemperatureSensor;
import sensors.WindDirectionSensor;
import sensors.WindSpeedSensor;

public class DriverMain {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		TemperatureSensor temp = new TemperatureSensor();
		WindSpeedSensor windSpeed = new WindSpeedSensor();
		HumiditySensor humidity = new HumiditySensor();
		WindDirectionSensor windDirection = new WindDirectionSensor();
		
		System.out.println("----Console Receiver Output----");
		temp.start();
		windSpeed.start();
		humidity.start();
		windDirection.start();
		
		
		while(System.currentTimeMillis() - startTime < 100000) {
		try {
			System.out.println("----Console Receiver Output----");
			Thread.sleep(5000);
			temp.join();
			windSpeed.join();
			humidity.join();
			windDirection.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		}
	}

}
