#ifndef COMUNA_H
#define COMUNA_H
#include "Pedestrian.h"
#include "vacunatorio.h"
#include "settings.h"
#include <QRect>
#include <string>
#include <QtGui>
#include <QObject>
using namespace std;
class Comuna :public QObject {
    Q_OBJECT

private:
    Pedestrian *pPerson;
    Pedestrian *last_P; //Puntero al ultimo individuo de people.
    Vacunatorio *pVac;
    Vacunatorio *last_V; //Puntero al ultimo individuo de people.
    QRect territory; // Alternatively: double width, length;



public:
    Comuna( double *param, double width=1000, double length=1000  );
    double I, R, S, V;
    bool VacNotCreated;

    double getWidth() const;
    double getHeight() const;

    vector<Pedestrian> *own_people;
    vector<Vacunatorio> *own_vacs;
    void setPerson(vector<Pedestrian> *people);
    void setVac(vector<Vacunatorio> *vacs);
    void computeNextState (double delta_t);
    void updateState ();
    void interaction (); //metodo que genera interacción entre todos los individuos
    static string getStateDescription();
    string getState() ;
    double *parameters;
    void restart();

public slots:
    void modifyParameters(double* param);


 };

#endif // COMUNA_H
