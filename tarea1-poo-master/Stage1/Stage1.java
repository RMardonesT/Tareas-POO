import sources1.*; // Importando todas las clases de sources1
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Stage1 {
    public static void main(String [] args) throws IOException
    {
      if (args.length != 1) {
            System.out.println("Usage: java Stage1 Main <configurationFile.txt>");
            System.exit(-1);
        }
        Scanner s=new Scanner(new File(args[0]));
        System.out.println("File: "+args[0]);

        //LECTURA DE DATOS DESDE EL ARCHIVO configurationFile.txt
        double simulationDuration = s.nextDouble();   //TIEMPO DE LA SIMULACION
        System.out.println("Simulation time: "+simulationDuration);
        s.nextLine();

        double comunaWidth = s.nextDouble(); //ANCHO DE LA COMUNA
        double comunaLength = s.nextDouble();//ALTURA DE LA COMUNA

        double speed = s.nextDouble(); //VELOCIDAD
        double delta_t = s.nextDouble(); //DIFERENCIAL DE TIEMPO DE LA SIMULACION
        double deltaAngle = s.nextDouble(); //DIFERENCIAL DE ANGULO DE LA DIRECCIÓN DE LOS INDIVIDUOS EN LA SIMULACION

        //Tiempo de sampleo
        double samplingTime = 1.0;  // 1 [s]


        Comuna comuna = new Comuna(comunaWidth, comunaLength); // Crea comuna

        Individuo person = new Individuo(comuna, speed, deltaAngle); //Crear un individuo

        comuna.setPerson(person); //añade individuo a la comuna

        Simulador sim = new Simulador(System.out, comuna);//Crear simulador

        sim.simulate(delta_t, simulationDuration,samplingTime);// Ejecutar el método que inicia la simulación


    }
}
