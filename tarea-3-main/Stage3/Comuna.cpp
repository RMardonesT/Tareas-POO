#include "Comuna.h"
#include <iostream>
#include "Pedestrian.h"

//CONSTRUCTOR DE COMUNA CUANDO SE INGRESAN PARAMETROS
Comuna::Comuna(double *parameters, double width, double length): territory(0,0,width,length){
    pPerson=NULL;
    last_P =NULL;
    this->parameters = parameters;
    I = 0;
    R = 0;
    S = 0;

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

                        int auxNextState = pPerson1->getNewStatePerson(*pPerson2); //person
                        if (auxNextState == 1) {
                            break;
                        }
                    }
                pPerson2++;
                }
            }
            else {
                    pPerson1->getNewStatePerson(*pPerson1);//
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
string Comuna::getState() {
    this->I=0; this->R=0; this->S=0;
    Pedestrian *pPerson1 = pPerson;
    while(pPerson1 != last_P + 1){
        if(pPerson1->getStatePerson()==1){this->I++;}
        if(pPerson1->getStatePerson()==0){this->S++;}
        if(pPerson1->getStatePerson()==2){this->R++;}
        pPerson1++;
    }
    return to_string(this->I)+", \t"+to_string(this->R)+", \t"+to_string(this->S);
}

void Comuna::restart() {
    int PEAK = (int)parameters[0];
    int mask;
    int num_maskInf = (int) round(parameters[0]*parameters[9]);         //round(I*M)
    int num_maskSus = (int) round((PEAK-parameters[1])*parameters[9]);  //round((N-I)*M)
    Pedestrian * pPerson_aux = pPerson;
    //vector<Pedestrian> people;
    double state;
    this->I = 0;
    this->R = 0;
    this->S = 0;
    for (int i=0; i< PEAK; i++)
    {
         state = 0;  //Por defecto state = 0

         if (i < parameters[1]) state = 1;  //Incluye I personas con state = 1

         if(i<num_maskInf || (i >= parameters[1] && i < parameters[1] + num_maskSus)){mask = 1;}

         else{mask = 0;}

         pPerson_aux->modify(state,mask);
         pPerson_aux++;


         //people.push_back(*(new Pedestrian(*this, state, mask)));
    }
    //setPerson(&people.front(), &people.back());

}
