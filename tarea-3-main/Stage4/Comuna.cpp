#include "Comuna.h"
#include <iostream>
#include "Pedestrian.h"
#include "settings.h"

//CONSTRUCTOR DE COMUNA CUANDO SE INGRESAN PARAMETROS
Comuna::Comuna( double *parameters, double width, double length): territory(0,0,width,length){
    pPerson=NULL;
    last_P =NULL;
    own_people = NULL;
    pVac=NULL;
    last_V =NULL;
    own_vacs = NULL;

    this->parameters = parameters;
    this->VacNotCreated = true;
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
void Comuna::setPerson(vector<Pedestrian> *people){

    own_people = people;

    double N = this->parameters[0];
    double m = this->parameters[9];
    double Inf = this->parameters[1];

    int mask;
    int num_maskInf = (int) round(Inf*m);         //round(I*M)
    int num_maskSus = (int) round((N-Inf)*m);  //round((N-I)*M)

    double state;

    for (int i=0; i< (int)N; i++)
     {
         state = 0;  //Por defecto state = 0

         if (i < Inf) state = 1;  //Incluye I personas con state = 1

         if(i<num_maskInf || (i >= Inf && i < Inf + num_maskSus)){mask = 1;}

         else{mask = 0;}


         people->push_back(*(new Pedestrian(*this , state, mask)));
     }
    pPerson = &people->front();
    last_P = &people->back();

}
// {N, I, I_time, comunaWidth, comunaLength, speed, delta_t, deltaAngle, d, m, p0, p1, p2,  num_vac, vac_size, vac_time;};
// {0, 1,   2   ,     3      ,     4     ,      5,     6   ,      7    , 8, 9, 10, 11, 12,    13   ,   14    ,    15 };
void Comuna::setVac(vector<Vacunatorio> *vacs){
    own_vacs = vacs;
    for (int i=0; i < parameters[13]; i++){
        own_vacs->push_back(*(new Vacunatorio(parameters[14], this->getWidth(), this->getHeight())));
    }
    pVac=&own_vacs->front();
    last_V=&own_vacs->back();
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
    return "V, \tI, \tR, \tS";//....
}

//ACTUALIZA LOS VALORES DE LAS COLUMNAS  I,R Y S
string Comuna::getState() {
    this->I=0; this->R=0; this->S=0, this->V=0;

    Pedestrian *cursor = &own_people->front();

    while(cursor != last_P + 1){
        if(cursor->getStatePerson()==1){this->I++;}
        if(cursor->getStatePerson()==0){this->S++;}
        if(cursor->getStatePerson()==2){this->R++;}
        if(cursor->getStatePerson()==3){this->V++;}
        cursor++;
    }
    return to_string(this->V)+", \t"+to_string(this->I)+", \t"+to_string(this->R)+", \t"+to_string(this->S);
}

void Comuna::restart() {

    own_people->clear();
    own_people->shrink_to_fit();
    setPerson(own_people);

    own_vacs->clear();
    own_vacs->shrink_to_fit();
    setVac(own_vacs);
    VacNotCreated = true;

    this->I = 0;
    this->R = 0;
    this->S = 0;
    this->V = 0;


}

// {N, I, I_time, comunaWidth, comunaLength, speed, delta_t, deltaAngle, d, m, p0, p1, p2,  num_vac, vac_size, vac_time;};
// {0, 1,   2   ,     3      ,     4     ,      5,     6   ,      7    , 8, 9, 10, 11, 12,    13   ,   14    ,    15 };
void Comuna::modifyParameters(double* param){


    this->parameters[0] =  *param;
    this->parameters[1] =  *(param+1);
    this->parameters[2] =  *(param+2);
    this->parameters[5] =  *(param+3);
    this->parameters[7] =  *(param+4);
    this->parameters[9] =  *(param+5);
    this->parameters[10] = *(param+6);
    this->parameters[11] = *(param+7);
    this->parameters[12] = *(param+8);
    this->parameters[8] =  *(param+9);

    cout << "this is the param: "  << *param << endl;
    cout << "this is the param: "  << *(param+1) << endl;
    cout << "this is the param: "  << *(param+2) << endl;
    cout << "this is the param: "  << *(param+3) << endl;
    cout << "this is the param: "  << *(param+4) << endl;
    cout << "this is the param: "  << *(param+5) << endl;
    cout << "this is the param: "  << *(param+6) << endl;
    cout << "this is the param: "  << *(param+7) << endl;
    cout << "this is the param: "  << *(param+8) << endl;

    this->restart();

}
