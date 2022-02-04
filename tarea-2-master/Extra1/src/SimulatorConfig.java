package src;
import java.util.Scanner;

public class SimulatorConfig {  // This class is just to read the input configuration file
    // and then to have a convenient way to access each parameter.
    protected static double N, I, I_TIME;
    protected static double WIDTH, LENGTH;
    protected static double SPEED, DELTA_T, DELTA_THETA;
    protected static double D, M, P0, P1, P2;
    protected static double NUM_VAC, VAC_SIZE, VAC_TIME;
    protected static double I_TIME_txt;

    public SimulatorConfig(Scanner s){
        N=s.nextDouble(); I= s.nextDouble(); I_TIME_txt=s.nextDouble(); I_TIME = I_TIME_txt*10;
        WIDTH=s.nextDouble(); LENGTH= s.nextDouble();
        SPEED=s.nextDouble(); DELTA_T=s.nextDouble(); DELTA_THETA=s.nextDouble();
        D=s.nextDouble(); M=s.nextDouble(); P0=s.nextDouble(); P1=s.nextDouble(); P2=s.nextDouble();
        NUM_VAC=s.nextDouble(); VAC_SIZE=s.nextDouble(); VAC_TIME=s.nextDouble()*10;
    }


}
