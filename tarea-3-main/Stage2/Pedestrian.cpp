#include <QtMath> // for M_PI and functions, see https://doc.qt.io/qt-5/qtmath.html
#include <string>
#include "Comuna.h"
#include "Pedestrian.h"
#include <cmath>
#include "Stage2.cpp"

Pedestrian::Pedestrian (Comuna &com, double state): comuna(com)/*....*/{
    this->comuna = com;

    this->speed = this->comuna.parameters[5]*(0.9+0.2*myRand.generateDouble());//
    this->deltaAngle =  this->comuna.parameters[8]*(0.9+0.2*myRand.generateDouble());//

    myRand = QRandomGenerator::securelySeeded();
    angle = myRand.generateDouble()*2*M_PI;
    x = myRand.generateDouble()*comuna.getWidth();
    y = myRand.generateDouble()*comuna.getHeight();
    switch((int) state){
    case 0:{
        this->state=0;
        this->time_inf=0.0;
        break;
    }
    case 1:{
        this->state=1;
        this->time_inf = myRand.generateDouble()*( this->comuna.parameters[2]*0.9);//
        break;
    }
    default: break;
    }
    this->new_state=this->state;
}


//CALCULA LA SIGUIENTE POSICION DEL INDIVIDUO CADA DELTA_T
void Pedestrian::computeNextState(double delta_t) {
    double r=myRand.generateDouble();
    angle+=deltaAngle*(1-2*r);
    x_tPlusDelta=x+speed*qCos(angle)*delta_t;
    y_tPlusDelta=y+speed*qSin(angle)*delta_t;

    if(x_tPlusDelta < 0){   // rebound logic
        x_tPlusDelta=-x_tPlusDelta;
        angle=M_PI-angle;
    }

    if(y_tPlusDelta < 0){
        y_tPlusDelta=-y_tPlusDelta;
        angle=2*M_PI-angle;
    }

    if( x_tPlusDelta > comuna.getWidth()){
        x_tPlusDelta=2*comuna.getWidth()-x_tPlusDelta;
        angle=M_PI-angle;
    }

    if(y_tPlusDelta > comuna.getHeight()){
        y_tPlusDelta=2*comuna.getHeight()-y_tPlusDelta;
        angle=2*M_PI-angle;
    }

//...
}

//ENTREGA EL ESTADO ACTUAL DEL INDIVIDUO
int Pedestrian::getStatePerson() {return state;}




/*CALCULA EL NUEVO ESTADO DE UN INDIVIDUO SUSCEPTIBLE
* @param person2: persona con la que se esta comparando la ubicación para el calculo de distancia entre ellos
* @param d: Distancia límite para posibilidad de contagio
* @param P: Probabilidad de que el individuo se contagie de el individuo person2

/*Si el individuo es susceptible, se actualiza su estado de infección asumiendo que person2
esta infectado. (Se verifica en la parte de update_comuna)*/

int Pedestrian::getNewStatePerson(Pedestrian person, double d, double P){
    double DELTA_T =  this->comuna.parameters[6];//
    double I_TIME =  this->comuna.parameters[2];//
    new_state = state;

    if((state == 0) && !(this==&person)){
        double auxD = pow(this->x - person.getX(),2) + pow(this->y - person.getY(),2);
        double powD = d*d;
        if (auxD < powD) {
            if (myRand.generateDouble() < P){
                new_state = 1;
            }
        }
    }
    else if (state == 1){
        if (time_inf + DELTA_T < I_TIME){
            time_inf+=DELTA_T;
        }
        else
            new_state = 2;
    }
    return new_state;

}
 // ENTREGAN LA POSICION DEL INDIVIDUO
double Pedestrian::getX() {return x;}
double Pedestrian::getY() {return y;}


//ACTUALIZA LA POSICION Y ESTADOS ACTUALES  DEL INDIVIDUO A LOS CALCULADA EN computeNextState
void Pedestrian::updateState(){
    x=x_tPlusDelta;
    y=y_tPlusDelta;
    state = new_state;
}
