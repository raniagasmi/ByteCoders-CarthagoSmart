#ifndef MAINWINDOW_H
#define MAINWINDOW_H
#include "notification.h"

#include <QMainWindow>
#include <QMetaObject>
#include <QPainter>

#include <QtCharts/QPieSlice>
#include <QtWidgets/QApplication>
 #include <QtWidgets/QMainWindow>
 #include <QtCharts/QChartView>
 #include <QtCharts/QPieSeries>
#include <QMainWindow>
#include "Event.h"
#include "arduino.h"


QT_CHARTS_USE_NAMESPACE
QT_BEGIN_NAMESPACE
namespace Ui {class MainWindow;}
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
     explicit MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

     int G_PVS1();
     int G_PVS2();
     int G_PVS3();
     int G_PVS4();
     int G_PVS5();
     int G_PVS6();


     QChartView* piechart();


private slots:

     void on_pushButton_ajouter_clicked();

     void on_pushButton_supprimer_clicked();

     void on_pushButton_modifier_clicked();

     void on_pushButton_pdf_clicked();

     void on_pushButton_triec_clicked();
     void on_pushButton_tried_clicked();

      void on_pushButton_rech_id_clicked();
      void on_pushButton_rech_type_clicked();
      void on_pushButton_rech_localisation_clicked();



      void on_tableView_activated(const QModelIndex &index);

       void on_tabWidget_2_tabBarclicked(int  index );

       void on_pushButton_notification_clicked();

       //void browse();

private:
    Ui::MainWindow *ui;
    Event event;
     //arduino A;
    PopUp *popUp;
};

#endif // MAINWINDOW_H
