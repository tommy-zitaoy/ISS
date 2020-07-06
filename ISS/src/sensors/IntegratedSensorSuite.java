package sensors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

/**
 * IntegratedSensorSuite is a control for all of the sensors, it makes them
 * each a Callable object and executes them concurrently while storing the results
 * in a future object. When all are complete, it outputs them to the console as well
 * as to a text file for archival use.
 * @author Group 5
 *
 */
public class IntegratedSensorSuite {
	/*
	 * Executor to control the various sensor threads.
	 */
	ScheduledExecutorService executor;
	
	//All of the sensors as Callable objects
	Callable<String> tempSensor = new TemperatureSensor();
	Callable<String> speedSensor = new WindSpeedSensor();
	Callable<String> dirSensor = new WindDirectionSensor();
	Callable<String> humiditySensor = new HumiditySensor();
	Callable<String> solarSensor = new SolarSensor();
	Callable<String> headerCon = () -> { return ("----Console Receiver Output----");};
	
	//FileWriter object for archiving data.
	FileWriter out;
	
	
	public IntegratedSensorSuite() {
		executor = Executors.newScheduledThreadPool(7);
		try {
			out = new FileWriter(".//issreadings.txt", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Creates Future objects and stores them to be returned and used by the driver (console display)
	 * @return a list of readings from each sensor
	 */
	public List<String> run() {
		List<Future<String>> list = new ArrayList<>();
		Future<String> header = executor.submit(headerCon);
		Future<String> temp = executor.submit(tempSensor);
		Future<String> speed = executor.submit(speedSensor);
		Future<String> dir = executor.submit(dirSensor);
		Future<String> humidity = executor.submit(humiditySensor);
		Callable<String> newLine = () -> {return "";};
		Future<String> nl = executor.submit(newLine);
		Future<String> solar = executor.submit(solarSensor);
		
		list.add(header);
		list.add(temp);
		list.add(speed);
		list.add(dir);
		list.add(humidity);
		list.add(solar);
		list.add(nl);;
		
		List<String> consoleOutList = new LinkedList<>();
		
		
		//Add each threads reading to a output list and also send it to 
		//archival text file.
		for(Future<String> future : list) {
			try {
				String reading = future.get();
				consoleOutList.add(reading);
				out.append(reading);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return consoleOutList;
	}
	
	/**
	 * Method for retrieving and displaying archival data
	 */
	public void accessArchives() {
		Scanner fileIn = null;
		try {
			fileIn = new Scanner(new File(".\\issreadings.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(fileIn.hasNext()) {
			System.out.println(fileIn.nextLine());
		}
	}
	
}