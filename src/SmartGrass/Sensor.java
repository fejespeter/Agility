/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartGrass;

import java.util.Random;

public class Sensor extends Thread {

	private int id;
	private SensorData sensorData;
	private IrrigationServer server;

	public Sensor(int id, IrrigationServer server) {
		this.id = id;
		this.server = server;
	}
	
	public long getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void sendSensorDataToServer() {
		server.receiveData(id, sensorData);
	}

	public void readSensor() {
		if (sensorData != null) {
			sensorData.setMoistureState(generateRandomMoistureState());
			sensorData.setVitaminState(generateRandomVitaminState());
		}
		else {
			sensorData = new SensorData(generateRandomVitaminState(), generateRandomMoistureState());
		}
	}
	
	public void readSensor(VitaminState vitamine, MoistureState moisture) {
		if (sensorData != null) {
			sensorData.setMoistureState(moisture);
			sensorData.setVitaminState(vitamine);
		}
		else {
			sensorData = new SensorData(vitamine, moisture);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10*1000);
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
		switch (i) {
		case 0:
			return MoistureState.Dry;
		case 1:
			return MoistureState.OK;
		case 2:
			return MoistureState.SlightlyDry;
		case 3:
			return MoistureState.TooWet;
		case 4:
			return MoistureState.VeryDry;
		}
		return null;
	}

	private VitaminState generateRandomVitaminState() {
		Random rand = new Random();
		int i = rand.nextInt(5);
		switch (i) {
		case 0:
			return VitaminState.LacksInFosfor;
		case 1:
			return VitaminState.LacksInKalium;
		case 2:
			return VitaminState.LacksInMagnesiumOxid;
		case 3:
			return VitaminState.LacksInNitrogen;
		case 4:
			return VitaminState.OK;
		}
		return null;
	}

    void receiveWater() {
        
    }

    void receiveVitamin() {
        
    }
}
