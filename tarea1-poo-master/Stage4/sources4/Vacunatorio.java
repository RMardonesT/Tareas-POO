package sources4;
import java.awt.geom.Rectangle2D;

public class Vacunatorio {
    private Rectangle2D territory;
    //private Comuna comuna;
    private double VacSize;


    //CONSTRUCTOR POR DEFECTO
    public Vacunatorio()
    {
        territory = new Rectangle2D.Double(5, 5, 5, 5);
        //comuna = null;
        this.VacSize = 5;
    }


    /* CONSTRUCTOR CUANDO SE INGRESAN PARAMETROS
     * @param VacSize: Tamaño del vacunatorio
     * @param comuna: Comuna en la que se crea el vacunatorio
     */
    public Vacunatorio(double VacSize, Comuna comuna)
    {
        //this.comuna = comuna;
        this.VacSize = VacSize;
        double vacX = Math.random()*(comuna.getWidth()-VacSize);
        double vacY= Math.random()*(comuna.getHeight()-VacSize);
        territory = new Rectangle2D.Double(vacX, vacY, VacSize, VacSize);
    }

    /* ENTREGA COORDENADA X DEL VACUNATORIO*/
    public double getVacX()
    {
        return territory.getX();
    }

    /* ENTREGA COORDENADA Y DEL VACUNATORIO*/
    public double getVacY()
    {
        return territory.getY();
    }
}
