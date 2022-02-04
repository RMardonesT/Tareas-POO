package sources2;
import java.io.PrintStream;
import java.util.ArrayList;

public class Simulador {
    private Comuna comuna;
    private PrintStream out;
    private double N, I, I_time, speed, delta_t, deltaAngle, samplingTime, d, p0;

    //CONSTRUCTOR POR DEFECTO
    private Simulador(){ }


    /* CONSTRUCTOR DE LA SIMULACION CUANDO TIENE PARAMETROS DE ENTRADA
     * @param output salida por pantalla que entrega la  simulación
     * @param comuna Objeto de la clase comuna que se debe simular
     * @param parameters  arreglo con los parámetros de simulacion provenientes del archivo configurationFile.txt
     */

    public Simulador (PrintStream output, Comuna comuna, double [] parameters){
        out=output;
        this.comuna = comuna;
        this.N = parameters[0];
        this.I = parameters[1];
        this.I_time = parameters[2];
        this.speed = parameters[3];
        this.delta_t = parameters[4];
        this.deltaAngle = parameters[5];
        this.samplingTime = parameters[6];
        this.d = parameters[7];
        this.p0 = parameters[8];

    }
    // IMPRIME LOS  TITULOS LAS COLUMNAS ASOCIADA A LA SIMULACION  T  I R S
    private void printStateDescription(){;
        String s="time,\t"+comuna.getStateDescription();
        out.println(s);
    }
    //IMPRIME LOS LOS VALORES DE LAS COLUMANS T I R S
    private void printState(double t){
        String s = t + ",\t";
        s+= comuna.getState();
        out.println(s);
    }

    //Pobla la comuna con N individuos
    private ArrayList populate_comuna(){
        ArrayList<Individuo> arr_people = new ArrayList<Individuo>();

        for (int i=0; i< N; i++)
        {
            int state = 0;  //Por defecto state = 0

            if (i < I) state = 1;  //Incluye I personas con state = 1

            Individuo person = new Individuo (comuna, speed, deltaAngle, state, I_time);
            arr_people.add(person);
        }
    return arr_people;
    }

    /*   Actualiza el estado de infeccion de los individuos de el arreglod e personas que se encuentran en la comuna en cuestión
     *  Considera el cambio en la probabilidad de infección dependeiendo de el uso de mascarilla de los involucrados
     * Considera el estado de vacunación de los individuos al pasar por vacunatorios
     *
     * @param  arr_people: Arreglo con elementos de tipo Individuo que contiene a las personas de la comuna
     */
    private void update_comuna(ArrayList <Individuo> arr_people)
    {
        for (int i = 0; i < arr_people.size(); i++)
        {
            if (!arr_people.get(i).getStateRec())
            {
                if (arr_people.get(i).getStateInf())
                {
                    arr_people.get(i).getInfNewState(delta_t, I_time);
                }
                else
                {
                    for (int j = 0; j < arr_people.size(); j++)
                    {
                        if(arr_people.get(j).getStateInf())
                        {
                            if (arr_people.get(i).getSusNewState(arr_people.get(j), d, p0))
                            {
                                break;
                            }
                        }
                    }
                }
            }
            else
                arr_people.get(i).getRecNewState();
        }
    }


    /*
     * @param delta_t time step
     * @param endTime simulation time
     * @param samplingTime  time between printing states to not use delta_t that would generate too many lines.
     */
    public void simulate (double endTime) {  // simulate time passing
        double t=0;
        printStateDescription();

        ArrayList <Individuo> arr_people = populate_comuna();
        comuna.setPerson(arr_people);
        printState(t);
        while (t<endTime) {
            if  (t > endTime*0.8) {samplingTime = 0.05*endTime;}   // Actualizacion de la samplingTime cuando queda 20% de la simulacion

            for(double nextStop=t+samplingTime; t<nextStop; t+=delta_t) {
                update_comuna(this.comuna.people);
                comuna.computeNextState(delta_t);// compute its next state based on current global state
                comuna.updateState();            // update its state
            }
            printState(t);
        }
    }
}
