package src;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import javafx.scene.paint.Color;

public class PedestrianView {

    private Pedestrian person;
    private Rectangle view;
    private final double SIZE = 20;

    public PedestrianView(Comuna comuna, Pedestrian p) {
        person = p;
        view = new Rectangle(SIZE, SIZE, p.getColor()); //individuo
        view.setX(person.getX()-SIZE/2);   // Rectangle x position is the X coordinate of the
        // upper-left corner of the rectangle
        view.setY(person.getY()-SIZE/2); // Rectangle y position is the Y coordinate of the
        // upper-left corner of the rectangle
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
        }
        else{
            view.setArcHeight(0);
            view.setArcWidth(0);
        }
    }

    public void ResetPedView(Comuna comuna){
        comuna.getView().getChildren().remove(this.view);
    }
}
