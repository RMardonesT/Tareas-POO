package src;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class Comuna {
    private Pedestrian person;
    private Rectangle2D territory; // Alternatively: double width, length;
                                   // but more methods would be needed.
    private ComunaView view;
    private Pane graph;

    public Comuna(){
        double width = SimulatorConfig.WIDTH;
        double length = SimulatorConfig.LENGTH;
        territory = new Rectangle2D(50,50, width, length);
        double speed = SimulatorConfig.SPEED;
        double deltaAngle = SimulatorConfig.DELTA_THETA;
        view = new ComunaView(this); // What if you exchange this and the follow line?
        person=new Pedestrian(this, speed, deltaAngle);
        graph = new Pane();  // to be completed in other stages.
    }

    public double getWidth() {
        return territory.getWidth();
    }
    public double getHeight() {
        return territory.getHeight();
    }
    public void computeNextState (double delta_t) {
        person.computeNextState(delta_t);
    }

    public void updateState () {
        person.updateState();
    }

    public void updateView(){
        view.update();
    }

    public Pedestrian getPedestrian() {
        return person;
    }

    public void ResetPedestrian(){
        this.person = new Pedestrian(this,SimulatorConfig.SPEED,SimulatorConfig.DELTA_THETA);
    }

    public Group getView() {
        return view;
    }
    public Pane getGraph(){
        return graph;
    }
 }
