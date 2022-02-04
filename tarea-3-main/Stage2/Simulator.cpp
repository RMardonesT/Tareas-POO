#include "Simulator.h"
Simulator::Simulator(ostream &output, Comuna &com,
                     double delta, double st, ofstream *fp): comuna(com), out(output)/*.....*/{
    t=0;
    delta_t=delta;
    samplingTime=st;
    own_fp = fp;
    timer = new QTimer(this);
    connect(timer, SIGNAL(timeout()),
            this, SLOT(simulateSlot()));
}
Simulator::~Simulator(){
    delete timer;
}

// IMPRIME LOS  TITULOS LAS COLUMNAS ASOCIADA A LA SIMULACION  T  I R S
void Simulator::printStateDescription() const {
    string s=  "Time,\t"+ comuna.getStateDescription();//....
    out << s << endl;
}
void Simulator::printState(double t) const{
    string s = to_string((int)round(t)) + ",\t";
    s+= comuna.getState();//...
    out << s << endl;
}
void Simulator::startSimulation(){
    printStateDescription();
    string s=  "Time,\t"+ comuna.getStateDescription();
    *own_fp << s << endl;
    t=0;
    printState(t);
    string line = to_string((int)t) + ",\t";
    line+= comuna.getState();//...
    *own_fp << line << endl;
    timer->start(1000);
}
void Simulator::simulateSlot(){
    double nextStop=t+samplingTime;

    while(t<(nextStop-delta_t*0.001)) {
       comuna.interaction();
       comuna.computeNextState(delta_t); // compute its next state based on current global state
       comuna.updateState();  // update its state
       t+=delta_t;
    }
    printState(t);
    string line = to_string((int)t) + ",\t";
    line+= comuna.getState();//...
    *own_fp << line << endl;
}
