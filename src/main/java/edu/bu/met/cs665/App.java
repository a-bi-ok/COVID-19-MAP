
package edu.bu.met.cs665;

import com.esri.arcgisruntime.mapping.view.MapView;
import edu.bu.met.cs665.domain.MapConfiguration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Summary.
 * 
 * @author tim_abiok
 * @course CS-665
 * @term Summer 2
 * @assignment PROJECT
 * @date 20 AUG 2020
 */


@SpringBootApplication(scanBasePackages = {"edu.bu.met.cs665"})
public class App extends Application {


  private static final int STAGE_HEIGHT = 700;
  private static final int STAGE_WIDTH = 800;
  private static final String MY_MAP_APP = "My Map App";

  /**
   * Main.
   *
   */

  public static void main(String[] args) {
    // Application run ordered Sequence
    SpringApplication springApplication = new SpringApplication(App.class);
    springApplication.setLogStartupInfo(false);
    springApplication.run(args);
    Application.launch(args);
  }

  /**
   * On Start for Application Object.
   *
   */
  @Override
  public void start(Stage stage) {
    // set the title and size of the stage and show it
    stage.setTitle(MY_MAP_APP);
    stage.setWidth(STAGE_WIDTH);
    stage.setHeight(STAGE_HEIGHT);
    stage.show();

    // create a JavaFX scene with a stack pane as the root node and add it to the scene
    StackPane stackPane = new StackPane();
    Scene scene = new Scene(stackPane);
    stage.setScene(scene);
    //
    // create a MapView to display the map and add it to the stack pane
    MapConfiguration.mapView = new MapView();
    stackPane.getChildren().add(MapConfiguration.mapView);
    MapConfiguration.setupMap();
  }

  /**
   * Stops and releases all resources used in application.
   */
  @Override
  public void stop() {
    if (MapConfiguration.mapView != null) {
      MapConfiguration.mapView.dispose();
    }
  }



}
