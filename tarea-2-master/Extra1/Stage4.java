import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import src.*;

public class Stage4 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parameters param = getParameters();
        List<String> rawParam = param.getRaw();
        if (rawParam.size() != 1 && rawParam.size() != 2) {
            System.out.println("Usage: java Stage4 <configurationFile.txt> [audiofile.*]");
            System.exit(-1);
        }
        boolean sonidoarg = false;
        String audiofile = "";
        if (rawParam.size()==2){
            sonidoarg = true;
            audiofile = rawParam.get(1);
        }
        primaryStage.setTitle("Pandemic Graphics Simulator");
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 600, 600);
        scene.getStylesheets().add("style.css"); //carga el estilo del grafico de areas apiladas (colores)
        primaryStage.setScene(scene);
        SimulatorConfig config = new SimulatorConfig(new Scanner(new File(rawParam.get(0))));
        //File f = new File("configurationFile.txt");
        //SimulatorConfig config = new SimulatorConfig(new Scanner(f));
        Comuna comuna = new Comuna(sonidoarg, audiofile);
        Simulator simulator = new Simulator(10,1,comuna, scene);
        borderPane.setTop(new SimulatorMenuBar(simulator, config, primaryStage));
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);
        borderPane.setCenter(splitPane);
        Pane pane = new Pane();
        pane.getChildren().add(comuna.getView());
        splitPane.getItems().addAll(pane, comuna.getGraph());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
