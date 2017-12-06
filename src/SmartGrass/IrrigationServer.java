/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartGrass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


/**
 *
 * @author gulya
 */
class IrrigationServer extends Thread{
    //now thread safe
    private List<Sensor> sensors = new ArrayList<>();
    
    protected static Integer REFRESH_INTERVAL = 100*1000;
    
    private Map<SensorData,Integer> sensorDatas=Collections.synchronizedMap(new HashMap<>());
    public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private List<SensorRow> sensorRowList=new ArrayList<>();
    public Irrigator irrigator=new Irrigator();
    
    @Override
	public void run() {
        while (true) {
        	try {
                    Thread.sleep(REFRESH_INTERVAL);
                    sendCommandsToIrrigator();
                    
        	} catch (InterruptedException ex) {
                    ex.printStackTrace();
        	}
        }
    }

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
    		logger.info("Sensor " + id + " is too wet");
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
    	if(sensors == null){ 
    		sensors = new ArrayList<>();
    	}    	
    	sensors.add(s);  	
        if(sensorRowList.size()==0){
                sensorRowList.add(new SensorRow());
                sensorRowList.add(new SensorRow());
                irrigator.synchronizeSensorList(sensorRowList);
        }
    	if(s.getId()%2==0){
            sensorRowList.get(0).addSensor(s);
        }
        else{
            sensorRowList.get(1).addSensor(s);
        }
    }
    
    public void removeSensor(Sensor s) {
    	if(sensors != null && sensors.contains(s))
    		sensors.remove(s);
    }

    public void sendCommandsToIrrigator() {
        //TODO:calculate minimal quantities of water and vitamins for each row
        //TODO:irrigate (irrigator.irrigate(SensorRow sr))
        irrigator.irrigateSensorRowWithVater(sensorRowList.get(0));
    }
    
}
