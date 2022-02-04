package sources3;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Comuna {
    public ArrayList<Individuo> people; //Arreglo de individuos
    private Rectangle2D territory; // Alternatively: double width, length;

 //CONSTRUCTOR POR DEFECTO
    public Comuna() {
        territory = new Rectangle2D.Double(0, 0, 1000, 1000); // 1000x1000 m²;
        people = new  ArrayList<Individuo>();
        //people.add(null);
    }

    //CONSTRUCTOR DE COMUNA CUANDO SE INGRESAN PARAMETROS
    public Comuna(double width, double length) {
        territory = new Rectangle2D.Double(0, 0, width, length);
        people = new  ArrayList<Individuo>();
        //people.add(null);

    }

    //ENTREGA ANCHO DE LA COMUNA
    public double getWidth() {
        return territory.getWidth();
    }

    //ENTREGA ALTURA DE LA COMUNA
    public double getHeight() {
        return territory.getHeight();

    }

    //INGRESA LAS PERSONAS DEL ARREGLO arr_people A LA COMUNA
    public void setPerson(ArrayList<Individuo> arr_people) {
        int N = arr_people.size();

        for (int i= 0; i< N; i++ )
        {
            people.add(arr_people.get(i));
        }
        return;
    }

    //CALCULA LA POSICION SIGUIENTE DE CADA INDIVIDUO DE LA COMUNA
    public void computeNextState(double delta_t) {

        int N = people.size();

        for (int i= 0; i< N; i++ )
        {
            people.get(i).computeNextState(delta_t);
        }
        return;

    }

    //ACTUALIZA EL ESTADO DE CADA INDIVIDUO DE LA COMUNA
    public void updateState() {
        int N = people.size();

        for (int i= 0; i< N; i++ )
        {
            people.get(i).updateState();
        }

        return;
    }


    // TITULO COLUMNA STATE
    public static String getStateDescription()
    {
        return "I,\tR,\tS" ;
    }

    //IMPRIME LOS VALORES DE LAS COLUMNAS I,R Y S
    public String getState() {
        int I= 0, R= 0, S = 0;

        int N = people.size();


        for (int i=0; i< N; i++)
        {
            if (people.get(i).getStateInf()) I++;
            if (people.get(i).getStateSus()) S++;
            if (people.get(i).getStateRec()) R++;
        }
        return I + ",\t" + R +", \t"+ S ;

    }

 }
