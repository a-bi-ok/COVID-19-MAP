package edu.bu.met.cs665.domain;

import com.esri.arcgisruntime.data.FeatureCollection;
import com.esri.arcgisruntime.layers.FeatureCollectionLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;

/**
 * Summary.
 * 
 * @author tim_abiok
 * @course CS-665
 * @term Summer 2
 * @assignment PROJECT
 * @date 20 AUG 2020
 */


public class MapConfiguration {

  private static final int LEVEL_OF_DETAIL = 6;
  private static final double REF_LONGITUDE = 12.8333;
  private static final double REF_LATITUDE = 42.8333;
  public static MapView mapView;
  private static ArcGISMap arcMap;
  
 
  /**
   * Facade Method for the Application.
   */
  public static void setupMap() {
    if (mapView != null) {
      Basemap.Type basemapType = Basemap.Type.DARK_GRAY_CANVAS_VECTOR;

      double latitude = REF_LATITUDE;
      double longitude = REF_LONGITUDE;
      int levelOfDetail = LEVEL_OF_DETAIL;
      arcMap = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);

      FeatureCollection featureCollection = new FeatureCollection();
      FeatureCollectionLayer featureCollectionLayer = new FeatureCollectionLayer(featureCollection);
      arcMap.getOperationalLayers().add(featureCollectionLayer);

      String data = GlobalCovidRestData.result.getBody();

      MapPointFeature mapPointFeature = new MapPointFeature(featureCollection);
      mapPointFeature.createFeatureCollection(data);

      mapView.setMap(arcMap);
    }

  }
  
  /**
   * Getter for mapView.
   */
  public static MapView getMapView() {
    return mapView;
  }
  
  /**
   * Getter for arcMap.
   */

  public static ArcGISMap getArcMap() {
    return arcMap;
  }
  
  
  /**
   * Setter for mapView.
   */
  public static void setMapView(MapView mapView) {
    MapConfiguration.mapView = mapView;
  }

  /**
   * Setter for arcMap.
   */
  public static void setArcMap(ArcGISMap arcMap) {
    MapConfiguration.arcMap = arcMap;
  }


}
