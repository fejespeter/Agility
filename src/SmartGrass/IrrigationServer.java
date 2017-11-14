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
    
	private List<Sensor> sensors = new ArrayList<>();
    private Map<SensorData,Integer> sensorDatas=new HashMap<>();
    public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    void receiveData(int id, SensorData sensorData) {
        logger.info("Sensor " + id + ": "+sensorData.toString());
        sensorDatas.put(sensorData, id);
        calculateNeedsToSensor(id, sensorData);
    }
    
    public Map<SensorData, Integer> getSensorDataCollection(){
        return sensorDatas;
    }
    
    public void calculateNeedsToSensor(int id, SensorData data) {
    	isVitaminNeeded(id, data.getVitaminState());
    	isMoistureNeeded(id, data.getMoistureState());
    }
    
    public boolean isVitaminNeeded(int id, VitaminState state) {
    	switch(state) {
    	case LacksInFosfor:
    		logger.info("Sensor " + id + " needs fosfor");
    		return true;
    	case LacksInKalium:
    		logger.info("Sensor " + id + " needs kalium");
    		return true;
    	case LacksInMagnesiumOxid:
    		logger.info("Sensor " + id + " needs magnesium-oxid");
    		return true;
    	case LacksInNitrogen:
    		logger.info("Sensor " + id + " needs nitrogen");
    		return true;
    	case OK:
    		return false;
    	default:
    		return false;
    	}
    }
    
    public boolean isMoistureNeeded(int id, MoistureState state) {
    	switch(state) {
    	case Dry:
    		logger.info("Sensor " + id + " needs water");
    		return true;
    	case OK:
    		return false;
    	case SlightlyDry:
    		logger.info("Sensor " + id + " needs small water");
    		return true;
    	case TooWet:
    		logger.info("Sensor " + id + " is to wet");
    		return false;
    	case VeryDry: 
    		logger.info("Sensor " + id + " needs a lot of water");
    		return true;
    	default:
    			return false;
    	}
    }
    
    public Sensor getSensorById(int id) {
    	for(Sensor s : sensors) {
    		if(s.getId() == id)
    			return s;
    	}
    	logger.warning("Sensor with id: " + id + " not found.");
    	return null;
    }
    
    public void addSensor(Sensor s) {
    	if(sensors == null) 
    		sensors = new ArrayList<>();
    	sensors.add(s);
    		
    }
    
    public void removeSensor(Sensor s) {
    	if(sensors != null && sensors.contains(s))
    		sensors.remove(s);
    }
    
}
