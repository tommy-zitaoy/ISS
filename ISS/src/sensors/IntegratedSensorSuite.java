package sensors;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class IntegratedSensorSuite {
	TemperatureSensor tempSensor;
	WindSpeedSensor windSpeedSensor;
	WindDirectionSensor windDirectionSensor;
	HumiditySensor humiditySensor;
	ScheduledExecutorService executor;
	Callable<String> headerCon;
	Callable<String> callable1;
	Callable<String> callable2;
	Callable<String> callable3;
	Callable<String> callable4;
	Callable<String> callable5;
	
	
	public IntegratedSensorSuite(TemperatureSensor theTemp, WindSpeedSensor theWindSpeed, 
			WindDirectionSensor theWindDir, HumiditySensor theHumidity) {
		executor = Executors.newScheduledThreadPool(5);
		tempSensor = theTemp;
		tempSensor.setTemp(60);
		windSpeedSensor = theWindSpeed;
		windDirectionSensor = theWindDir;
		humiditySensor = theHumidity;
		humiditySensor.setHumidity(47);
		headerCon = () -> { return ("----Console Receiver Output----");};
		callable1 = tempSensor;
		callable2 = windSpeedSensor;
		callable3 = windDirectionSensor;
		callable4 = humiditySensor;
		callable5 = headerCon;

	}
	
	public void run() {
		List<Future<String>> list = new ArrayList<>();
		Future<String> header = executor.submit(callable5);
		Future<String> temp = executor.submit(callable1);
		Future<String> speed = executor.submit(callable2);
		Future<String> dir = executor.submit(callable3);
		Future<String> humidity = executor.submit(callable4);
		Callable<String> newLine = () -> {return "";};
		Future<String> nl = executor.submit(newLine);
		
		list.add(header);
		list.add(temp);
		list.add(speed);
		list.add(dir);
		list.add(humidity);
		list.add(nl);;
		for(Future<String> future : list) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		Future<String> newLine = executor.submit(() -> {System.out.println();});
	}
	
	
	
}
