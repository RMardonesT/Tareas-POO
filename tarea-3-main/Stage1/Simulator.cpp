#include "Simulator.h"
#include <iostream>
Simulator::Simulator(ostream &output, Comuna &com,
                     double delta, double st): comuna(com), out(output)/*.....*/{
    t=0;
    delta_t=delta;
    samplingTime=st;
    timer = new QTimer(this);
    connect(timer, SIGNAL(timeout()),
            this, SLOT(simulateSlot()));
}
Simulator::~Simulator(){
    delete timer;
}
void Simulator::printStateDescription() const {
    string s=  "Time,\t"+ comuna.getStateDescription();//....
    out << s << endl;
}
void Simulator::printState(double t) const{
    string s = to_string(t) + ",\t";
    s+= comuna.getState();//...
    out << s << endl;
}
void Simulator::startSimulation(){
    printStateDescription();
    t=0;
    printState(t);
    timer->start(1000);
}
void Simulator::simulateSlot(){
    double nextStop=t+samplingTime;
    while(t<(nextStop-delta_t*0.001)) {
       comuna.computeNextState(delta_t); // compute its next state based on current global state
       comuna.updateState();  // update its state
       t+=delta_t;
    }
    printState(t);


}
