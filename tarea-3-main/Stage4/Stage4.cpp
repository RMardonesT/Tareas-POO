#include "Comuna.h"
#include "Simulator.h"
#include "Pedestrian.h"
#include "graph.h"
#include "vacunatorio.h"
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
            deltaAngle, d, m, p0, p1, p2, num_vac, vac_size, vac_time;



    fin >> N >> I >> I_time;
    fin >> comunaWidth >> comunaLength;
    fin >> speed >> delta_t >> deltaAngle;
    fin >> d >> m >> p0 >> p1 >> p2;
    fin >> num_vac >> vac_size >>vac_time;


    //Se guardan los parametros del archivo en un array
    double Param[16] = {N, I, I_time, comunaWidth, comunaLength, speed, delta_t, deltaAngle, d, m, p0, p1 , p2, num_vac, vac_size, vac_time};
    double samplingTime = 1.0;


    //Creacion de la comuna
    Comuna comuna(Param, comunaWidth, comunaLength );


    //Creación del arreglo de personas e inserción en la comuna


    // {N, I, I_time, comunaWidth, comunaLength, speed, delta_t, deltaAngle, d, m, p0, p1, p2,  num_vac, vac_size, vac_time;};
    // {0, 1,   2   ,     3      ,     4     ,      5,     6   ,      7    , 8, 9, 10, 11, 12,    13   ,   14    ,    15 };


    vector<Pedestrian> people;
    comuna.setPerson(&people);
    vector<Vacunatorio> vacunatorios;
    comuna.setVac(&vacunatorios);

    ofstream exit_s("output.txt");
    ofstream *fp = &exit_s;

    Simulator sim(cout, comuna, delta_t, samplingTime, fp);


    graph g;
    Settings settings;
    g.settings = &settings;

    QObject::connect(&g, SIGNAL(start_pressed()), &sim, SLOT(startSimulation()));
    QObject::connect(&g, SIGNAL(stop_pressed()), &sim, SLOT(stopSimulation()));

    QObject::connect(&settings, SIGNAL(ok_pressed(double*)),&comuna, SLOT(modifyParameters(double*)));

    QChartView *chartView = new QChartView(sim.chart);

    //QObject::connect(&sim, SIGNAL(data_change()),chartView, SLOT(resize()));

    chartView->setRenderHint(QPainter::Antialiasing);
    g.setCentralWidget(chartView);
    g.resize(800,600);
    g.show();
    //sim.startSimulation();
    return a.exec();

}
