#ifndef GRAPH_H
#define GRAPH_H
#include <QGraphicsScene>
#include <QMainWindow>
#include <QDialog>
#include <QtCore>
#include <QtGui>
#include <QGraphicsScene>
#include <QGraphicsRectItem>
#include <QtCharts>

namespace Ui {
class graph;
}

class graph : public QMainWindow
{
    Q_OBJECT

signals:
    void start_pressed();
    void stop_pressed();

public:
    explicit graph(QWidget *parent = nullptr);
    ~graph();

public slots:
    void g_recharge();

private slots:
    void on_action_Start_triggered();

    void on_action_Stop_triggered();

private:
    Ui::graph *ui;
    QGraphicsScene *scene;
};

#endif // GRAPH_H
