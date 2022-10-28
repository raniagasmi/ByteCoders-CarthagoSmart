

#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QSqlDatabase>
#include <QSqlQuery>
#include <QString>
#include <string>
#include <QMessageBox>
#include <QLineEdit>
#include <QSqlQueryModel>
#include <QtCharts>

QT_CHARTS_USE_NAMESPACE

 //Graphique
QChartView* MainWindow::piechart()
{

    QPieSeries *series = new QPieSeries();
    series->append("mariage", 1);
    series->append("fiancaille", 2);
    series->append("tournoi", 3);
    series->append("soiree", 4);
    series->append("remise de diplome", 5);
    series->append("anniversaire", 6);



    // Add label to 1st slice

    QPieSlice *slice0 = series->slices().at(0);
    slice0->setExploded();
    slice0->setLabelVisible();
    slice0->setPen(QPen(Qt::darkBlue, 2));
    slice0->setBrush(Qt::blue);

    // Add label, explode and define brush for 2nd slice
    QPieSlice *slice1 = series->slices().at(1);
    slice1->setExploded();
    slice1->setLabelVisible();
    slice1->setPen(QPen(Qt::darkGreen, 2));
    slice1->setBrush(Qt::green);

    // Add labels to rest of slices
    QPieSlice *slice2 = series->slices().at(2);
    slice2->setExploded();
    slice2->setLabelVisible();
    slice2->setPen(QPen(Qt::darkRed, 2));
    slice2->setBrush(Qt::red);

    QPieSlice *slice3 = series->slices().at(3);
    slice3->setExploded();
    slice3->setLabelVisible();
    slice3->setPen(QPen(Qt::darkBlue, 2));
    slice3->setBrush(Qt::blue);

    QPieSlice *slice4 = series->slices().at(4);
    slice4->setExploded();
    slice4->setLabelVisible();
    slice4->setPen(QPen(Qt::darkYellow, 2));
    slice4->setBrush(Qt::yellow);

    QPieSlice *slice5 = series->slices().at(5);
    slice5->setExploded();
    slice5->setLabelVisible();
    slice5->setPen(QPen(Qt::darkCyan, 2));
    slice5->setBrush(Qt::cyan);


    QPieSlice *slice6 = series->slices().at(6);
    slice6->setExploded();
    slice6->setLabelVisible();
    slice6->setPen(QPen(Qt::darkGray, 2));
    slice6->setBrush(Qt::gray);

    //ajouter une fonction pour  chaque slice
    series->slices().at(0)->setValue(G_PVS1());
    series->slices().at(1)->setValue(G_PVS2());
    series->slices().at(2)->setValue(G_PVS3());
    series->slices().at(3)->setValue(G_PVS4());
    series->slices().at(4)->setValue(G_PVS5());
    series->slices().at(5)->setValue(G_PVS6());


    series->slices().at(0)->setBrush(Qt::darkMagenta);
    QChart *chart = new QChart();
    chart->addSeries(series);
    chart->setTitle(" type des évenements ");



  QChartView *chartView1 = new QChartView(chart);
    chartView1->setRenderHint(QPainter::Antialiasing);
    return chartView1;
}
/*PieChart::PieChart(QDeclarativeItem *parent)
    : QDeclarativeItem(parent)
{
    // need to disable this flag to draw inside a QDeclarativeItem
    setFlag(QGraphicsItem::ItemHasNoContents, false);
}*/
int MainWindow::G_PVS1()
{

    int c22=0;
    QSqlQuery qry22;
    qry22.exec("SELECT id FROM EVENT WHERE type='mariage'");
    while(qry22.next())
    {
        c22++;
    }
    return c22;
}

int MainWindow::G_PVS2()
{
    int c21=0;
    QSqlQuery qry21;
    qry21.exec("SELECT id FROM EVENT WHERE type='fiancaille' ");
    while(qry21.next())
    {
        c21++;
    }
    return c21;
}

int MainWindow::G_PVS3()
{
    int c20=0;
    QSqlQuery qry20;
    qry20.exec("SELECT id FROM EVENT WHERE type='tournoi' ");
    while(qry20.next())
    {
        c20++;
    }
    return c20;
}

int MainWindow::G_PVS4()
{
    int c19=0;
    QSqlQuery qry19;
    qry19.exec("SELECT id FROM EVENT WHERE type='soiree' ");
    while(qry19.next())
    {
        c19++;
    }
    return c19;
}

int MainWindow::G_PVS5()
{
    int c18=0;
    QSqlQuery qry18;
    qry18.exec("SELECT id FROM EVENT WHERE type='remise de diplome' ");
    while(qry18.next())
    {
        c18++;
    }
    return c18;
}
int MainWindow::G_PVS6()
{
    int c17=0;
    QSqlQuery qry17;
    qry17.exec("SELECT id FROM EVENT WHERE type='anniversaire' ");
    while(qry17.next())
    {
        c17++;
    }
    return c17;
}








