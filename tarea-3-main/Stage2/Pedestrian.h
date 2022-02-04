#ifndef PEDESTRIAN_H
#define PEDESTRIAN_H
#include <string>
#include <QRandomGenerator>
using namespace std;
class Comuna;
class Pedestrian {
private:
    double x, y, speed, angle, deltaAngle;
    double x_tPlusDelta, y_tPlusDelta;
    double time_inf;
    double state, new_state;
    Comuna &comuna;
    QRandomGenerator myRand; // see https://doc.qt.io/qt-5/qrandomgenerator.html


public:
    Pedestrian(Comuna &com, double state);
    static string getStateDescription() {
        return "x, \ty";
    };
    int getStatePerson();
    int getNewStatePerson(Pedestrian person, double d, double P);
    string getState() const;
    double getX();
    double getY();
    void computeNextState(double delta_t);
    void updateState();
};

#endif // PEDESTRIAN_H
