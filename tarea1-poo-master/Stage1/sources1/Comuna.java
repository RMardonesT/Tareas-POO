package sources1;
import java.awt.geom.Rectangle2D;

public class Comuna {
    private Individuo person;
    private Rectangle2D territory; // Alternatively: double width, length;

    //CONSTRUCTOR POR DEFECTO
    public Comuna() {
        territory = new Rectangle2D.Double(0, 0, 1000, 1000); // 1000x1000 m²;
        person =null;
    }
    //CONSTRUCTOR DE COMUNA CUANDO SE INGRESAN PARAMETROS
    public Comuna(double width, double length) {
        territory = new Rectangle2D.Double(0, 0, width, length);
        person = null;

    }
    //ENTREGA ANCHO DE LA COMUNA
    public double getWidth() {
        return territory.getWidth();
    }
    //ENTREGA ALTURA DE LA COMUNA
    public double getHeight() {
        return territory.getHeight();

    }
    //INGRESA LA PERSONA A LA COMUNA
    public void setPerson(Individuo person) {
        this.person = person;
        return;
    }
    //CALCULA LA POSICION SIGUIENTE DE CADA INDIVIDUO DE LA COMUNA
    public void computeNextState(double delta_t) {
        person.computeNextState(delta_t);

    }
    //ACTUALIZA EL ESTADO DE CADA INDIVIDUO DE LA COMUNA
    public void updateState() {
        person.updateState();
        return;
    }



    // TITULO COLUMNA STATE
    public static String getStateDescription()
    {
        return "coorx ,\t coor_y" ;
    }

    //ENTREGA LOS VALORES DE LAS COLUMNAS X,Y
    public String getState() {
        return person.getState();
    }

 }
