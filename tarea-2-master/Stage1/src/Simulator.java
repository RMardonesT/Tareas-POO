package src;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;

public class Simulator {
    private Timeline animation;
    private Comuna comuna;
    private double simulationSamplingTime;
    private double simulationTime;  // it goes along with real time, faster or slower than real time
    private double delta_t;   // precision of discrete simulation time

    /**
     * @param framePerSecond frequency of new views on screen
     * @param simulationTime2realTimeRate how faster the simulation runs relative to real time
     */
    public Simulator (double framePerSecond, double simulationTime2realTimeRate, Comuna comuna){
        this.comuna = comuna;
        double viewRefreshPeriod = 1 / framePerSecond; // in [ms] real time used to display
        // a new view on application
        simulationSamplingTime = viewRefreshPeriod *simulationTime2realTimeRate;
        delta_t = SimulatorConfig.DELTA_T;
        simulationTime = 0;
        animation = new Timeline(new KeyFrame(Duration.millis(viewRefreshPeriod*1000), e->takeAction()));
        animation.setCycleCount(Timeline.INDEFINITE);
    }
    public void takeAction() {
        double nextStop=simulationTime+simulationSamplingTime;
        for( ; simulationTime<nextStop; simulationTime+=delta_t) {
            comuna.computeNextState(delta_t); // compute its next state based on current global state
            comuna.updateState();            // update its state
        }
        comuna.getPedestrian().updateView();
        //???
    }

    /* INICIA LA SIMULACION
     * Remueve individuo anterior y crea uno nuevo, resetea la animacion
     */
    public void start(){
        animation.play();
        //volver a setear indv
        comuna.getView().getChildren().remove(1);
        comuna.ResetPedestrian();

        //comuna.getView().setOnKeyPressed( e->keyHandle(e));
    }

    private void keyHandle (KeyEvent e) {
	/// ????
    }
    /* DETIENE LA SIMULACION
     *
     */
    public void stop(){
        animation.stop();
    }

    public void speedup(){
       //????
    }

    public void slowdown(){
       // ???
    }
}
