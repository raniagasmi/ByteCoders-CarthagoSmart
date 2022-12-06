#ifndef EVENT_H
#define EVENT_H
#include<QString>
#include<QSqlQuery>
#include<QSqlQueryModel>
#include <QFrame>
#include<QFileDialog>
#include <QtCharts/QtCharts>
#include <QtCharts/QPieSeries>
#include <QtCharts/QChartView>
#include <QtCharts/QPieSeries>
#include <QtCharts/QPieSlice>
#include "QString"
#include<QDebug>

class Event
{
    int id;
    QString type_event;
    int nbr_invi;
    QString localisation;

public:

    Event();
    Event(int,QString,int,QString);
   //getters
    int get_id();
    QString get_type_event();
    QString get_localisation();
    int get_nbr_invi();
    //setters
    void set_id(int x);
    void set_type_event(QString t);
    void set_localisation(QString l);
    void set_nbr_invi(int n);
    //ajouter afficher supprimer modifier(methode de base )
    bool ajouter();
    QSqlQueryModel * afficher();
    bool supprimer(int);
    bool modifier();
    //métier de base

     QSqlQueryModel * tri_decroissant();
     QSqlQueryModel * tri_croissant();

     QSqlQueryModel* recherche_id(QString );
     QSqlQueryModel *recherche_type(QString);
     QSqlQueryModel *recherche_localisation(QString);

     QSqlQueryModel* statistiques();
     QChartView* piechart();

    // QSqlQueryModel* G_PVS1();



};
#endif // EVENT_H
