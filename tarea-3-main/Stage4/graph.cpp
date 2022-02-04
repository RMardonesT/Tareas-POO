#include "graph.h"
#include "settings.h"
#include "ui_graph.h"
#include <iostream>

graph::graph(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::graph)
{
    ui->setupUi(this);
    ui->action_Start->setEnabled(true);
    ui->action_Stop->setEnabled(false);

    //scene = new QGraphicsScene(this);
    //ui->graphicsView->setScene(scene);
   // connect(ui->action_Start, SIGNAL(pressed()), this, SLOT(Start()));
    //connect(ui->action_Stop, SIGNAL(pressed()), this, SLOT(Stop()));
}

graph::~graph()
{
    delete ui;
}

void graph::g_recharge()
{

}

void graph::on_action_Start_triggered()
{
    emit start_pressed();
    ui->action_Start->setEnabled(false);
    ui->action_Stop->setEnabled(true);
    ui->menuSettings->setEnabled(false);
    //std::cout << "start" << std::endl;
}


void graph::on_action_Stop_triggered()
{
    emit stop_pressed();
    //std::cout << "stop" << std::endl;
    ui->action_Start->setEnabled(true);
    ui->action_Stop->setEnabled(false);
    ui->menuSettings->setEnabled(true);
}


void graph::on_action_parameters_triggered()
{
 this->settings->show();


}

