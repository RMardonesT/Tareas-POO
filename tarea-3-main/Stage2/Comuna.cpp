#include "Comuna.h"
#include <iostream>


//CONSTRUCTOR DE COMUNA CUANDO SE INGRESAN PARAMETROS
Comuna::Comuna(double *parameters, double width, double length): territory(0,0,width,length){
    pPerson=NULL;
    last_P =NULL;
    this->parameters = parameters;
}

//ENTREGA ANCHO DE LA COMUNA
double Comuna::getWidth() const {
    return territory.width();
}

//ENTREGA ALTURA DE LA COMUNA
double Comuna::getHeight() const {
    return territory.height();//.....
}

   //INGRESA LAS PERSONAS DEL ARREGLO arr_people A LA COMUNA RECIBIENDO LA CABEZA Y LA COLA DEL ARREGLO PARA USARLOS COMO REFERENCIA DE INTERACION
void Comuna::setPerson(Pedestrian *people_f, Pedestrian *people_b){
   this->pPerson = people_f;//....
   this->last_P = people_b;
}

//CALCULA LA POSICION SIGUIENTE DE CADA INDIVIDUO DE LA COMUNA
void Comuna::computeNextState (double delta_t) {
   Pedestrian *pPerson1 = pPerson;

   while(pPerson1 != last_P+1){
       pPerson1->computeNextState(delta_t);
       pPerson1++;
   }
}

//ACTUALIZA EL ESTADO DE CADA INDIVIDUO DE LA COMUNA BASADO EN LA INTERACCIÓN ENTRE LOS INDIVIDUOS
void Comuna::interaction (){
    Pedestrian* pPerson1 = pPerson;
    while(pPerson1 != (last_P + 1)){
        Pedestrian* pPerson2 = pPerson;
        if (pPerson1->getStatePerson() !=2) {
            if (pPerson1->getStatePerson() == 0){  //si la persona es susceptible
                while(pPerson2 != (last_P + 1)) {
                    if (pPerson2->getStatePerson() == 1) {
                        int auxNextState = pPerson1->getNewStatePerson(*pPerson2, 50, 0.5);//d - p0
                        if (auxNextState == 1) {
                            break;
                        }
                    }
                pPerson2++;
                }
            }
            else {
                pPerson1->getNewStatePerson(*pPerson1, 50, 0.5);//
            }

        }
        pPerson1++;
    }
}

void Comuna::updateState () {
    Pedestrian *pPerson1 = pPerson;
    while(pPerson1 != last_P+1){
        pPerson1->updateState();
        pPerson1++;
    }
}

// TITULO COLUMNA STATE
string Comuna::getStateDescription(){
    return "I, \tR, \tS";//....
}

//ACTUALIZA LOS VALORES DE LAS COLUMNAS  I,R Y S
string Comuna::getState() const{
    int I=0, R=0, S=0;
    Pedestrian *pPerson1 = pPerson;
    while(pPerson1 != last_P + 1){
        if(pPerson1->getStatePerson()==1){I++;}
        if(pPerson1->getStatePerson()==0){S++;}
        if(pPerson1->getStatePerson()==2){R++;}
        pPerson1++;
    }
    return to_string(I)+", \t"+to_string(R)+", \t"+to_string(S);
}
