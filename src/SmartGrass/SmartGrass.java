/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartGrass;

/**
 *
 * @author gulya
 */
public class SmartGrass {

	public static final int sensorCount = 10;
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	IrrigationServer server = new IrrigationServer();
    	
    	for(int i = 0; i< sensorCount; i++) {
    		Sensor sensor = new Sensor(i, server);
    		sensor.start();
    	}
    	
    }
    
}
