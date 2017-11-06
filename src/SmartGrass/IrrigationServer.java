/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartGrass;

import java.util.logging.Logger;


/**
 *
 * @author gulya
 */
class IrrigationServer {

	public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	void receiveData(int id, SensorData sensorData) {
		
        logger.info("Sensor " + id + ": "+sensorData.toString());
    }
    
}
