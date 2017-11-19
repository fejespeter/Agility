/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartGrass;

import java.util.ArrayList;
import java.util.List;


public class SensorRow {
    private List<Sensor> sensors=new ArrayList<>();
    public void addSensor(Sensor s){
        sensors.add(s);
    }
    public void receiveWater(){
        sensors.forEach(s->s.receiveWater());
    }
    public void receiveVitamin(){
        sensors.forEach(s->s.receiveVitamin());
    }

    public SensorRow() {
    }
    
}
