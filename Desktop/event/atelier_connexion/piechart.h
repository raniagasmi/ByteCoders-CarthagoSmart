/*#ifndef PIECHART_H
#define PIECHART_H

#include <QDialog>

#include <QSqlDatabase>
#include <QSqlError>
#include <QSqlQuery>

#include <QtCharts/QChartView>
#include <QtCharts/QBarSeries>
#include <QtCharts/QBarSet>
#include <QtCharts/QLegend>
#include <QtCharts/QBarCategoryAxis>
#include <QtCharts/QHorizontalStackedBarSeries>
#include <QtCharts/QLineSeries>
#include <QtCharts/QCategoryAxis>
#include <QtCharts/QPieSeries>
#include <QtCharts/QPieSlice>

#include <QWidget>

class piechartwidget :public QWidget
   {
        Q_OBJECT
        public:
        explicit piechartwidget(QWidget *parent=0);
protected:
    void paintEvent(QPaintEvent *);
signals:
public slots:
};

 PIECHART_H /*
