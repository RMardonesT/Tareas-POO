package src;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Simulator {
    private Timeline animation;
    private Comuna comuna;
    private Scene scene;
    private double simulationSamplingTime;
    private double simulationTime;  // it goes along with real time, faster or slower than real time
    private double delta_t;   // precision of discrete simulation time

    /**
     * @param framePerSecond frequency of new views on screen
     * @param simulationTime2realTimeRate how faster the simulation runs relative to real time
     */
    public Simulator (double framePerSecond, double simulationTime2realTimeRate, Comuna comuna, Scene scene){
        this.scene = scene;
        this.comuna = comuna;
        double viewRefreshPeriod =  1 /  framePerSecond; // in [ms] real time used to display
        // a new view on application
        simulationSamplingTime = viewRefreshPeriod*simulationTime2realTimeRate/1000;

        delta_t = SimulatorConfig.DELTA_T;
        simulationTime = 0;
        animation = new Timeline(new KeyFrame(Duration.millis(viewRefreshPeriod*1000), e->takeAction()));
        animation.setCycleCount(Timeline.INDEFINITE);
    }
    public void takeAction() {
        this.scene.setOnKeyPressed(event -> keyHandle(event));
        comuna.getState();

        double nextStop=simulationTime+simulationSamplingTime;
        for( ; simulationTime<nextStop; simulationTime+=delta_t) {
            comuna.Interaction();
            comuna.computeNextState(delta_t); // compute its next state based on current global state
            comuna.updateState();            // update its state
        }
        comuna.getState();
        //ArrayList<Pedestrian> people = comuna.getPedestrians();
        for (int i=0; i < comuna.getPedestrians().size(); i++)
            comuna.getPedestrians().get(i).updateView();
        comuna.updateView();
    }
    public void start(){
        comuna.getStateDescription();
        comuna.ResetPedestrian();  //
        simulationTime = 0;
        animation.play();

        //comuna.getView().setOnKeyPressed( e->keyHandle(e));
    }

    private void keyHandle (KeyEvent e) {
        if (e.getCode() == KeyCode.LEFT) {
            this.slowdown();
            System.out.println("presion tecla izquierda");
        } else if (e.getCode() == KeyCode.RIGHT) {
            this.speedup();
            System.out.println("presion tecla derecha");
        }
    }

    public void stop(){
        animation.stop();
        //comuna.getView().getChildren().remove(1, (int)SimulatorConfig.N+1);
    }

    public void speedup(){
        simulationSamplingTime = simulationSamplingTime*2;
    }

    public void slowdown(){
        simulationSamplingTime = simulationSamplingTime/2;
    }
}
