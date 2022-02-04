#ifndef COMUNA_H
#define COMUNA_H
#include "Pedestrian.h"
#include <QRect>
#include <string>
using namespace std;
class Comuna {
private:
    Pedestrian *pPerson;
    Pedestrian *last_P; //Puntero al ultimo individuo de people.
    QRect territory; // Alternatively: double width, length;


public:
    Comuna(double *param, double width=1000, double length=1000);
    double I, R, S;
    double getWidth() const;
    double getHeight() const;
    void setPerson(Pedestrian *people_f, Pedestrian *people_b);
    void computeNextState (double delta_t);
    void updateState ();
    void interaction (); //metodo que genera interacción entre todos los individuos
    static string getStateDescription();
    string getState() ;
    double *parameters;
    void restart();

 };

#endif // COMUNA_H
