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
		Sensor testSensor = new Sensor(0, server);
		server.addSensor(testSensor);
		server.getSensorById(0).readSensor();
		server.getSensorById(0).sendSensorDataToServer();
		int dataNum = server.getSensorDataCollection().size();
		assertTrue(dataNum > 0);
	}

	@Test
	public void storeDataInStructuralSystem() {
		boolean structured = false;
		if (server.getSensorDataCollection() instanceof Collection || server.getSensorDataCollection() instanceof Map)
			structured = true;
		assertTrue(structured);
	}

	@Test
	public void isVitaminNeededTest() {
		assertTrue(server.isVitaminNeeded(0, VitaminState.LacksInFosfor));
		assertTrue(server.isVitaminNeeded(0, VitaminState.LacksInKalium));
		assertTrue(server.isVitaminNeeded(0, VitaminState.LacksInMagnesiumOxid));
		assertTrue(server.isVitaminNeeded(0, VitaminState.LacksInNitrogen));
		assertTrue(!server.isVitaminNeeded(0, VitaminState.OK));

	}

	@Test
	public void isMoistureNeededTest() {
		assertTrue(server.isMoistureNeeded(0, MoistureState.Dry));
		assertTrue(!server.isMoistureNeeded(0, MoistureState.OK));
		assertTrue(server.isMoistureNeeded(0, MoistureState.SlightlyDry));
		assertTrue(!server.isMoistureNeeded(0, MoistureState.TooWet));
		assertTrue(server.isMoistureNeeded(0, MoistureState.VeryDry));
	
	}

}
