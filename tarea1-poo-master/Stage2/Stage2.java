import sources2.*; // Importando todas las clases de sources1
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Stage2 {
    public static void main(String [] args) throws IOException
    {
      if (args.length != 1) {
            System.out.println("Usage: java Stage2 Main <configurationFile.txt>");
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


        double comunaWidth = s.nextDouble();//ANCHO DE LA COMUNA
        double comunaLength = s.nextDouble();//ALTURA DE LA COMUNA

        double speed = s.nextDouble(); //VELOCIDAD DE LOS INDIVIDUOS
        double delta_t = s.nextDouble();//DIFERENCIAL DE TIEMPO DE LA SIMULACION
        double deltaAngle = s.nextDouble();// DIFERENCIAL DE ANGULO PARA LAS DIRECCIONES DE LOS INDIVIDUOS
        double d = s.nextDouble(); //DISTANCIA LIMITE A LA QUE COMIENZA LA PROBABILIDAD DE CONTAGIO
        double M = s.nextDouble(); //FRACCIÓN DE LOS N INDIVIDUOS CORRESPONDIENTES A CADA TIPO
        double p0 = s.nextDouble(); //PROBABILIDAD DE CONTAGIO PARA 0 INDIVIDUOS CON MASCARILLA
        double p1 = s.nextDouble();//PROBABILIDAD DE CONTAGIO PARA 1 INDIVIDUOS CON MASCARILLA
        double p2 = s.nextDouble(); //PROBABILIDAD DE CONTAGIO PARA 2 INDIVIDUOS CON MASCARILLA
        double Num_vac = s.nextDouble(); //NUMERO DE VACUNATORIOS
        double Vac_size = s.nextDouble(); //TAMAÑO DE LOS VACUNATORIOS
        double Vac_time = s.nextDouble(); //TIEMPO DE LA SIMULACION EN QUE COMIENZAN A APRECER LOS VACUNATORIOS

      //Tiempo de sampleo
        double samplingTime = 1;  // 1 [s]

      //arreglo con los parámetros extraidos desde el archivo configurationFile.txt
        double [] parameters = {N, I, I_time, speed, delta_t, deltaAngle, samplingTime, d, p0};


        Comuna comuna = new Comuna(comunaWidth, comunaLength); // Crea comuna

        Simulador sim = new Simulador(System.out, comuna, parameters);//Crear simulador


      sim.simulate(simulationDuration); // Ejecutar el método que inicia la simulación


    }
}

