package edu.bu.met.cs665.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
/**
 * Summary.
 * 
 * @author tim_abiok
 * @course CS-665
 * @term Summer 2
 * @assignment PROJECT
 * @date 20 AUG 2020
 */
class MapConfigurationTest {

  @Test
  void testSetupMap() {
    //MapConfiguration  mapConfiguration = new MapConfiguration();
      MapConfiguration.setupMap();    
    assertEquals(null, MapConfiguration.getArcMap());
    
  }

}
