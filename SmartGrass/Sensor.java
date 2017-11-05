/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartgrass;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Sensor extends Thread{
    private SensorData sensorData;
    private IrrigationServer server;
    
    public Sensor(IrrigationServer server){
        this.server=server;
    }
    
    private void sendSensorDataToServer(){
        server.receiveData(sensorData);
    }
    
    private void readSensor(){
        sensorData.setMoistureState(generateRandomMoistureState());
        sensorData.setVitaminState(generateRandomVitaminState());
    }
    
    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep(1000*60);
                readSensor();
                sendSensorDataToServer();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private MoistureState generateRandomMoistureState() {
        Random rand = new Random();
        int i = rand.nextInt(5);
        switch(i){
            case 0: return MoistureState.Dry;
            case 1: return MoistureState.OK;
            case 2: return MoistureState.SlightlyDry;
            case 3: return MoistureState.TooWet;
            case 4: return MoistureState.VeryDry;
        }
        return null;
    }

    private VitaminState generateRandomVitaminState() {
        Random rand = new Random();
        int i = rand.nextInt(5);
        switch(i){
            case 0: return VitaminState.LacksInFosfor;
            case 1: return VitaminState.LacksInKalium;
            case 2: return VitaminState.LacksInMagnesiumOxid;
            case 3: return VitaminState.LacksInNitrogen;
            case 4: return VitaminState.OK;
        }
        return null;
    }
}
