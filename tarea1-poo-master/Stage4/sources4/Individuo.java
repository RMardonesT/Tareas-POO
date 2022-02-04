package sources4;

import java.util.ArrayList;

public class Individuo {
    private double x, y, speed, angle, deltaAngle;  //Coordenadas iniciales del individuo, velocidad, angulo inicial, parametro angular del archivo.txt
    private double x_tPlusDelta, y_tPlusDelta;    //Coordenadas siguientes del individuo
    private double time_inf;     //Tiempo de duración de la infección
    private Comuna comuna;   //Comuna a la que pertenece el individuo
    private boolean stateInf, stateRec, stateSus; //Estados de infección, recuperación y suscetibilidad del individuo, true or false
    private boolean new_stateInf, new_stateRec,new_stateSus;  //Estados siguientes de infección,recuperación y suceptibilidad, true or false
    private boolean Mask; //Uso de mascarilla, true or false
    public boolean stateVac;  //Estado de Vacuna , true or false


    /* CONSTRUCTOR CUANDO SE INGRESAN PARAMETROS
     * @param comuna: Comuna en la que se crea el vacunatorio
     * @param speed: Velocidad de movimiento
     * @param deltaAngle: Tamaño del vacunatorio
     * @param state: Valor int que indica si el individuo iniciará infectado o no,  //state =1 -> infectado, state = 0-> susceptible
     * @param I_time: tiempo que dura la infección en un individuo
     * @param mask: valor booleano que indica si el individuo usa mascarilla o no, true = si, false = no
     */

    public Individuo (Comuna comuna, double speed, double deltaAngle, int state, double I_time, boolean mask)
    {
        angle = Math.random()*2*Math.PI;
        this.speed = speed;
        this.comuna = comuna;
        this.deltaAngle = deltaAngle;
        Mask = mask;

        double  xlim = comuna.getWidth();
        double ylim = comuna.getHeight();

        x = Math.round(((Math.random() * (xlim - 0)) + 0));
        y = Math.round((Math.random() * (ylim - 0)) + 0);

        stateRec = false;
        stateVac = false;

        switch(state)
        {
            case 0:    stateInf = false;
                        time_inf = 0.0;
                        stateSus = true;
                        break;
            case 1:    stateInf = true;
                        time_inf = ((Math.random() * (I_time*0.9 - 0)) + 0); //se considera un tiempo de infeccion aleatorio en t=0
                        stateSus = false;
                        break;
            default: break;
        }


    }

    /*CALCULA EL NUEVO ESTADO DE UN INDIVIDUO SUSCEPTIBLE
     * @param person2: persona con la que se esta comparando la ubicación para el calculo de distancia entre ellos
     * @param d: Distancia límite para posibilidad de contagio
     * @param P: Probabilidad de que el individuo se contagie de el individuo person2

    /*Si el individuo es susceptible, se actualiza su estado de infección asumiendo que person2
    esta infectado. (Se verifica en la parte de update_comuna)*/


    public boolean getSusNewState(Individuo person2, double d, double P)
    {
        new_stateInf = stateInf;
        new_stateRec = stateRec;
        new_stateSus = stateSus;
        if(!stateRec && !stateInf)
        {
            new_stateRec = false;
            if ((Math.pow(this.x - person2.getStateX(),2) + Math.pow(this.y - person2.getStateY(),2))<= d*d)
            {
                if (Math.random() < P) {
                    new_stateInf = true;
                    new_stateSus = false;
                }
                else {
                    new_stateInf = false;
                    new_stateSus = true;
                }
            }

        }
        return new_stateInf;
    }

    //Se asume individuo suscetible no vacunado

    /* VERIFICA SI UN INDIVIDUO SUSCEPTIBLE PASA POR UN VACONATORIO
     * @param vacunatorios: Arreglo de vacunatorios
     * @param VacSize : Tamaño del lado de cada vacunatorio
    */

    public void verifyVac(ArrayList<Vacunatorio> vacunatorios, double VacSize)
    {
        for(int i = 0; i < vacunatorios.size(); i++)
        {
            if ((this.x > vacunatorios.get(i).getVacX()) && (this.x < (vacunatorios.get(i).getVacX()) + VacSize) && (this.y > vacunatorios.get(i).getVacY()) && (this.y < (vacunatorios.get(i).getVacY()) + VacSize) )
            {
                stateVac = true;
                stateSus = false;
                stateRec = false;
                stateInf = false;
                new_stateSus = stateSus;
                new_stateRec = stateRec;
                new_stateInf = stateInf;

                break;
            }
            else
            {
                stateVac = false;
                new_stateSus = stateSus;
                new_stateRec = stateRec;
                new_stateInf = stateInf;
            }
        }
    }

    /* CALCULA SI UN INFECTADO PASA A RECUPARADO
     * @param delta_t:  tiempo de computo de simulación
     * @param I_time : Tiempo de duración de la infección para pasar a recuperado
     */
    public void getInfNewState(double delta_t, double I_time){
        new_stateSus = stateSus;
        new_stateRec = stateRec;
        new_stateInf = stateInf;
        if (time_inf+delta_t < I_time)
        {
            time_inf += delta_t;
        }
        else{
            new_stateSus = false;
            new_stateRec = true;
            new_stateInf = false;
        }
    }
    /* MANTIENE LOS ESTADOS ACTUALES PARA LOS INDIVIDUOS RECUPERADOS */
    public void getRecNewState() {
        new_stateSus = stateSus;
        new_stateRec = stateRec;
        new_stateInf = stateInf;
        return;
    }

    /* ENTREGA EL VALOR BOOLEANO QUE INDICA SI EL INDIVIDUO USA MASCARA O NO*/

    public boolean getMaskState()
    {
        return Mask;
    }



    //TITULO COLUMNA STATE
    public static String getStateDescription(){

        return "x,\ty";
    }



    // ENTREGAN LA POSICION DEL INDIVIDUO
    public double getStateX() {
        return  x;
    }
    public double getStateY() {
        return  y;
    }

    //ENTREGAN ESTADO SOBRE LA INFECCIÓN, RECUPERACIÓN, SUSCEPTIBILIDAD Y VACUNACIÓN DEL INDIVIDUO

    public boolean getStateInf()
    {
        return stateInf;
    }
    public boolean getStateRec()
    {
        return stateRec;
    }
    public boolean getStateSus()
    {
        return stateSus;
    }
    public boolean getStateVac()
    {
        return stateVac;
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

    //ACTUALIZA LA POSICION Y ESTADOS ACTUALES  DEL INDIVIDUO A LOS CALCULADA EN computeNextState  Y TODOS LOS METODOS DEL TIPO getNewState
    public void updateState(){
        x = x_tPlusDelta;
        y = y_tPlusDelta;
        stateSus = new_stateSus;
        stateRec = new_stateRec;
        stateInf = new_stateInf;
        return;
    }
}
