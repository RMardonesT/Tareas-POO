package src;

import java.awt.geom.Rectangle2D;

public class Vacunatorio {
    private Rectangle2D territory;
    //private Comuna comuna;
    private double VacSize;
    private  double PedSize;

    /* CONSTRUCTOR CUANDO SE INGRESAN PARAMETROS
     * @param VacSize: Tamaño del vacunatorio
     * @param comuna: Comuna en la que se crea el vacunatorio
     */
    public Vacunatorio(double Vacsize, Comuna comuna)
    {
        PedSize = comuna.PedSize;
        //this.comuna = comuna
        this.VacSize = Vacsize;
        double vacX = Math.random()*(comuna.getWidth()-VacSize);
        double vacY= Math.random()*(comuna.getHeight()-VacSize);
        territory = new Rectangle2D.Double(vacX, vacY, VacSize, VacSize);
        VacView vacView = new VacView(comuna, this);
    }

    /* ENTREGA COORDENADA X DEL VACUNATORIO*/
    public double getVacX() {
        return territory.getX();
    }

    /* ENTREGA COORDENADA Y DEL VACUNATORIO*/
    public double getVacY()
    {
        return territory.getY();
    }
}

