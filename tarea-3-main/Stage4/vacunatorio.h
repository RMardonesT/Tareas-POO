#ifndef VACUNATORIO_H
#define VACUNATORIO_H
#include <QRandomGenerator>
#include <QRect>

class Vacunatorio
{

private:
    QRect vac_terr;
    QRandomGenerator myRand;

    double VacSize;
    double x,y;

public:
    Vacunatorio(double size , double com_W, double com_H );
    double getVacX();
    double getVacY();
};

#endif // VACUNATORIO_H
