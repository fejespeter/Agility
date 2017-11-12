/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartGrass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


/**
 *
 * @author gulya
 */
class IrrigationServer {
    
    private Map<SensorData,Integer> sensorDatas=new HashMap<>();
    public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    void receiveData(int id, SensorData sensorData) {
        logger.info("Sensor " + id + ": "+sensorData.toString());
        sensorDatas.put(sensorData, id);
    }
    
    public Map getSensorDataCollection(){

        return sensorDatas;
    }
    
    
    
}
