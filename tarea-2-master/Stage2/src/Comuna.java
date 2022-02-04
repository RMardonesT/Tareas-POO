package src;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;



public class Comuna {
    private ArrayList<Pedestrian> people;
    private Rectangle2D territory; // Alternatively: double width, length;
                                   // but more methods would be needed.
    private ComunaView view;
    private Pane graph;

    private  double N;

    //Constructor
    public Comuna() {
        double width = SimulatorConfig.WIDTH;
        double length = SimulatorConfig.LENGTH;
        territory = new Rectangle2D(50,50, width, length);
        double speed = SimulatorConfig.SPEED;
        double deltaAngle = SimulatorConfig.DELTA_THETA;
        view = new ComunaView(this); // What if you exchange this and the follow line?
        people = new ArrayList<Pedestrian>();
        populate_comuna();
        graph = new Pane();  // to be completed in other stages.

        N = SimulatorConfig.N;

    }


    private void populate_comuna(){
        int PEAK = (int)SimulatorConfig.N;

        for (int i=0; i< PEAK; i++)
        {
            int state = 0;  //Por defecto state = 0

            if (i < SimulatorConfig.I) state = 1;  //Incluye I personas con state = 1

            Pedestrian person = new Pedestrian( this, SimulatorConfig.SPEED, SimulatorConfig.DELTA_THETA , state ,SimulatorConfig.I_TIME,i );
            this.people.add(person);
        }

    }

    public void Interaction(){
        for (int i= 0; i < people.size()  ; i++){
            Pedestrian person1 = people.get(i);
            if (person1.getState() !=2) {

                if (person1.getState() == 0){  //si la persona es susceptible

                    for (int k = 0; k < people.size(); k++) {
                        Pedestrian person2 = people.get(k);
                        if (person2.getState() == 1) {
                            int auxNextState = person1.getNewState(person2, SimulatorConfig.D, SimulatorConfig.P0);
                            if (auxNextState == 1) {
                                break;
                            }
                        }

                    }
                }
                else {
                    person1.getNewState(person1, SimulatorConfig.D, SimulatorConfig.P0);
                }

            }
        }
    }



    //CALCULA LA POSICION SIGUIENTE DE CADA INDIVIDUO DE LA COMUNA
    public void computeNextState (double delta_t) {

        for (int i= 0; i< SimulatorConfig.N; i++ )
        {
            people.get(i).computeNextState(delta_t);
        }
        return;
    }

    //ACTUALIZA EL ESTADO DE CADA INDIVIDUO DE LA COMUNA
    public void updateState() {

        for (int i= 0; i< SimulatorConfig.N; i++ )
        {
            people.get(i).updateState();
        }
        return;
    }


    public void updateView(){
        view.update();
    }

    public ArrayList<Pedestrian> getPedestrians() {
        return this.people;
    }

    public void ResetPedestrian(){
        this.getView().getChildren().remove(1, (int)this.N+1); //Borro los anteriores al actualizar los parametros
        N = SimulatorConfig.N;  //N actualizado
        people = new ArrayList<Pedestrian>();
        populate_comuna();
    }


    // TITULO COLUMNA STATE
    public static void getStateDescription()
    {
        System.out.println( "I,\tR,\tS") ;
    }

    //IMPRIME LOS VALORES DE LAS COLUMNAS  I,R Y S
    public void getState() {
        int I= 0, R= 0, S = 0;

        int N = people.size();

        for (int i=0; i< N; i++)
        {
            if (people.get(i).state == 1) I++;
            if (people.get(i).state == 0) S++;
            if (people.get(i).state == 2) R++;
        }
        System.out.println( I + ",\t" + R +", \t"+ S );

    }


    public Group getView() {
        return view;
    }
    public Pane getGraph(){
        return graph;
    }
    public double getWidth() {
        return territory.getWidth();
    }
    public double getHeight() {
        return territory.getHeight();
    }
 }
