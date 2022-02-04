package src;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.Node;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;


public class SimulatorMenuBar extends MenuBar {

//   //OBJETOS DE LA VENTANA SETTINGS
//   @FXML
//   private ResourceBundle resources;
//
//   @FXML
//   private URL location;
//
//   @FXML
//   private Spinner<Integer> Susceptibles;
//
//   @FXML
//   private Spinner<Integer> Infectados;
//
//   @FXML
//   private TextField P0;
//
//   @FXML
//   private TextField I_time;
//
//   @FXML
//   private Button Restart;
//
    protected Simulator simulator;
    static final String MEDIA_URL = Comuna.class.getResource("virus_update.wav").toExternalForm();
    static Media media = new Media(MEDIA_URL);


    public SimulatorMenuBar(Simulator simulator, SimulatorConfig simulatorConfig, Stage stage) {

        SimulatorConfig config = simulatorConfig;
        this.simulator = simulator;
        //Control Menu
        Menu controlMenu = new Menu("Control");
        getMenus().add(controlMenu);
        MenuItem start = new MenuItem("Start");
        MenuItem stop = new MenuItem("Stop");
        controlMenu.getItems().addAll(start, stop);
        stop.setDisable(true);

        //Settings Menu
        Menu setMenu = new Menu("Settings");
        getMenus().add(setMenu);
        MenuItem modify = new MenuItem("Modify parameters");
        setMenu.getItems().add(modify);

        //Events Control Menu
        start.setOnAction(e -> Control(true, this.simulator, setMenu));
        stop.setOnAction(e -> Control(false, this.simulator, setMenu));

        //Event Settings menu
        modify.setOnAction(e -> {
            try {
                Settings(true, this.simulator, config, setMenu);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

    }

    public void Control(Boolean option, Simulator simulator, Menu settings) {
        if (option) {
            simulator.start();
            this.getMenus().get(0).getItems().get(0).setDisable(true); //
            this.getMenus().get(0).getItems().get(1).setDisable(false);
            settings.setDisable(true);

        } else {
            simulator.stop();
            this.getMenus().get(0).getItems().get(0).setDisable(false);
            this.getMenus().get(0).getItems().get(1).setDisable(true);
            settings.setDisable(false);

        }

    }

    private void Settings(Boolean option, Simulator simulator, SimulatorConfig simulatorC, Menu settings) throws IOException {
        if (option) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
            Parent root = (Parent) loader.load();
            settingsControl controller = loader.getController();

            Scene scene = new Scene(root);

            stage.setTitle("Modify Parameters");
            stage.setScene(scene);
            controller.Simulador(simulator, this, settings);
            stage.show();
        }
    }

}


