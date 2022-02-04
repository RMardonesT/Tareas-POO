#ifndef SIMULATOR_H
#define SIMULATOR_H
#include "Comuna.h"
#include <ostream>
#include <QTimer>
class Simulator: public QObject { // By inheriting from QObject,
    //our class can use signal and slot mechanism Qt provides.
    Q_OBJECT
private:
    Comuna &comuna;
    ostream &out;
    double t;
    double delta_t, samplingTime;
    QTimer * timer;  // see https://doc.qt.io/qt-5.12/qtimer.html
public:
    Simulator (ostream &output, Comuna &comuna, double delta_t, double samplingTime);
    ~Simulator();
    void printStateDescription() const;
    void printState(double t) const;
    void startSimulation();
public slots:
    void simulateSlot();
};

#endif // SIMULATOR_H
