 #include "mainwindow.h"
#include "ui_mainwindow.h"

#include "QSqlQuery"
#include "Event.h"
#include <QString>
#include <QSqlQuery>
#include <QSqlQueryModel>
#include <QMessageBox>
#include <QAxWidget>
#include <QtCharts/QPieSlice>

#include "qaxbase.h"
#include <qwidget.h>

#include <QMetaObject>
#include <QPrinter>
#include <QPrintDialog>
#include <QMouseEvent>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
      , ui (new Ui::MainWindow)
{
    ui->setupUi(this);


    ui->tableView->setModel(event.afficher());
    ui->id->setValidator(new QIntValidator(0,99999999,this));
    ui->nbr_invi->setValidator(new QIntValidator(0,99999999,this));


  popUp = new PopUp();

    QSettings settings(QSettings::IniFormat, QSettings::UserScope,
                       QCoreApplication::organizationName(), QCoreApplication::applicationName());

    ui->WebBrowser->dynamicCall("Navigate(const QString&)", "https://www.google.com/maps/place/ESPRIT/@36.9016729,10.1713215,15z");
/*
    int ret=A.connect_arduino(); // lancer la connexion à arduino
                  switch(ret){
                  case(0):qDebug()<< "arduino is available and connected to : "<< A.getarduino_port_name();
                      break;
                  case(1):qDebug() << "arduino is available but not connected to :" <<A.getarduino_port_name();
                     break;
                  case(-1):qDebug() << "arduino is not available";
                  }
                   QObject::connect(A.getserial(),SIGNAL(readyRead()),this,SLOT(update_label())); // permet de lancer
                   //le slot update_label suite à la reception du signal readyRead (reception des données).
                   //A.write_to_arduino("0");
                   A.read_from_arduino();
                   QObject::connect(A.getserial(),SIGNAL(readyRead()),this,SLOT(update_label2())); // permet de lancer
                   //le slot update_label suite à la reception du signal readyRead (reception des données).
                   //A.write_to_arduino("0");
                   A.read_from_arduino();
                   */

}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_pushButton_ajouter_clicked()
{
    //recuperation des info dans les4 champs
    int id=ui->id->text().toInt();
    QString type_event=ui->type_event-> currentText ();
    int nbr_invi=ui->nbr_invi->text().toInt();
    QString localisation=ui->localisation->text();
    Event e(id,type_event,nbr_invi,localisation);
    bool test=e.ajouter();
    if(test)
    {
        ui->tableView->setModel(e.afficher());
        QMessageBox::information(nullptr,QObject::tr("ok"),
                                 QObject::tr("ajout effectué \n"
                                             "Click Cancel to exist ."),QMessageBox::Cancel);
    }else
    {
        QMessageBox::critical(nullptr,QObject::tr(" not ok"),
                                 QObject::tr("ajout non effectué \n"
                                             "Click Cancel to exist ."),QMessageBox::Cancel);
    }



}

void MainWindow::on_pushButton_supprimer_clicked()
{
    int id=ui->id_2->text().toInt();
    Event e1;
    bool test=e1.supprimer(id);
    ui->tableView->setModel(e1.afficher());

    if (test)
    {

        QMessageBox::information(nullptr,QObject::tr("ok"),
                                 QObject::tr("suppression effectué \n"
                                             "Click Cancel to exist ."),QMessageBox::Cancel);

    }
    else
          QMessageBox::critical(nullptr, QObject::tr("no"),
                      QObject::tr("Suppression failed.\n"
                                  "Click Cancel to exit."), QMessageBox::Cancel);

}



void MainWindow::on_pushButton_modifier_clicked()
{

    int id=ui->id->text().toInt();
    QString type_event=ui->type_event-> currentText();
    QString localisation=ui->localisation->text();
    int nbr_invi=ui->nbr_invi->text().toInt();
    Event e (id,type_event,nbr_invi,localisation);


       bool test=e.modifier( );
       if (test)
          {

      ui->tableView->setModel(e.afficher());
              QMessageBox::critical(nullptr,QObject::tr("ok"),
                                       QObject::tr("Modification effectué \n"
                                                   "Click Cancel to exist ."),QMessageBox::Cancel);

          }
          else
                QMessageBox::critical(nullptr, QObject::tr("non"),
                            QObject::tr("Modification failed.\n"
                                        "Click Cancel to exit."), QMessageBox::Cancel);

}

void MainWindow::on_pushButton_triec_clicked()
{
    Event e;
    ui->tableView->setModel(e.tri_croissant());
    ui->tableView->setModel((e.afficher()));
    bool test=e.tri_croissant();
    if(test)
    {
        ui->tableView->setModel(e.tri_croissant());

                    QMessageBox::information(nullptr,QObject::tr("ok"),
                                             QObject::tr("tri croissante effectué \n"
                                                         "Click Cancel to exist ."),QMessageBox::Cancel);

                }
                else
                      QMessageBox::critical(nullptr, QObject::tr("nonnnn"),
                                  QObject::tr("tri croissante failed.\n"
                                              "Click Cancel to exit."), QMessageBox::Cancel);
            }




void MainWindow::on_pushButton_tried_clicked()
{
    Event e;
    ui->tableView->setModel(e.tri_decroissant());
    ui->tableView->setModel((e.afficher()));
    bool test=e.tri_decroissant();
    if(test)
    {
        ui->tableView->setModel(e.tri_decroissant());

                    QMessageBox::information(nullptr,QObject::tr("ok"),
                                             QObject::tr("tri decroissante effectué \n"
                                                         "Click Cancel to exist ."),QMessageBox::Cancel);

                }
                else
                      QMessageBox::critical(nullptr, QObject::tr("nonnnn"),
                                  QObject::tr("tri decroissante failed.\n"
                                              "Click Cancel to exit."), QMessageBox::Cancel);
            }







void MainWindow::on_pushButton_pdf_clicked()
{
    QPdfWriter fichier_pdf("C:\\Users\\LENOVO\\Desktop\\gestion évenement\\évenement.pdf");


                   QPainter painter(&fichier_pdf);
                   int i = 4000;
                          painter.setPen(Qt::red);
                          painter.setFont(QFont("Time New Roman", 25));
                          painter.drawText(3000,1400,"Liste Des évenements");
                          painter.setPen(Qt::black);
                          painter.setFont(QFont("Time New Roman", 15));
                          painter.drawRect(100,100,9400,2500);
                          painter.drawRect(100,3000,9400,500);
                          painter.setFont(QFont("Time New Roman", 9));
                          painter.drawText(400,3300,"Identifiant");
                          painter.drawText(1350,3300,"type évenement");
                          painter.drawText(3400,3300,"nbr_invi");
                          painter.drawText(2200,3300,"localisation");



                          QSqlQuery query;
                          query.prepare("select * from Event");
                          query.exec();
                          while (query.next())
                          {
                              painter.drawText(400,i,query.value(0).toString());
                              painter.drawText(1350,i,query.value(1).toString());
                              painter.drawText(2300,i,query.value(2).toString());
                              painter.drawText(3400,i,query.value(3).toString());



                             i = i + 350;
                          }
                          QMessageBox::information(this, QObject::tr("PDF Enregistré!"),
                          QObject::tr("PDF Enregistré.\n" "Click Cancel to exit."), QMessageBox::Cancel);
}
void MainWindow::on_pushButton_rech_id_clicked()
{
    Event e;
    QString id=ui->lineEdit_3->text();
    ui->tableView_2->setModel(e.recherche_id(id));
}
void MainWindow::on_pushButton_rech_type_clicked()
{
    Event e;
    QString type_event=ui->type_event_2->currentText();
    ui->tableView_2->setModel(e.recherche_type(type_event));
}
void MainWindow::on_pushButton_rech_localisation_clicked()
{
    Event e;
    QString localisation=ui->lineEdit_5->text();
    ui->tableView_2->setModel(e.recherche_localisation(localisation));
}









void MainWindow::on_tabWidget_2_tabBarclicked(int index)
{
    qDeleteAll(ui->tabWidget->widget(0)->children());
    QWidget* piestats = new QWidget(this);
    piestats = piechart();
    QGridLayout* layout1 = new QGridLayout(this);
    layout1->addWidget(piestats, 0, 0); // Top-Left
    layout1->addWidget(NULL, 0, 1); // Top-Right
    layout1->addWidget(NULL, 1, 0); // Bottom-Left
    layout1->addWidget(NULL, 1, 1); // Bottom-Right
    ui->tab_7->setLayout(layout1);

}



void MainWindow::on_tableView_activated(const QModelIndex &index)
{
    QString val=ui->tableView_2->model()->data(index).toString();
             QSqlQuery qry;
             qry.prepare("select * from EVENT where"
                         " id='"+val+"' or type_event='"+val+"' or localisation='"+val+"' or nbr_invi='"+val+"'  ");
             if(qry.exec())
               {while (qry.next())
              {
                    ui->id->text().toInt();
                    ui->type_event-> currentText();
                    ui->localisation->text();
                    ui->nbr_invi->text().toInt();
               }
           }
}



void MainWindow::on_pushButton_notification_clicked()
{
    popUp->setPopupText(ui->textEdit_2->toPlainText());

    popUp->show();
}
