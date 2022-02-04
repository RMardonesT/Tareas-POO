package src;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class PedestrianView {

    private Pedestrian person;
    private Comuna comuna;
    private Rectangle view;
    private double SIZE;
    private Polygon polygon;
    private Double[] points;
    private  Double[] old_points;


    public PedestrianView(Comuna comuna, Pedestrian p) {
        this.comuna = comuna;
        SIZE = comuna.PedSize;
        person = p;
        view = new Rectangle(SIZE, SIZE, p.getColor()); //individuo
        view.setX(person.getX()-SIZE/2);   // Rectangle x position is the X coordinate of the
        // upper-left corner of the rectangle
        view.setY(person.getY()-SIZE/2); // Rectangle y position is the Y coordinate of the
        // upper-left corner of the rectangle
        view.setStrokeWidth(4.0);
        if (p.getMask()) {
            view.setStroke(Color.BLACK);
        }
        polygon = new Polygon();
        points = new Double[]{person.getX()+10.0, person.getY(),person.getX(),person.getY()+20.0,person.getX()+20.0,person.getY()+20};
        polygon.setFill(Color.TRANSPARENT);
        polygon.getPoints().addAll(points);
        comuna.getView().getChildren().add(polygon);

        comuna.getView().getChildren().add(view);
    }
    public void update() { //actualiza posicion
        view.setX(person.getX());
        view.setY(person.getY());
        view.setFill(person.getColor());


        if (person.getState() == 1){
            view.setArcHeight(SIZE);
            view.setArcWidth(SIZE);
            view.setFill(person.getColor());
            view.setStroke(Color.rgb(128,0,0));
        }
        else if (person.getState() == 3)
        {
            view.setFill(Color.TRANSPARENT);
            view.setStroke(Color.TRANSPARENT);

            polygon.setFill(person.getColor());
            comuna.getView().getChildren().remove(polygon);
            polygon = new Polygon();
            points = new Double[]{person.getX()+10.0, person.getY(),person.getX(),person.getY()+20.0,person.getX()+20.0,person.getY()+20};
            polygon.setFill(person.getColor());
            polygon.getPoints().addAll(points);
            comuna.getView().getChildren().add(polygon);

            if (person.getMask()){
                polygon.setStroke(Color.BLACK);
                polygon.setStrokeWidth(4);
            }

        }
        else {
            view.setArcHeight(0);
            view.setArcWidth(0);
            if (person.getMask()) view.setStroke(Color.BLACK);
            else view.setStroke(person.getColor());

        }
    }

    public void ResetPedView(Comuna comuna){
        comuna.getView().getChildren().remove(this.view);
    }
}
