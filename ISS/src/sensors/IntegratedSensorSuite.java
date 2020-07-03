package sensors;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
	Callable<String >headerCon = () -> { return ("----Console Receiver Output----");};
	
	//PrintStream object for archiving data.
	FileWriter out;
	
	
	public IntegratedSensorSuite() {
		executor = Executors.newScheduledThreadPool(5);
		try {
			out = new FileWriter(".//issreadings.txt", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<String> run() {
		List<Future<String>> list = new ArrayList<>();
		Future<String> header = executor.submit(headerCon);
		Future<String> temp = executor.submit(tempSensor);
		Future<String> speed = executor.submit(speedSensor);
		Future<String> dir = executor.submit(dirSensor);
		Future<String> humidity = executor.submit(humiditySensor);
		Callable<String> newLine = () -> {return "";};
		Future<String> nl = executor.submit(newLine);
		
		list.add(header);
		list.add(temp);
		list.add(speed);
		list.add(dir);
		list.add(humidity);
		list.add(nl);;
		
		List<String> consoleOutList = new LinkedList<>();
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
	
	
	
}
