import sources3.*; // Importando todas las clases de sources1
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Stage3 {
    public static void main(String [] args) throws IOException
    {
        if (args.length != 1) {
            System.out.println("Usage: java Stage3 Main <configurationFile.txt>");
            System.exit(-1);
        }
        Scanner s=new Scanner(new File(args[0]));
        System.out.println("File: "+args[0]);

        //Scanner s=new Scanner(new File("configurationFile.txt"));
        //System.out.println("File: configurationFile.txt");

        //LECTURA DE DATOS DESDE EL ARCHIVO configurationFile.txt
        double simulationDuration = s.nextDouble();
        System.out.println("Simulation time: "+simulationDuration);

        double N = s.nextDouble();        //Individuos
        double I = s.nextDouble();        //Numero de infectados iniciales
        double I_time = s.nextDouble();   //Tiempo de infeccion


        double comunaWidth = s.nextDouble();
        double comunaLength = s.nextDouble();

        double speed = s.nextDouble();
        double delta_t = s.nextDouble();
        double deltaAngle = s.nextDouble();
        double d = s.nextDouble();
        double M = s.nextDouble();
        double p0 = s.nextDouble();
        double p1 = s.nextDouble();
        double p2 = s.nextDouble();
        double Num_vac = s.nextDouble();
        double Vac_size = s.nextDouble();
        double Vac_time = s.nextDouble();

        //Tiempo de sampleo
        double samplingTime = 1;  // 1 [s]

        //arreglo con los parámetros extraidos desde el archivo configurationFile.txt
        double [] parameters = {N, I, I_time, speed, delta_t, deltaAngle, samplingTime, d, p0, M, p1, p2};


        Comuna comuna = new Comuna(comunaWidth, comunaLength); // Crea comuna

        Simulador sim = new Simulador(System.out, comuna, parameters); //Crear simulador

        sim.simulate(simulationDuration); // Ejecutar el método que inicia la simulación


    }
}

