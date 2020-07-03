# ISS
Version 1:
We created a Sensor interface with a basic outline of what each sensor would ostensibly needed, mostly just a getData method and a Random object to create random data. No multithreading has been created at this point, and the rest of the team is working on test cases.

Version 2:
We abandoned the Sensor interface to inherit Callable, and got multithreading working by using an IntegratedSensorSuite class that joined together all of the sensors and controlled the multithreading with an Executor. Team member also updated the sensor data randomization to prevent unrealistic jumps from 2 degrees F all the way to 100 degrees in 2 seconds. Now the data coming out of the sensors is much more realistic. The sensor classes now have a method that allows them to set the base temp for the randomized data, and then random additions/subtractions are made from that number.

Version 3:
After conversing with team members, we decided it would be best to keep the sensor interface so we switched everything around to have the Sensor interface extend Callable<String>. 
I also added comments, cleaned up the driver class, changed it so that the IntegratedSensorSuite class creates all of the sensors because that seems more realistic to me, and also changed call() to return a list of readings for ease of testing.
