package src;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class ComunaView extends Group {
    private final Comuna comuna;
    private  static  double SIZE = 20;

    public ComunaView(Comuna c){
        comuna = c;
        Rectangle territoryView = new Rectangle(comuna.getWidth() + SIZE, comuna.getHeight()+SIZE, Color.WHITE);
        territoryView.setStroke(Color.BROWN);
        getChildren().add(territoryView);
        setFocusTraversable(true);  // needed to receive mouse and keyboard events.
    }

    public void update(){

        for (int i=0; i < comuna.getPedestrians().size(); i++)
            comuna.getPedestrians().get(i).updateView();
        //grax tamy <3
    }

}
