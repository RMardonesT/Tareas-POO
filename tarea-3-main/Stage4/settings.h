#ifndef SETTINGS_H
#define SETTINGS_H

#include <QDialog>
#include "Comuna.h"

namespace Ui {
class Settings;
}

class Settings : public QDialog
{
    Q_OBJECT


signals:
    double* ok_pressed(double* param);

public:
    Ui::Settings *ui;
    explicit Settings(QWidget *parent = nullptr);


    ~Settings();

private slots:
    void on_buttonBox_accepted();


private:


};

#endif // SETTINGS_H
