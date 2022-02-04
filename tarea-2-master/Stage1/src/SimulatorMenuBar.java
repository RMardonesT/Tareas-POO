package src;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;

public class SimulatorMenuBar extends MenuBar {

    public SimulatorMenuBar (Simulator simulator){
        Menu controlMenu = new Menu("Control");
        getMenus().add(controlMenu);
        MenuItem start = new MenuItem("Start");
        MenuItem stop = new MenuItem("Stop");
        controlMenu.getItems().addAll(start,stop);
        stop.setDisable(true);
        start.setOnAction(e -> Control(true,simulator));
        stop.setOnAction(e -> Control(false,simulator));

    }
    /* EJECUTA OPCION START O STOP Y BLOQUEA EL QUE FUE SELECCIONADO
     * @param Boolean option : indica que item se selecciono, true: Start false: Stop
     * @param Simulator simulator : simulador que utiliza los items
     */
    private void Control(Boolean option,Simulator simulator) {
        if(option) {
            simulator.start();
            this.getMenus().get(0).getItems().get(0).setDisable(true); //
            this.getMenus().get(0).getItems().get(1).setDisable(false);
        }
        else {
            simulator.stop();
            this.getMenus().get(0).getItems().get(1).setDisable(true);
            this.getMenus().get(0).getItems().get(0).setDisable(false);
        }

    }
}

