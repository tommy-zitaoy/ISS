package sensors;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class TemperatureSensor extends Thread implements Callable<String> {
	
	private int myTemp;
	
	public TemperatureSensor() {
		super();
	}
	
	public void setTemp(final int theInput)
	{
		this.myTemp = theInput;
	}
	
	public int getData() {
		setTemp(this.myTemp + ThreadLocalRandom.current().nextInt(-2, 1 + 1));
		return this.myTemp;
	}

	@Override
	public String call() throws Exception {
		return "Temperature(F): " + getData();
	}
}
