package src;

import javafx.scene.paint.Color;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;




public class Pedestrian {
    private double x, y, speed, angle, deltaAngle;
    private double x_tPlusDelta, y_tPlusDelta;
    private Comuna comuna;

    private PedestrianView pedestrianView;
    private double time_inf;
    private Color color ;
    public int id;

    public int state, newState;  //0: susc, 1:inf, 2: rec, 3: vac

    private boolean redFlag = true;  //Para permitir habilitar el cambio de rojo intenso a rojo suave en caso de infeccion
    private boolean mask;

    //Crea el objeto media a reproducir
    private static final String MEDIA_URL = Comuna.class.getResource("oh_no_short.wav").toExternalForm();
    Media media = new Media(MEDIA_URL);

    //Constructor Pedestrian
    public Pedestrian (Comuna comuna, double speed, double deltaAngle, int state, double I_time, boolean mask){
        this.comuna = comuna;
        this.speed = speed*(Math.random()*(1.1-0.9) + 0.9);
        this.deltaAngle=deltaAngle;
        x = Math.random()*comuna.getWidth();
        y = Math.random()*comuna.getHeight();
        angle = Math.random()*2*Math.PI;
        this.mask = mask;

        switch((int)state)
        {
            case 0: {this.state= 0;
                    this.time_inf = 0.0;
                    this.color = Color.BLUE;
                    break;}
            case 1: {this.state = 1;
                    this.time_inf = ((Math.random() * (I_time*0.9 - 0)) + 0); //se considera un tiempo de infeccion aleatorio en t=0
                    this.color = Color.RED;
                    break;}
            default: break;
        }
        newState = state;

        pedestrianView = new PedestrianView(this.comuna,this);

    }


        /*CALCULA EL NUEVO ESTADO DE UN INDIVIDUO SUSCEPTIBLE
     * @param person2: persona con la que se esta comparando la ubicación para el calculo de distancia entre ellos
     * @param d: Distancia límite para posibilidad de contagio
     * @param P: Probabilidad de que el individuo se contagie de el individuo person2

    /*Si el individuo es susceptible, se actualiza su estado de infección asumiendo que person2
    esta infectado. (Se verifica en la parte de update_comuna)*/

    public int getNewState(Pedestrian person2, double d, double P0, double P1, double P2)
    {
        newState = state;

        if((state == 0 )&& (!this.equals(person2))) //que no este ni infectado ni recuperado
        {
            double auxD = (Math.pow(this.x - person2.getX(),2) + Math.pow(this.y - person2.getY(),2));
            double powD = d*d;
            //Analizar la posibilidad de contagio
            if (auxD <= powD) {
                if (this.getMask() && person2.getMask()){
                    if (Math.random() < P2) {
                        newState = 1;
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        mediaPlayer.play();
                        color = Color.RED;
                    }
                }
                else if (this.getMask() || person2.getMask()){
                    if (Math.random() < P1) {
                        newState = 1;
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        mediaPlayer.play();
                        color = Color.RED;
                    }
                }
                else{
                    if (Math.random() < P0) {
                        newState = 1;
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        mediaPlayer.play();
                        color = Color.RED;
                    }
                }

            }
        }

        if (state == 1){
            if (time_inf+SimulatorConfig.DELTA_T < SimulatorConfig.I_TIME)
            {
                time_inf += SimulatorConfig.DELTA_T;
                if (redFlag) {color = Color.rgb(255, 120, 200); redFlag = false;}
                else {color = Color.rgb(248,0,0); redFlag = true;}
            }
            else{
                newState = 2;
                color = Color.rgb(101,67,33);
            }
        }

        return newState;
    }


    public void computeNextState(double delta_t) {
        double r=Math.random();
        angle+=deltaAngle*(1-2*r);
        x_tPlusDelta=x+speed*Math.cos(angle)*delta_t;
        y_tPlusDelta=y+speed*Math.sin(angle)*delta_t;
        if(x_tPlusDelta < 0){   // rebound logic
            x_tPlusDelta=-x_tPlusDelta;
            angle=Math.PI-angle;
        }
        if(y_tPlusDelta < 0){
            y_tPlusDelta=-y_tPlusDelta;
            angle=2*Math.PI-angle;
        }
        if( x_tPlusDelta > comuna.getWidth()){
            x_tPlusDelta=2*comuna.getWidth()-x_tPlusDelta;
            angle=Math.PI-angle;
        }
        if(y_tPlusDelta > comuna.getHeight()){
            y_tPlusDelta=2*comuna.getHeight()-y_tPlusDelta;
            angle=2*Math.PI-angle;
        }
    }

    public void updateState(){
        x=x_tPlusDelta;
        y=y_tPlusDelta;
        state = newState;
    }
    public void updateView() {

        pedestrianView.update();
    }


    public int getState(){return state;}
    public boolean getMask(){
        return this.mask;
    }
    public Color getColor(){return this.color;}
    public double getX(){
        return x;
    }
    public double getY() {
        return y;
    }


}
