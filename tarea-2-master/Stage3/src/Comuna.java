package src;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;



public class Comuna {
    private ArrayList<Pedestrian> people;
    private Rectangle2D territory; // Alternatively: double width, length;
                                   // but more methods would be needed.
    private ComunaView view;
    private StackPane graph;
    public NumberAxis ejeX;
    public NumberAxis ejeY;
    public StackedAreaChart<Number, Number> grafico;
    //Crear "Series" para I, R, S (abajo hacia arriba)
    private XYChart.Series<Number, Number> dataI;
    private XYChart.Series<Number, Number> dataR;
    private XYChart.Series<Number, Number> dataS;

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
        graph = new StackPane();  // to be completed in other stages.
        ejeX = new NumberAxis();
        ejeY = new NumberAxis();
        grafico = new StackedAreaChart<Number, Number>(ejeX, ejeY);
        dataI = new XYChart.Series<Number, Number>();
        dataR = new XYChart.Series<Number, Number>();
        dataS = new XYChart.Series<Number, Number>();
        createGraph();
        N = SimulatorConfig.N;

    }

    public void createGraph(){
        grafico.setTitle("Grafico de Areas Apiladas");
        ejeX.setLabel("Tiempo [s]");
        ejeY.setLabel("N° Individuos [-]");


        //Agregar valores iniciales

        dataI.getData().add(new XYChart.Data(0, SimulatorConfig.I));
        dataR.getData().add(new XYChart.Data(0, 0));
        dataS.getData().add(new XYChart.Data(0, SimulatorConfig.N - SimulatorConfig.I));


        //Etiquetar areas

        dataI.setName("Infectados");
        dataR.setName("Recuperados");
        dataS.setName("Susceptibles");

        grafico.getData().addAll(dataI, dataR, dataS);
        this.graph.getChildren().add(grafico);
    }

    public void resetGraph(){
        grafico = new StackedAreaChart<Number, Number>(ejeX, ejeY);

        dataI = new XYChart.Series<Number, Number>();
        dataR = new XYChart.Series<Number, Number>();
        dataS = new XYChart.Series<Number, Number>();
        createGraph();
    }

    private void populate_comuna(){
        int PEAK = (int)SimulatorConfig.N;
        boolean mask;
        int num_maskInf = (int) Math.round(SimulatorConfig.I*SimulatorConfig.M);
        int num_maskSus = (int) Math.round((SimulatorConfig.N-SimulatorConfig.I)*SimulatorConfig.M);


        for (int i=0; i< PEAK; i++)
        {
            int state = 0;  //Por defecto state = 0

            if (i < SimulatorConfig.I) state = 1;  //Incluye I personas con state = 1
            if(i<num_maskInf || (i >= SimulatorConfig.I && i < SimulatorConfig.I+num_maskSus))
            {
                mask = true;
            }
            else
            {
                mask = false;
            }
            Pedestrian person = new Pedestrian( this, SimulatorConfig.SPEED, SimulatorConfig.DELTA_THETA , state ,SimulatorConfig.I_TIME, mask);
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
                            int auxNextState = person1.getNewState(person2, SimulatorConfig.D, SimulatorConfig.P0, SimulatorConfig.P1, SimulatorConfig.P2);
                            if (auxNextState == 1) {
                                break;
                            }
                        }

                    }
                }
                else {
                    person1.getNewState(person1, SimulatorConfig.D, SimulatorConfig.P0, SimulatorConfig.P1, SimulatorConfig.P2);
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
        //System.out.println( "I,\tR,\tS") ;
    }

    //IMPRIME LOS VALORES DE LAS COLUMNAS  I,R Y S
    public void getState(double time) {
        int I= 0, R= 0, S = 0;

        int N = people.size();

        for (int i=0; i< N; i++)
        {
            if (people.get(i).state == 1) I++;
            if (people.get(i).state == 0) S++;
            if (people.get(i).state == 2) R++;
        }
        dataI.getData().add(new XYChart.Data(time/10, I));
        dataR.getData().add(new XYChart.Data(time/10, R));
        dataS.getData().add(new XYChart.Data(time/10, S));
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
