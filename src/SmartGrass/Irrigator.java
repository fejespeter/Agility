/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartGrass;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gulya
 */
public class Irrigator {
    private List<SensorRow> sensorRowList=new ArrayList<>();
    public boolean commandWasReceivedFromServer=false;
    
    public void addSensorRow(SensorRow sr){
        sensorRowList.add(sr);
    }
    
    public void irrigateSensorRowWithVater(SensorRow sr){
        sr.receiveWater();
        commandWasReceivedFromServer=true;
    }
    public void irrigateSensorRowWithVitamin(SensorRow sr){
        sr.receiveVitamin();
        commandWasReceivedFromServer=true;
    }
  
    public void synchronizeSensorList(List<SensorRow> sensorRowList){
        this.sensorRowList=sensorRowList;
    }
}
