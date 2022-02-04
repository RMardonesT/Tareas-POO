#ifndef PEDESTRIAN_H
#define PEDESTRIAN_H
#include <string>
#include <QRandomGenerator>
#include <QObject>
#include "Comuna.h"

using namespace std;
class Comuna;
class Pedestrian {


private:
    double x, y, speed, angle, deltaAngle;
    double x_tPlusDelta, y_tPlusDelta;
    double time_inf;
    double state, new_state;

    int mask;
    Comuna &comuna;
    QRandomGenerator myRand; // see https://doc.qt.io/qt-5/qrandomgenerator.html


public:
    Pedestrian(Comuna &com, double state,int  mask);
    static string getStateDescription() {
        return "x, \ty";
    };
    int getStatePerson();
    int getNewStatePerson(Pedestrian person);
    string getState() const;
    int getMask();
    double getX();
    double getY();
    void computeNextState(double delta_t);
    void updateState();
    void modify(double state, int mask);
    void verifyvac();
};

#endif // PEDESTRIAN_H
