#include "Simulator.h"
Simulator::Simulator(ostream &output, Comuna &com,
                     double delta, double st, ofstream *fp): comuna(com), out(output)/*.....*/{
    t=0;
    delta_t=delta;
    samplingTime=st;
    timer = new QTimer(this);
    connect(timer, SIGNAL(timeout()),
            this, SLOT(simulateSlot()));

    seriesO = new QLineSeries();
    *seriesO << QPointF(0.0, 0.0);
    seriesV = new QLineSeries();
    *seriesV << QPointF(0.0, 0.0);
    seriesI = new QLineSeries();
    *seriesI << QPointF(0.0, comuna.parameters[1]);
    seriesR = new QLineSeries();
    *seriesR << QPointF(0.0, comuna.parameters[1]);
    seriesS = new QLineSeries();
    *seriesS << QPointF(0.0, comuna.parameters[0]);


    m_seriesV = new QAreaSeries(seriesO, seriesV);
    m_seriesV->setName("Vacunados");
    m_seriesI = new QAreaSeries(seriesV, seriesI);
    m_seriesI->setName("Infectados");
    m_seriesR = new QAreaSeries(seriesI, seriesR);
    m_seriesR->setName("Recuperados");
    m_seriesS = new QAreaSeries(seriesR, seriesS);
    m_seriesS->setName("Susceptibles");
    chart = new QChart();

    chart->addSeries(m_seriesI);
    chart->addSeries(m_seriesR);
    chart->addSeries(m_seriesS);
    chart->addSeries(m_seriesV);
    chart->setTitle("Grafico Simulacion Pandemia Stage 3");
    chart->createDefaultAxes();
    chart->setAnimationOptions(QChart::AllAnimations);
    chart->axes(Qt::Horizontal).first()->setRange(0,20);
    //chart->axes(Qt::Vertical).first()->setRange(0,comuna.parameters[0]);
    own_fp = fp;
}
Simulator::~Simulator(){
    delete timer;
    delete chart;
    delete m_seriesS;
    delete m_seriesR;
    delete m_seriesI;
    delete m_seriesV;
    delete seriesS;
    delete seriesR;
    delete seriesI;
    delete seriesO;
    delete seriesV;
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

void Simulator::updateSeries(double t2){
    this->seriesO->append(QPointF(t2,0));
    this->seriesV->append(QPointF(t2,comuna.V));
    this->seriesI->append(QPointF(t2,comuna.V+comuna.I));
    this->seriesR->append(QPointF(t2,comuna.V+comuna.I+comuna.R));
    this->seriesS->append(QPointF(t2,comuna.V+comuna.I+comuna.R+comuna.S));
    this->chart->axes(Qt::Horizontal).first()->setRange(0,t2+1);

    this->chart->update();

}
void Simulator::startSimulation(){
    printStateDescription();
    own_fp->open("output.txt", std::ofstream::out | std::ofstream::trunc);
    own_fp->close();
    own_fp->open("output.txt");
    string s=  "Time,\t"+ comuna.getStateDescription();
    *own_fp << s << endl;

    t=0;

    printState(t);
    string line = to_string((int)t) + ",\t";
    line+= comuna.getState();//...
    *own_fp << line << endl;

    this->seriesO->clear();
    this->seriesI->clear();
    this->seriesR->clear();
    this->seriesS->clear();
    this->seriesV->clear();
    *seriesO << QPointF(0.0, 0.0);
    *seriesV << QPointF(0.0, 0.0);
    *seriesI << QPointF(0.0, comuna.parameters[1]);
    *seriesR << QPointF(0.0, comuna.parameters[1]);
    *seriesS << QPointF(0.0, comuna.parameters[0]);
    this->chart->axes(Qt::Vertical).first()->setRange(0,this->comuna.parameters[0]);
    timer->start(1000);
}
void Simulator::stopSimulation(){
    timer->stop();
    comuna.restart();
}
void Simulator::simulateSlot(){
    double nextStop=t+samplingTime;

    while(t<(nextStop-delta_t*0.001)) {
       comuna.interaction();
       comuna.computeNextState(delta_t); // compute its next state based on current global state
       comuna.updateState();  // update its state

       if (t > this->comuna.parameters[15] && this->comuna.VacNotCreated)
       {
           this->comuna.VacNotCreated = false;

       }
       t+=delta_t;
    }
    printState(t);
    updateSeries(t);
    string line = to_string((int)round(t)) + ",\t";
    line+= comuna.getState();//...
    *own_fp << line << endl;
    emit data_change();
}
