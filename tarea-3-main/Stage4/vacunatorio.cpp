#include "vacunatorio.h"


Vacunatorio::Vacunatorio(double size , double com_W, double com_H )
{
    myRand = QRandomGenerator::securelySeeded();
    x =  myRand.generateDouble()*com_W;
    y =  myRand.generateDouble()*com_H;

    this->VacSize = size;

    vac_terr = QRect(x,y,VacSize,VacSize);


}


double Vacunatorio::getVacX(){return this->x;}
double Vacunatorio::getVacY(){return this->y;}
