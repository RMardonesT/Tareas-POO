#include "Comuna.h"
#include "Simulator.h"
#include "Pedestrian.h"
#include <QCoreApplication>
#include <iostream>
#include <fstream>
#include <vector>
using namespace std;
int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    if (argc != 2){
        cout << "Usage: stage1 <configurationFile.txt>" << endl;
        exit(-1);
    }
    ifstream fin(argv[1]);
    double N, I, I_time, comunaWidth,comunaLength,speed, delta_t,
            deltaAngle, d, m, p0;//, p1, p2, num_vac, vac_size, vac_time;



    fin >> N >> I >> I_time;
    fin >> comunaWidth >> comunaLength;
    fin >> speed >> delta_t >> deltaAngle;
    fin >> d >> m >> p0; // p1 >> p2;


    //Se guardan los parametros del archivo en un array
    double Param[11] = {N, I, I_time, comunaWidth, comunaLength, speed, delta_t, deltaAngle, d, m, p0};
    double samplingTime = 1.0;


    //Creacion de la comuna
    Comuna comuna(Param, comunaWidth, comunaLength);


    //Pedestrian person(comuna, speed, deltaAngle);
    //Creación del arreglo de personas
    vector<Pedestrian> people;
    double state;
    for (int i = 0; i < N; i++){
        if (i < I){
            state = 1;
            people.push_back(*(new Pedestrian(comuna, state)));
        }
        else {
            state = 0;
            people.push_back(*(new Pedestrian(comuna, state)));
        }
    }
    ofstream exit_s("output.txt");
    ofstream *fp = &exit_s;

    comuna.setPerson(&people.front(), &people.back());
    Simulator sim(cout, comuna, delta_t, samplingTime, fp);
    sim.startSimulation();
    return a.exec();
}
