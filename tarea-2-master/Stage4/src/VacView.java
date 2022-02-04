package src;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import javafx.scene.paint.Color;

public class VacView {
    private Vacunatorio vacunatorio;
    private Rectangle view;
    private final double SIZE = SimulatorConfig.VAC_SIZE;

    public VacView(Comuna comuna, Vacunatorio v) {
        vacunatorio = v;
        view = new Rectangle(SIZE, SIZE); //individuo
        view.setFill(Color.rgb(144, 238, 144, 0.5));
        view.setX(vacunatorio.getVacX());   // Rectangle x position is the X coordinate of the
        // upper-left corner of the rectangle
        view.setY(vacunatorio.getVacY()); // Rectangle y position is the Y coordinate of the
        // upper-left corner of the rectangle
        comuna.getView().getChildren().add(view);
    }
}