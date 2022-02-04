#ifndef SIMULATOR_H
#define SIMULATOR_H
#include "Comuna.h"
#include <ostream>
#include <QTimer>
#include <QObject>
#include <QtCharts>
#include <fstream>

QT_CHARTS_BEGIN_NAMESPACE
class QAreaSeries;
class QValueAxis;
QT_CHARTS_END_NAMESPACE

QT_CHARTS_USE_NAMESPACE

class Simulator: public QObject { // By inheriting from QObject,
    //our class can use signal and slot mechanism Qt provides.
    Q_OBJECT
private:
    Comuna &comuna;
    ostream &out;
    double t;
    double delta_t, samplingTime;
    QTimer * timer;  // see https://doc.qt.io/qt-5.12/qtimer.html
    QLineSeries *seriesO, *seriesI, *seriesR, *seriesS, *seriesV;
    QAreaSeries *m_seriesI, *m_seriesR, *m_seriesS, *m_seriesV;
    QStringList m_titles;
    QValueAxis *m_axisX;
    QValueAxis *m_axisY;
    ofstream *own_fp;
signals:
    void data_change();

public:
    Simulator (ostream &output, Comuna &comuna, double delta_t, double samplingTime, ofstream *fp);
    ~Simulator();
    QChart *chart;
    void printStateDescription() const;
    void printState(double t) const;
    //void startSimulation();
    void updateSeries(double t);
public slots:
    void simulateSlot();
    void startSimulation();
    void stopSimulation();
};

#endif // SIMULATOR_H
