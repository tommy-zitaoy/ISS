import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Queue;

import org.junit.Test;

import sensors.HumiditySensor;
import sensors.IntegratedSensorSuite;
import sensors.TemperatureSensor;
import sensors.WindDirectionSensor;
import sensors.WindSpeedSensor;

/**
 * Test class for all sensors
 *  @author Group 5
 */
public class SensorTest {
    final HumiditySensor humiditySensor = new HumiditySensor();
    final TemperatureSensor tempSensor = new TemperatureSensor();
    final WindSpeedSensor windSpeedSensor = new WindSpeedSensor();
    final WindDirectionSensor windDirectionSensor = new WindDirectionSensor();
    int humidityData;
    int tempData;
    int windSpeedData;
    double windDirectionData;

    /**
	 * Test getData() for humidity sensor
	 */
    @Test
    public void testHumidityGetData() {
    	humiditySensor.setHumidity(-10);
    	humiditySensor.setHumidity(110);
        humiditySensor.setHumidity(47);
        humidityData = humiditySensor.getData();
        //System.out.println(data);
        assertTrue("The humidity sensor's return value of " + humidityData + " was less than 1.",
                1 <= humidityData);
        assertTrue("The humidity sensor's return value of " + humidityData + " was greater than 100.",
                humidityData <= 100);

    }
    
    /**
	 * Test getData() for temperature sensor
	 */
    @Test
    public void testTemperatureGetData() {
    	tempSensor.setTemp(-60);
    	tempSensor.setTemp(160);
        tempSensor.setTemp(60);
        tempData = tempSensor.getData();
        //System.out.println(data);
        assertTrue("The temperature sensor's return value of " + windSpeedData + " was less than -40.",
                tempData >= -40);
        assertTrue("The temperature sensor's return value of " + windSpeedData + " was greater than 150.",
                tempData <= 150);
    }
    
    /**
	 * Test getData() for wind speed sensor
	 */
    @Test
    public void testWindSpeedGetData() {
        windSpeedData = windSpeedSensor.getData();
        //System.out.println("Testing wind speed: " + windSpeedData);
        assertTrue("The wind speed sensor's return value of " + windSpeedData + " was less than 0.",
                windSpeedData > 0);
    }
    
    /**
	 * Test getData() for wind direction sensor
	 */
    @Test
    public void testWindDirectionGetData() {
        windDirectionData = windDirectionSensor.getData( );
        //System.out.println(data);
        assertTrue("The wind direction sensor's return value of " + windDirectionData + " was less than 0.",
                windDirectionData >= 0);
        assertTrue("The temperature sensor's return value of " + windDirectionData + " was greater than 360.",
                windDirectionData <= 360);
    }
    
    /**
	 * Test wind direction
	 */
    @Test
    public void testWindDirectionSensor() {

        String s = windDirectionSensor.getDirection();
        assertTrue(s.equals("North") || s.equals("South") || s.equals("East") || s.equals("West")
                || s.equals("NorthEast") || s.equals("SouthEast") || s.equals("SouthWest") 
                || s.equals("NorthWest"));
    	
    	int num = windDirectionSensor.getData();
    	assertTrue(num >= 0 && num < 361);
    	
    	// test determineDirection()
    	assertTrue(windDirectionSensor.determineDirection(0.0).equals("North"));
    	assertTrue(windDirectionSensor.determineDirection(360).equals("North"));
    	assertTrue(windDirectionSensor.determineDirection(40).equals("NorthEast"));
    	assertTrue(windDirectionSensor.determineDirection(80).equals("East"));
    	assertTrue(windDirectionSensor.determineDirection(120).equals("SouthEast"));
    	assertTrue(windDirectionSensor.determineDirection(160).equals("South"));
    	assertTrue(windDirectionSensor.determineDirection(210).equals("SouthWest"));
    	assertTrue(windDirectionSensor.determineDirection(250).equals("West"));
    	assertTrue(windDirectionSensor.determineDirection(300).equals("NorthWest"));
    }
    
    /**
	 * Test sensor type
	 */
    @Test
    public void testSensorType() {
    	assertTrue(humiditySensor.sensorType().equals("HumiditySensor"));
    	assertTrue(tempSensor.sensorType().equals("TemperatureSensor"));
    	assertTrue(windSpeedSensor.sensorType().equals("WindSpeedSensor"));
    	assertTrue(windDirectionSensor.sensorType().equals("WindDirectionSensor"));
    }
    /**
	 * Test the output
	 */
    @Test
    public void testCall() throws Exception {
    	// test case for humidity sensor call()
    	String s = humiditySensor.call();
    	s = (s.substring(0, s.length() - 1));
    	String sensorInfo = s.substring(0, 10);
    	int data = Integer.parseInt(s.substring(10));
    	
    	assertTrue(sensorInfo.equals("Humidity: "));
    	assertTrue(data >= 0 && data <= 100);
    	
    	// test case for temp sensor call()
    	s = tempSensor.call();
    	sensorInfo = s.substring(0, 16);
    	data = Integer.parseInt(s.substring(16));
    	assertTrue(sensorInfo.equals("Temperature(F): "));
    	assertTrue(data >= -40 && data <= 120);
    	
    	// test case for wind speed sensor call()
    	s = windSpeedSensor.call();
    	sensorInfo = s.substring(0, 18);
    	data = Integer.parseInt(s.substring(18));
    	assertTrue(sensorInfo.equals("Wind Speed(MPH) : "));
    	assertTrue(data >= 0 && data <= 200);
    	
    	// test case for wind dir sensor call()
    	s = windDirectionSensor.call();
    	sensorInfo = s.substring(0, 26);
    	String direction = s.substring(26);

    	assertTrue(sensorInfo.equals("Wind Direction(Cardinal): "));
    	assertTrue(direction.equals("North") || direction.equals("South") 
    			|| direction.equals("East") || direction.equals("West") 
    			|| direction.equals("NorthEast") || direction.equals("SouthEast") 
    			|| direction.equals("SouthWest") || direction.equals("NorthWest"));
    }
    /**
	 * Test the output
	 */
    @Test
    public void testRun() {
    	IntegratedSensorSuite iss = new IntegratedSensorSuite();
    	List<String> list = iss.run();

    	int tempData = Integer.parseInt(list.get(1).substring(16));
    	int windSpeedData = Integer.parseInt(list.get(2).substring(18));
    	String windDirection = list.get(3).substring(26);
    	int humidityData = Integer.parseInt(list.get(4).substring(0, list.get(4).length() - 1)
    										.substring(10));
    	
    	assertTrue(list.get(0).equals("----Console Receiver Output----"));
    	assertTrue(list.get(1).substring(0, 16).equals("Temperature(F): "));
    	assertTrue(tempData >= -40 && tempData <= 120);
    	
    	assertTrue(list.get(2).substring(0, 18).equals("Wind Speed(MPH) : "));
    	assertTrue(windSpeedData >= 0 && windSpeedData <= 200);
    	
    	assertTrue(list.get(3).substring(0, 26).equals("Wind Direction(Cardinal): "));
       	assertTrue(windDirection .equals("North") || windDirection .equals("South") 
    			|| windDirection .equals("East") || windDirection .equals("West") 
    			|| windDirection .equals("NorthEast") || windDirection.equals("SouthEast") 
    			|| windDirection .equals("SouthWest") || windDirection .equals("NorthWest"));
       	
    	assertTrue(list.get(4).substring(0, 10).equals("Humidity: "));
    	assertTrue(humidityData >= 0 && humidityData <= 100);
    }

}
