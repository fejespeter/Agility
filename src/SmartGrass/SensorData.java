package SmartGrass;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class SensorData{
    private VitaminState vitaminState;
    private MoistureState moistureState;
    
    public SensorData(){
        
    }
    public SensorData(VitaminState vs, MoistureState ms){
        vitaminState=vs;
        moistureState=ms;
    }
    
    public void setVitaminState(VitaminState vs){
        vitaminState=vs;
    }
    public void setMoistureState(MoistureState ms){
        moistureState=ms;
    }
    
    public VitaminState getVitaminState(){
        return vitaminState;
    }
    public MoistureState getMoistureState(){
        return moistureState;
    }
	@Override
	public String toString() {
		return "SensorData [vitaminState=" + vitaminState + ", moistureState=" + moistureState + "]";
	}
    
    

}
