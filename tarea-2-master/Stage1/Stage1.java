import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import src.*;

public class Stage1 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parameters param = getParameters();
        List<String> rawParam = param.getRaw();
        if (rawParam.size() != 1) {
            System.out.println("Usage: java Stage1 <configurationFile.txt>");
            System.exit(-1);
        }
        primaryStage.setTitle("Pandemic Graphics Simulator");
        BorderPane borderPane = new BorderPane();
        primaryStage.setScene(new Scene(borderPane, 600, 400));
        SimulatorConfig config = new SimulatorConfig(new Scanner(new File(rawParam.get(0))));
        //File f = new File("configurationFile.txt");
        //SimulatorConfig config = new SimulatorConfig(new Scanner(f));

        Comuna comuna = new Comuna();
        Simulator simulator = new Simulator(10,1,comuna);
        //simulator.takeAction();
        borderPane.setTop(new SimulatorMenuBar(simulator));
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
