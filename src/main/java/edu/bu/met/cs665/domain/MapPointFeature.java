package edu.bu.met.cs665.domain;



import com.esri.arcgisruntime.data.Feature;
import com.esri.arcgisruntime.data.FeatureCollection;
import com.esri.arcgisruntime.data.FeatureCollectionTable;
import com.esri.arcgisruntime.data.Field;
import com.esri.arcgisruntime.geometry.GeometryType;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.symbology.ClassBreaksRenderer;
import com.esri.arcgisruntime.symbology.ClassBreaksRenderer.ClassBreak;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Summary.
 * 
 * @author tim_abiok
 * @course CS-665
 * @term Summer 2
 * @assignment PROJECT
 * @date 20 AUG 2020
 */

public class MapPointFeature extends Table implements Callable<Feature> {

  private static final int _87 = 87;
  private static final int _54 = 54;
  private static final int _33 = 33;
  private static final int _21 = 21;
  private static final int _13 = 13;
  private static final int _8 = 8;
  private static final int _5 = 5;
  private static final int _3 = 3;
  private static final int _2 = 2;
  private static final int _1 = 1;
  private static final double _150000000_0 = 150000000.0;
  private static final double _3125000_0 = 3125000.0;
  private static final double _625000_0 = 625000.0;
  private static final double _125000_0 = 125000.0;
  private static final double _50000_0 = 50000.0;
  private static final double _25000_0 = 25000.0;
  private static final double _2000_0 = 2000.0;
  private static final double _0_0 = 0.0;
  private static final String NUMBER_OF_CASES = "Number of Cases";
  private static final String CASES = "Cases";
  private static final String NUMBER_OF_DEATHS = "Number of Deaths";
  private static final String DEATHS = "Deaths";
  private static final int STRING_FIELD_LENGTH = 50;
  private static final String PLACE_NAME = "Place Name";
  private static final String COUNTRY = "Country";
  private static Logger logger = Logger.getLogger(MapPointFeature.class);
  private static final int HEXRED = 0x88FF0000;
  private static final int HEXPURPLE = 0xFFe9724c;
  private Map<String, Object> map;
  private JsonObject jsonObject;
  private FeatureCollection featureCollection;
  private FeatureCollectionTable pointsTable;
  private List<Field> pointFields;

  /**
   * Constructor for MapFeature Class.
   */

  public MapPointFeature(FeatureCollection featureCollection) {
    this.featureCollection = featureCollection;
    // TODO Auto-generated constructor stub
  }

  /**
   * Creates FeatureColection given a JSON object parameter as String.
   */

  @Override
  public void createFeatureCollection(String json) {
    logger.setLevel(Level.DEBUG);
    List<Feature> features = new ArrayList<>();
    int numOfprocessors = Runtime.getRuntime().availableProcessors();
    JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
    ExecutorService executor = Executors.newFixedThreadPool(numOfprocessors - 1);
    this.pointFields = createPointFields();
    this.pointsTable = createPointsTable(this.pointFields);
    for (JsonElement row : jsonArray) {
      this.jsonObject = row.getAsJsonObject();
      setJsonObject(this.jsonObject);
      Future<Feature> future = executor.submit(this);
      try {
        features.add(future.get());
        // logger.debug(future.get().getAttributes());
        logger.info(future.get().getAttributes());

      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }

    }
    executor.shutdown();
    while (!executor.isTerminated()) {
      pointsTable.addFeaturesAsync(features);
      Thread.yield();
    }
  }

  /**
   * Getter for the jsonObject Field.
   */
  public JsonObject getJsonObject() {
    return jsonObject;
  }



  /**
   * Creates Map field as list.
   */

  public List<Field> createPointFields() {
    List<Field> pointFields = new ArrayList<>();
    pointFields.add(Field.createString(COUNTRY, PLACE_NAME, STRING_FIELD_LENGTH));
    pointFields.add(Field.createInteger(DEATHS, NUMBER_OF_DEATHS));
    pointFields.add(Field.createInteger(CASES, NUMBER_OF_CASES));
    return pointFields;
  }

  /**
   * Creates a feature collection Table using pointFields as parameter.
   */

  public FeatureCollectionTable createPointsTable(List<Field> pointFields) {
    FeatureCollectionTable pointsTable =
        new FeatureCollectionTable(pointFields, GeometryType.POINT, SpatialReferences.getWgs84());
    pointsTable.setRenderer(createSymbology(CASES));

    return pointsTable;
  }

  /**
   * Setter for the jsonObject Field.
   */
  public void setJsonObject(JsonObject jsonObject) {
    this.jsonObject = jsonObject;
  }



  /**
   * Creates Symbology (data breaks points) for a selected field parameter.
   */
  public ClassBreaksRenderer createSymbology(String field) {

    final SimpleMarkerSymbol graduatedSymbolA =
        new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, HEXRED, _1);
    final SimpleMarkerSymbol graduatedSymbolB =
        new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, HEXRED, _2);

    final SimpleMarkerSymbol graduatedSymbolC =
        new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, HEXRED, _3);

    final SimpleMarkerSymbol graduatedSymbolD =
        new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, HEXRED, _5);

    final SimpleMarkerSymbol graduatedSymbolE =
        new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, HEXRED, _8);

    final SimpleMarkerSymbol graduatedSymbolF =
        new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, HEXRED, _13);
    final SimpleMarkerSymbol graduatedSymbolG =
        new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, HEXRED, _21);
    final SimpleMarkerSymbol graduatedSymbolH =
        new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, HEXRED, _33);
    final SimpleMarkerSymbol graduatedSymbolI =
        new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, HEXRED, _54);
    final SimpleMarkerSymbol graduatedSymbolJ =
        new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, HEXRED, _87);
    //
    SimpleLineSymbol outline = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, HEXPURPLE, 1.0f);

    graduatedSymbolA.setOutline(outline);
    graduatedSymbolB.setOutline(outline);
    graduatedSymbolC.setOutline(outline);
    graduatedSymbolD.setOutline(outline);
    graduatedSymbolE.setOutline(outline);
    graduatedSymbolF.setOutline(outline);
    graduatedSymbolG.setOutline(outline);
    graduatedSymbolH.setOutline(outline);
    graduatedSymbolI.setOutline(outline);
    graduatedSymbolJ.setOutline(outline);

    /* ** ADD ** */
    ClassBreak breakOne = new ClassBreak(null, null, _0_0, _2000_0, graduatedSymbolC);
    ClassBreak breakTwo = new ClassBreak(null, null, _2000_0, _25000_0, graduatedSymbolD);
    ClassBreak breakThree = new ClassBreak(null, null, _25000_0, _50000_0, graduatedSymbolE);
    ClassBreak breakFour = new ClassBreak(null, null, _50000_0, _125000_0, graduatedSymbolF);
    ClassBreak breakFive = new ClassBreak(null, null, _125000_0, _625000_0, graduatedSymbolG);
    ClassBreak breakSix = new ClassBreak(null, null, _625000_0, _3125000_0, graduatedSymbolI);
    ClassBreak breakSeven = new ClassBreak(null, null, _3125000_0, _150000000_0, graduatedSymbolJ);

    List<ClassBreak> acreageBreaks = new ArrayList<>();
    acreageBreaks.add(breakOne);
    acreageBreaks.add(breakTwo);
    acreageBreaks.add(breakThree);
    acreageBreaks.add(breakFour);
    acreageBreaks.add(breakFive);
    acreageBreaks.add(breakSix);
    acreageBreaks.add(breakSeven);

    // Create the ClassBreakRenderer on the "GIS_ACRES" field with the list of breaks. Assign this
    // renderer to the feature layer.
    /* ** ADD ** */
    ClassBreaksRenderer openSpacesClassBreaksRenderer =
        new ClassBreaksRenderer(field, acreageBreaks);
    return openSpacesClassBreaksRenderer;
  }

  /**
   * Callable method implementation.
   */

  @Override
  public Feature call() throws Exception {
    pointFields = createPointFields();
    pointsTable = createPointsTable(pointFields);

    // SimpleRenderer renderer = new SimpleRenderer();
    // pointsTable.setRenderer(renderer);
    featureCollection.getTables().add(pointsTable);

    jsonObject = getJsonObject();
    setCountry(jsonObject.get("country").getAsString());
    setLat(jsonObject.get("countryInfo").getAsJsonObject().get("lat").getAsLong());
    setLon(jsonObject.get("countryInfo").getAsJsonObject().get("long").getAsLong());
    setDeaths(jsonObject.get("deaths").getAsInt());
    setCases(jsonObject.get("cases").getAsInt());

    final Point point = new Point(getLon(), getLat(), SpatialReferences.getWgs84());
    // features.add(pointsTable.createFeature(map, point));

    map = new HashMap<>();
    map.put(pointFields.get(0).getName(), getCountry());
    map.put(pointFields.get(1).getName(), getDeaths());
    map.put(pointFields.get(2).getName(), getCases());
    Feature feature = pointsTable.createFeature(map, point);
    return feature;

  }

}
