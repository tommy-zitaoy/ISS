package sensors;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class HumiditySensor extends Thread implements Callable<String> {
	
	private int myHumidity;
	
	public HumiditySensor() {
		super();
	}
	
	public int getData() {
		setHumidity(this.myHumidity + ThreadLocalRandom.current().nextInt(-1, 1 + 1));
		return this.myHumidity; 
	}
	
	public void setHumidity(final int theInput)
	{
		this.myHumidity = theInput;
	}
	
	public synchronized void run() {
		System.out.println("Humidity: " + getData() + "%");
	}

	@Override
	public String call() throws Exception {
		return "Humidity: " + getData() + "%";
	}
}
