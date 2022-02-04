#include "Comuna.h"
#include "Simulator.h"
#include "Pedestrian.h"
#include "graph.h"
#include <QApplication>
#include <iostream>
#include <fstream>
#include <vector>
using namespace std;
int main(int argc, char *argv[])
{
    QApplication a(argc, argv);

    if (argc != 2){
        cout << "Usage: stage3 <configurationFile.txt>" << endl;
        exit(-1);
    }
    ifstream fin(argv[1]);
    double N, I, I_time, comunaWidth,comunaLength,speed, delta_t,
            deltaAngle, d, m, p0, p1, p2;//, num_vac, vac_size, vac_time;



    fin >> N >> I >> I_time;
    fin >> comunaWidth >> comunaLength;
    fin >> speed >> delta_t >> deltaAngle;
    fin >> d >> m >> p0 >> p1 >> p2;



    //Se guardan los parametros del archivo en un array
    double Param[13] = {N, I, I_time, comunaWidth, comunaLength, speed, delta_t, deltaAngle, d, m, p0, p1 , p2};
    double samplingTime = 1.0;


    //Creacion de la comuna
    Comuna comuna(Param, comunaWidth, comunaLength);


    //Pedestrian person(comuna, speed, deltaAngle);
    //Creación del arreglo de personas
    /*
    vector<Pedestrian> people;
    double state;
    for (int i = 0; i < N; i++){
        if (i < I){
            state = 1;
            people.push_back(*(new Pedestrian(comuna, state, 1)));
        }
        else {
            state = 0;
            people.push_back(*(new Pedestrian(comuna, state, 1)));
        }
    }
*/

    //Creación del arreglo de personas e inserción en la comuna

    // {N, I, I_time, comunaWidth, comunaLength, speed, delta_t, deltaAngle, d, m, p0, p1, p2};
    // {0, 1,   2   ,     3      ,     4     ,      5,     6   ,      7    , 8, 9, 10, 11, 12 };

    int PEAK = (int)N;
    int mask;
    int num_maskInf = (int) round(I*m);         //round(I*M)
    int num_maskSus = (int) round((PEAK-I)*m);  //round((N-I)*M)

    vector<Pedestrian> people;
    double state;

    for (int i=0; i< PEAK; i++)
     {
         state = 0;  //Por defecto state = 0

         if (i < I) state = 1;  //Incluye I personas con state = 1

         if(i<num_maskInf || (i >= I && i < I + num_maskSus)){mask = 1;}

         else{mask = 0;}


         people.push_back(*(new Pedestrian(comuna , state, mask)));
     }

    ofstream exit_s("output.txt");
    ofstream *fp = &exit_s;

    comuna.setPerson(&people.front(), &people.back());

    Simulator sim(cout, comuna, delta_t, samplingTime, fp);
    graph g;
    QObject::connect(&g, SIGNAL(start_pressed()), &sim, SLOT(startSimulation()));
    QObject::connect(&g, SIGNAL(stop_pressed()), &sim, SLOT(stopSimulation()));

    QChartView *chartView = new QChartView(sim.chart);

    //QObject::connect(&sim, SIGNAL(data_change()),chartView, SLOT(resize()));

    chartView->setRenderHint(QPainter::Antialiasing);
    g.setCentralWidget(chartView);
    g.resize(800,600);
    g.show();
    //sim.startSimulation();
    return a.exec();
}
