#include "settings.h"
#include "ui_settings.h"
#include <iostream>

Settings::Settings(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::Settings)
{
    ui->setupUi(this);
    //connect(ui->M, SIGNAL(valueChanged()), this, SLOT(value(int)));



}



Settings::~Settings()
{
    delete ui;
}

void Settings::on_buttonBox_accepted()
{



   double parameters[10];



   parameters[0] = (double)ui->newN->value();
   parameters[1] = (double)ui->newI->value();
   parameters[2] = (double)ui->newI_time->value();
   parameters[3] = (double)ui->newSpeed->value();
   parameters[4] = (double)ui->newAngle->value();
   parameters[5] = (double)ui->M->value()/100;
   parameters[6] = (double)ui->P0->value()/100;
   parameters[7] = (double)ui->P1->value()/100;
   parameters[8] = (double)ui->P1->value()/100;
   parameters[9] = (double)ui->newD->value();


   emit ok_pressed(&parameters[0]);

}


