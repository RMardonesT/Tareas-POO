package sources1;
public class Individuo {
    private double x, y, speed, angle, deltaAngle;
    private double x_tPlusDelta, y_tPlusDelta;
    private Comuna comuna;

    /* CONSTRUCTOR CUANDO SE INGRESAN PARAMETROS
     * @param comuna: Comuna en la que se crea el vacunatorio
     * @param speed: Velocidad de movimiento
     * @param deltaAngle: Tamaño del vacunatorio
     */

    public Individuo (Comuna comuna, double speed, double deltaAngle){

        angle = Math.random()*2*Math.PI;
        this.speed = speed;
        this.comuna = comuna;
        this.deltaAngle = deltaAngle;

        double  xlim = comuna.getWidth();
        double ylim = comuna.getHeight();

         x = Math.round(((Math.random() * (xlim - 0)) + 0));
         y = Math.round((Math.random() * (ylim - 0)) + 0);


    }


    //TITULO COLUMNA STATE
    public static String getStateDescription(){

        return "x,\ty";
    }

    // ENTREGAN LA POSICION ACTUAL DEL INDIVIDUO
    public String getState() {
        return x + ",\t" + y;
    }


    //CALCULA LA SIGUIENTE POSICION DEL INDIVIDUO
    public void computeNextState(double delta_t) {

        double r =Math.random();

        angle=  ((r * (angle + deltaAngle )) - (angle- deltaAngle))*2*Math.PI;


        x_tPlusDelta = Math.round((x+speed*Math.cos(angle)*delta_t)*10)/10.0;
        y_tPlusDelta = Math.round((y+speed*Math.sin(angle)*delta_t)*10)/10.0;


        //CASOS EJE X
        if(x_tPlusDelta < 0){   // rebound logic
            x_tPlusDelta = -x_tPlusDelta ;
            angle = -angle;
        }
        else if( x_tPlusDelta > comuna.getWidth()){
            x_tPlusDelta =  comuna.getWidth() - (x_tPlusDelta - comuna.getWidth()) ;
            angle = -angle;
        }

        // CASOS EJE Y
        if(y_tPlusDelta < 0){   // rebound logic
            y_tPlusDelta = -y_tPlusDelta;
            angle = -angle;
        }
        else if( y_tPlusDelta > comuna.getHeight()){
            y_tPlusDelta = comuna.getHeight()  - (y_tPlusDelta - comuna.getHeight() );
            angle = -angle;
        }

        return;
    }
    //ACTUALIZA LA POSICION DEL INDIVIDUO A LOS CALCULADA EN computeNextState

    public void updateState(){
	    x = x_tPlusDelta;
	    y = y_tPlusDelta;

        return;
    }
}
