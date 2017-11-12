/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartGrass;

import static SmartGrass.SmartGrass.sensorCount;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gulya
 */
public class IrrigationServerTest {
    private IrrigationServer server = new IrrigationServer();
    
    public IrrigationServerTest() {
        for(int i = 0; i< sensorCount; i++) {
    		Sensor sensor = new Sensor(i, server);
    		sensor.start();
    	}
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void storeData() {
        int dataNum=server.getSensorDataCollection().size();
        assertTrue(dataNum!=0);
    }
    
    @Test
    public void storeDataInStructuralSystem() {
        boolean structured=false;
        if(server.getSensorDataCollection() instanceof Collection || server.getSensorDataCollection() instanceof Map)
            structured=true;
        assertTrue(structured);

    }
    
}
