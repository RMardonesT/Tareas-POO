package src;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class settingsControl {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Spinner<Integer> Susceptibles;
    @FXML
    private Spinner<Integer> Infectados;
    @FXML
    private TextField P0;
    @FXML
    private TextField I_time;
    @FXML
    private Button Restart;
    @FXML
    private Slider Speed;
    @FXML
    private Slider DeltaTheta;
    @FXML
    private TextField Delta_t;

    private Simulator simulator;
    private SimulatorMenuBar simMenuBar;
    private Menu settings;

    void Simulador (Simulator simulator, SimulatorMenuBar simMenuBar, Menu menu){
        this.simulator = simulator;
        this.simMenuBar = simMenuBar;
        this.settings = menu;
    }

    @FXML
    void RestartSimulation(ActionEvent event) {
        double P0value = SimulatorConfig.P0;
        double ITimeValue = SimulatorConfig.I_TIME;

        //UPDATE I_Time
        try {
            ITimeValue  = Double.valueOf(I_time.getText())*10;
        }
        catch (Exception e) {System.out.println("Por favor ingrese solo números");}

        SimulatorConfig.I_TIME = ITimeValue;

        //UPDATE P0
        try {
            P0value  = Double.valueOf(P0.getText());
        }
        catch (Exception e) {System.out.println("Por favor ingrese solo números");}

        SimulatorConfig.P0 = P0value;

        //UPDATE SUSCEPTIBLES E INFECTADOS
        int Svalue = (int)Susceptibles.getValue();
        int Ivalue = (int)Infectados.getValue();

        SimulatorConfig.I = Double.valueOf(Ivalue);
        SimulatorConfig.N = Svalue + Ivalue;

        //UPDATE Delta t
        double Deltatvalue = SimulatorConfig.DELTA_T;
        try {
            Deltatvalue  = Double.valueOf(Delta_t.getText());
        }
        catch (Exception e) {System.out.println("Por favor ingrese solo números");}
        SimulatorConfig.DELTA_T = Deltatvalue;

        //UPDATE SLIDERS
        SimulatorConfig.SPEED = Double.valueOf(Speed.getValue());
        SimulatorConfig.DELTA_THETA = Double.valueOf(DeltaTheta.getValue());

        //Obtener stage, scene y cerrarlo
        Node boton = (Node) event.getSource();
        Stage stage = (Stage) boton.getScene().getWindow();
        stage.close(); //Cierra la ventana de las settings
        //this.simulator.start();

        MediaPlayer mediaPlayer = new MediaPlayer(simMenuBar.media);
        mediaPlayer.play();
        boolean playing = true;

        mediaPlayer.setOnEndOfMedia(() -> this.simMenuBar.Control(true, this.simulator, this.settings));

        //this.simMenuBar.Control(true,this.simulator, this.settings); //fuerza el uso de start
    }

    @FXML
    void initialize() {
        assert Susceptibles != null : "fx:id=\"Susceptibles\" was not injected: check your FXML file 'settings.fxml'.";
        assert Infectados != null : "fx:id=\"Infectados\" was not injected: check your FXML file 'settings.fxml'.";
        assert P0 != null : "fx:id=\"P0\" was not injected: check your FXML file 'settings.fxml'.";
        assert I_time != null : "fx:id=\"I_time\" was not injected: check your FXML file 'settings.fxml'.";
        assert Restart != null : "fx:id=\"Restart\" was not injected: check your FXML file 'settings.fxml'.";
        assert Speed != null : "fx:id=\"Speed\" was not injected: check your FXML file 'settings.fxml'.";
        assert DeltaTheta != null : "fx:id=\"DeltaTheta\" was not injected: check your FXML file 'settings.fxml'.";
        assert Delta_t != null : "fx:id=\"Delta_t\" was not injected: check your FXML file 'settings.fxml'.";

        // Susceptibles
        SpinnerValueFactory<Integer> NvalueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 500, (int)(SimulatorConfig.N-SimulatorConfig.I));

        Susceptibles.setValueFactory(NvalueFactory);

        //Infectados
        SpinnerValueFactory<Integer> IvalueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 500, (int)SimulatorConfig.I);

        Infectados.setValueFactory(IvalueFactory);

        //P0 = new TextField(String.valueOf(SimulatorConfig.P0));
        P0.setText(String.valueOf(SimulatorConfig.P0));

        I_time.setText(String.valueOf(SimulatorConfig.I_TIME/10));

        //Speed = new Slider(0,5,0.1);
        //DeltaTheta = new Slider(0, 1, 0.5);
        Speed.setValue(SimulatorConfig.SPEED);
        DeltaTheta.setValue(SimulatorConfig.DELTA_THETA);
        Delta_t.setText(String.valueOf(SimulatorConfig.DELTA_T));

    }
}

