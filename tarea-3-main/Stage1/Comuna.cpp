#include "Comuna.h"
Comuna::Comuna(double width, double length): territory(0,0,width,length){
    pPerson=NULL;
}
double Comuna::getWidth() const {
    return territory.width();
}
double Comuna::getHeight() const {
    return territory.height();//.....
}
void Comuna::setPerson(Pedestrian & person){
   this->pPerson = &person;//....
}
void Comuna::computeNextState (double delta_t) {
   this->pPerson->computeNextState(delta_t);//...
}
void Comuna::updateState () {
   this->pPerson->updateState();//....
}
string Comuna::getStateDescription(){
    return "Coor x, \tCoor y ";//....
}
string Comuna::getState() const{
    return this->pPerson->getState();//...
}
