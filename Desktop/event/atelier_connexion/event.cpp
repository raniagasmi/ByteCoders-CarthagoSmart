#include "Event.h"
#include <QMessageBox>
#include <QSqlQueryModel>
#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "Event.h"
#include <QString>
#include <QSqlQuery>
#include <QSqlQueryModel>
#include <QMessageBox>

Event::Event()
{
    id=0;
    nbr_invi=0;
    type_event="";
    localisation="";
}

Event::Event(int x,QString t,int n,QString l)
{
    this->id=x;
    this->type_event=t;
    this->localisation=l;
    this->nbr_invi=n;
}

//getters
int Event::get_id(){return id;}
QString Event::get_type_event(){return type_event;}
QString Event::get_localisation(){return localisation;}
int Event::get_nbr_invi(){return nbr_invi ;}
//setters

void Event::set_id(int x){this->id=x;}
void Event::set_type_event(QString t){this->type_event=t;}
void Event::set_localisation(QString l){this->localisation=l;}
void Event::set_nbr_invi(int n){this->nbr_invi=n;}

bool Event::ajouter(){
    QSqlQuery query ;

    QString id_ =QString::number(id);
    QString nbr_invi_ =QString::number(nbr_invi);

    query.prepare("INSERT INTO EVENT (id, type_event,localisation, nbr_invi)"
                  "VALUES (:id, :type_event,:localisation , :nbr_invi)");

    query.bindValue(0,id);
    query.bindValue(1,type_event);
    query.bindValue(2,localisation);
    query.bindValue(3,nbr_invi);

    //envoie de requete pour l'executer
    return query.exec();

}
QSqlQueryModel * Event::afficher()
{

   QSqlQueryModel *model=new QSqlQueryModel();

   model->setQuery("select * from EVENT");
   model->setHeaderData(0,Qt::Horizontal, QObject::tr("id"));
   model->setHeaderData(0,Qt::Horizontal, QObject::tr("type_event"));
   model->setHeaderData(0,Qt::Horizontal, QObject::tr("localisation"));
   model->setHeaderData(0,Qt::Horizontal, QObject::tr("nbr_invi"));

   return model;
}

bool Event::supprimer(int id)
{
    QSqlQuery query;
    QString id_ =QString::number(id);
    query.prepare("Delete from EVENT where id=:id");
    query.bindValue(":id",id_);
    return query.exec();

}

bool Event::modifier()
{
    QSqlQuery query ;

    QString id_ =QString::number(id);
    QString num_ =QString::number(nbr_invi);

    QSqlDatabase::database().transaction();
    query.prepare("update EVENT set id=:id,type_event=:type_event,localisation=:localisation,nbr_invi=:num where id=:id");
                  query.bindValue(":id",id_);
                  query.bindValue("type_event:",type_event);
                  query.bindValue("localisation:",localisation);
                  query.bindValue("nbr_invi:",nbr_invi);
QSqlDatabase::database().commit();
                  return query.exec();
}


QSqlQueryModel * Event::tri_decroissant()
{
   QSqlQueryModel *model=new QSqlQueryModel();
   model->setQuery("SELECT * FROM Event ORDER BY nbr_invi DESC ");
   model->setHeaderData(0,Qt::Horizontal,QObject::tr("id"));
   model->setHeaderData(1,Qt::Horizontal,QObject::tr("type_event"));
   model->setHeaderData(2,Qt::Horizontal,QObject::tr("localisation"));
   model->setHeaderData(3,Qt::Horizontal,QObject::tr("nbr_invi"));
   return model;
}
QSqlQueryModel * Event::tri_croissant()
{
    QSqlQueryModel *model=new QSqlQueryModel();
    model->setQuery("SELECT * FROM Event ORDER BY nbr_invi ASC ");
    model->setHeaderData(0,Qt::Horizontal,QObject::tr("id"));
    model->setHeaderData(1,Qt::Horizontal,QObject::tr("type_event"));
    model->setHeaderData(2,Qt::Horizontal,QObject::tr("localisation"));
    model->setHeaderData(3,Qt::Horizontal,QObject::tr("nbr_invi"));

    return model;
 }
QSqlQueryModel *Event::recherche_id(QString rech)
{
    QSqlQueryModel *model= new QSqlQueryModel();
    model->setQuery("SELECT * FROM EVENT WHERE ID LIKE'%"+rech+"%'");
    return model;


}
QSqlQueryModel *Event::recherche_type(QString rech)
{
    QSqlQueryModel *model= new QSqlQueryModel();
    model->setQuery("SELECT * FROM EVENT WHERE TYPE_EVENT LIKE'%"+rech+"%'");
    return model;
}
QSqlQueryModel *Event::recherche_localisation(QString rech)
{
    QSqlQueryModel *model= new QSqlQueryModel();
    model->setQuery("SELECT * FROM EVENT WHERE LOCALISATION LIKE'%"+rech+"%'");
    return model;
}

