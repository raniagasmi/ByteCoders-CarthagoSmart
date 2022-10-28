/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.9.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <ActiveQt/QAxWidget>
#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QComboBox>
#include <QtWidgets/QGroupBox>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QTabWidget>
#include <QtWidgets/QTableView>
#include <QtWidgets/QTextEdit>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>
#include "webaxwidget.h"

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralWidget;
    QTabWidget *tabWidget;
    QWidget *tab;
    QGroupBox *groupBox_3;
    QTextEdit *textEdit_5;
    QTextEdit *textEdit_6;
    QLineEdit *id;
    QTextEdit *textEdit_7;
    QLineEdit *nbr_invi;
    QTextEdit *textEdit_8;
    QLineEdit *localisation;
    QPushButton *pushButton_ajouter;
    QPushButton *pushButton_modifier;
    QComboBox *type_event;
    QGroupBox *groupBox_2;
    QLineEdit *id_2;
    QLabel *label;
    QPushButton *pushButton_supprimer;
    QWidget *tab_2;
    QLineEdit *lineEdit_3;
    QLabel *label_3;
    QLabel *label_4;
    QPushButton *pushButton_rech_id;
    QPushButton *pushButton_rech_type;
    QLabel *label_5;
    QLineEdit *lineEdit_5;
    QPushButton *pushButton_rech_localisation;
    QTableView *tableView_2;
    QComboBox *type_event_2;
    QWidget *tab_3;
    WebAxWidget *WebBrowser;
    QWidget *tab_4;
    QTableView *tableView;
    QPushButton *pushButton_triec;
    QPushButton *pushButton_tried;
    QLabel *label_2;
    QPushButton *pushButton_pdf;
    QWidget *tab_5;
    QWidget *widget;
    QWidget *tab_6;
    QPushButton *pushButton_notification;
    QTextEdit *textEdit_2;
    QTextEdit *textEdit;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QStringLiteral("MainWindow"));
        MainWindow->resize(1154, 720);
        MainWindow->setStyleSheet(QStringLiteral("background-color: rgb(199, 203, 255);"));
        centralWidget = new QWidget(MainWindow);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        tabWidget = new QTabWidget(centralWidget);
        tabWidget->setObjectName(QStringLiteral("tabWidget"));
        tabWidget->setGeometry(QRect(90, 80, 971, 571));
        tab = new QWidget();
        tab->setObjectName(QStringLiteral("tab"));
        groupBox_3 = new QGroupBox(tab);
        groupBox_3->setObjectName(QStringLiteral("groupBox_3"));
        groupBox_3->setGeometry(QRect(10, 20, 571, 381));
        textEdit_5 = new QTextEdit(groupBox_3);
        textEdit_5->setObjectName(QStringLiteral("textEdit_5"));
        textEdit_5->setGeometry(QRect(40, 120, 241, 31));
        textEdit_6 = new QTextEdit(groupBox_3);
        textEdit_6->setObjectName(QStringLiteral("textEdit_6"));
        textEdit_6->setGeometry(QRect(40, 220, 241, 31));
        id = new QLineEdit(groupBox_3);
        id->setObjectName(QStringLiteral("id"));
        id->setGeometry(QRect(360, 70, 191, 31));
        textEdit_7 = new QTextEdit(groupBox_3);
        textEdit_7->setObjectName(QStringLiteral("textEdit_7"));
        textEdit_7->setGeometry(QRect(40, 70, 241, 41));
        nbr_invi = new QLineEdit(groupBox_3);
        nbr_invi->setObjectName(QStringLiteral("nbr_invi"));
        nbr_invi->setGeometry(QRect(360, 220, 101, 31));
        textEdit_8 = new QTextEdit(groupBox_3);
        textEdit_8->setObjectName(QStringLiteral("textEdit_8"));
        textEdit_8->setGeometry(QRect(40, 170, 241, 31));
        localisation = new QLineEdit(groupBox_3);
        localisation->setObjectName(QStringLiteral("localisation"));
        localisation->setGeometry(QRect(360, 170, 191, 31));
        pushButton_ajouter = new QPushButton(groupBox_3);
        pushButton_ajouter->setObjectName(QStringLiteral("pushButton_ajouter"));
        pushButton_ajouter->setGeometry(QRect(90, 290, 141, 51));
        pushButton_modifier = new QPushButton(groupBox_3);
        pushButton_modifier->setObjectName(QStringLiteral("pushButton_modifier"));
        pushButton_modifier->setGeometry(QRect(330, 290, 121, 51));
        type_event = new QComboBox(groupBox_3);
        type_event->setObjectName(QStringLiteral("type_event"));
        type_event->setGeometry(QRect(372, 120, 171, 31));
        groupBox_2 = new QGroupBox(tab);
        groupBox_2->setObjectName(QStringLiteral("groupBox_2"));
        groupBox_2->setGeometry(QRect(650, 260, 271, 261));
        id_2 = new QLineEdit(groupBox_2);
        id_2->setObjectName(QStringLiteral("id_2"));
        id_2->setGeometry(QRect(50, 90, 91, 31));
        label = new QLabel(groupBox_2);
        label->setObjectName(QStringLiteral("label"));
        label->setGeometry(QRect(20, 50, 141, 21));
        pushButton_supprimer = new QPushButton(groupBox_2);
        pushButton_supprimer->setObjectName(QStringLiteral("pushButton_supprimer"));
        pushButton_supprimer->setGeometry(QRect(60, 190, 141, 41));
        tabWidget->addTab(tab, QString());
        tab_2 = new QWidget();
        tab_2->setObjectName(QStringLiteral("tab_2"));
        lineEdit_3 = new QLineEdit(tab_2);
        lineEdit_3->setObjectName(QStringLiteral("lineEdit_3"));
        lineEdit_3->setGeometry(QRect(172, 21, 131, 31));
        label_3 = new QLabel(tab_2);
        label_3->setObjectName(QStringLiteral("label_3"));
        label_3->setGeometry(QRect(50, 25, 111, 31));
        label_4 = new QLabel(tab_2);
        label_4->setObjectName(QStringLiteral("label_4"));
        label_4->setGeometry(QRect(60, 60, 111, 31));
        pushButton_rech_id = new QPushButton(tab_2);
        pushButton_rech_id->setObjectName(QStringLiteral("pushButton_rech_id"));
        pushButton_rech_id->setGeometry(QRect(320, 17, 51, 31));
        pushButton_rech_type = new QPushButton(tab_2);
        pushButton_rech_type->setObjectName(QStringLiteral("pushButton_rech_type"));
        pushButton_rech_type->setGeometry(QRect(360, 60, 51, 31));
        label_5 = new QLabel(tab_2);
        label_5->setObjectName(QStringLiteral("label_5"));
        label_5->setGeometry(QRect(60, 100, 141, 31));
        lineEdit_5 = new QLineEdit(tab_2);
        lineEdit_5->setObjectName(QStringLiteral("lineEdit_5"));
        lineEdit_5->setGeometry(QRect(220, 100, 131, 31));
        pushButton_rech_localisation = new QPushButton(tab_2);
        pushButton_rech_localisation->setObjectName(QStringLiteral("pushButton_rech_localisation"));
        pushButton_rech_localisation->setGeometry(QRect(370, 100, 51, 31));
        tableView_2 = new QTableView(tab_2);
        tableView_2->setObjectName(QStringLiteral("tableView_2"));
        tableView_2->setGeometry(QRect(55, 161, 531, 331));
        type_event_2 = new QComboBox(tab_2);
        type_event_2->setObjectName(QStringLiteral("type_event_2"));
        type_event_2->setGeometry(QRect(180, 60, 171, 31));
        tabWidget->addTab(tab_2, QString());
        tab_3 = new QWidget();
        tab_3->setObjectName(QStringLiteral("tab_3"));
        WebBrowser = new WebAxWidget(tab_3);
        WebBrowser->setControl(QStringLiteral("{8856F961-340A-11D0-A96B-00C04FD705A2}"));
        WebBrowser->setObjectName(QStringLiteral("WebBrowser"));
        WebBrowser->setProperty("focusPolicy", QVariant::fromValue(Qt::StrongFocus));
        WebBrowser->setProperty("geometry", QVariant(QRect(120, 40, 691, 461)));
        tabWidget->addTab(tab_3, QString());
        tab_4 = new QWidget();
        tab_4->setObjectName(QStringLiteral("tab_4"));
        tableView = new QTableView(tab_4);
        tableView->setObjectName(QStringLiteral("tableView"));
        tableView->setGeometry(QRect(40, 20, 631, 431));
        pushButton_triec = new QPushButton(tab_4);
        pushButton_triec->setObjectName(QStringLiteral("pushButton_triec"));
        pushButton_triec->setGeometry(QRect(782, 67, 131, 41));
        pushButton_tried = new QPushButton(tab_4);
        pushButton_tried->setObjectName(QStringLiteral("pushButton_tried"));
        pushButton_tried->setGeometry(QRect(782, 127, 131, 41));
        label_2 = new QLabel(tab_4);
        label_2->setObjectName(QStringLiteral("label_2"));
        label_2->setGeometry(QRect(50, 460, 101, 20));
        pushButton_pdf = new QPushButton(tab_4);
        pushButton_pdf->setObjectName(QStringLiteral("pushButton_pdf"));
        pushButton_pdf->setGeometry(QRect(50, 480, 81, 51));
        tabWidget->addTab(tab_4, QString());
        tab_5 = new QWidget();
        tab_5->setObjectName(QStringLiteral("tab_5"));
        widget = new QWidget(tab_5);
        widget->setObjectName(QStringLiteral("widget"));
        widget->setGeometry(QRect(30, 30, 911, 501));
        tabWidget->addTab(tab_5, QString());
        tab_6 = new QWidget();
        tab_6->setObjectName(QStringLiteral("tab_6"));
        pushButton_notification = new QPushButton(tab_6);
        pushButton_notification->setObjectName(QStringLiteral("pushButton_notification"));
        pushButton_notification->setGeometry(QRect(70, 110, 271, 51));
        textEdit_2 = new QTextEdit(tab_6);
        textEdit_2->setObjectName(QStringLiteral("textEdit_2"));
        textEdit_2->setGeometry(QRect(70, 170, 601, 261));
        tabWidget->addTab(tab_6, QString());
        textEdit = new QTextEdit(centralWidget);
        textEdit->setObjectName(QStringLiteral("textEdit"));
        textEdit->setGeometry(QRect(100, 20, 601, 41));
        MainWindow->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(MainWindow);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 1154, 26));
        MainWindow->setMenuBar(menuBar);
        mainToolBar = new QToolBar(MainWindow);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        MainWindow->addToolBar(Qt::TopToolBarArea, mainToolBar);

        retranslateUi(MainWindow);

        tabWidget->setCurrentIndex(5);


        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QApplication::translate("MainWindow", "Gestion des Employes", Q_NULLPTR));
        groupBox_3->setTitle(QString());
        textEdit_5->setHtml(QApplication::translate("MainWindow", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n"
"<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"
"p, li { white-space: pre-wrap; }\n"
"</style></head><body style=\" font-family:'MS Shell Dlg 2'; font-size:7.8pt; font-weight:400; font-style:normal;\">\n"
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">type \303\251venement</p></body></html>", Q_NULLPTR));
        textEdit_6->setHtml(QApplication::translate("MainWindow", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n"
"<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"
"p, li { white-space: pre-wrap; }\n"
"</style></head><body style=\" font-family:'MS Shell Dlg 2'; font-size:7.8pt; font-weight:400; font-style:normal;\">\n"
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">Nombre d'invitation</p></body></html>", Q_NULLPTR));
        textEdit_7->setHtml(QApplication::translate("MainWindow", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n"
"<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"
"p, li { white-space: pre-wrap; }\n"
"</style></head><body style=\" font-family:'MS Shell Dlg 2'; font-size:7.8pt; font-weight:400; font-style:normal;\">\n"
"<p align=\"center\" style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-size:12pt; font-weight:600;\">ID</span></p></body></html>", Q_NULLPTR));
        textEdit_8->setHtml(QApplication::translate("MainWindow", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n"
"<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"
"p, li { white-space: pre-wrap; }\n"
"</style></head><body style=\" font-family:'MS Shell Dlg 2'; font-size:7.8pt; font-weight:400; font-style:normal;\">\n"
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">localisation</p></body></html>", Q_NULLPTR));
        pushButton_ajouter->setText(QApplication::translate("MainWindow", "Ajouter", Q_NULLPTR));
        pushButton_modifier->setText(QApplication::translate("MainWindow", "Modifier", Q_NULLPTR));
        type_event->clear();
        type_event->insertItems(0, QStringList()
         << QApplication::translate("MainWindow", "mariage", Q_NULLPTR)
         << QApplication::translate("MainWindow", "anniversaire", Q_NULLPTR)
         << QApplication::translate("MainWindow", "tournoi", Q_NULLPTR)
         << QApplication::translate("MainWindow", "soiree", Q_NULLPTR)
         << QApplication::translate("MainWindow", "remise de diplome", Q_NULLPTR)
         << QApplication::translate("MainWindow", "fiancaille", Q_NULLPTR)
        );
        groupBox_2->setTitle(QApplication::translate("MainWindow", "Suppression", Q_NULLPTR));
        label->setText(QApplication::translate("MainWindow", "Entrer l'ID", Q_NULLPTR));
        pushButton_supprimer->setText(QApplication::translate("MainWindow", "Supprimer", Q_NULLPTR));
        tabWidget->setTabText(tabWidget->indexOf(tab), QApplication::translate("MainWindow", "GESTION", Q_NULLPTR));
        label_3->setText(QApplication::translate("MainWindow", "Chercher par ID", Q_NULLPTR));
        label_4->setText(QApplication::translate("MainWindow", "Chercher par Type", Q_NULLPTR));
        pushButton_rech_id->setText(QApplication::translate("MainWindow", "OK", Q_NULLPTR));
        pushButton_rech_type->setText(QApplication::translate("MainWindow", "OK", Q_NULLPTR));
        label_5->setText(QApplication::translate("MainWindow", "Chercher par localisation", Q_NULLPTR));
        pushButton_rech_localisation->setText(QApplication::translate("MainWindow", "OK", Q_NULLPTR));
        type_event_2->clear();
        type_event_2->insertItems(0, QStringList()
         << QApplication::translate("MainWindow", "mariage", Q_NULLPTR)
         << QApplication::translate("MainWindow", "anniversaire", Q_NULLPTR)
         << QApplication::translate("MainWindow", "tournoi", Q_NULLPTR)
         << QApplication::translate("MainWindow", "soiree", Q_NULLPTR)
         << QApplication::translate("MainWindow", "remise de diplome", Q_NULLPTR)
         << QApplication::translate("MainWindow", "fiancaille", Q_NULLPTR)
        );
        tabWidget->setTabText(tabWidget->indexOf(tab_2), QApplication::translate("MainWindow", "RECHERCHE", Q_NULLPTR));
        tabWidget->setTabText(tabWidget->indexOf(tab_3), QApplication::translate("MainWindow", "MAP", Q_NULLPTR));
        pushButton_triec->setText(QApplication::translate("MainWindow", "Trie_croissant", Q_NULLPTR));
        pushButton_tried->setText(QApplication::translate("MainWindow", "Trie_d\303\251croissant", Q_NULLPTR));
        label_2->setText(QApplication::translate("MainWindow", "Convertir en pdf", Q_NULLPTR));
        pushButton_pdf->setText(QApplication::translate("MainWindow", "PDF", Q_NULLPTR));
        tabWidget->setTabText(tabWidget->indexOf(tab_4), QApplication::translate("MainWindow", "AFFICHAGE", Q_NULLPTR));
        tabWidget->setTabText(tabWidget->indexOf(tab_5), QApplication::translate("MainWindow", "STATISTIQUE", Q_NULLPTR));
        pushButton_notification->setText(QApplication::translate("MainWindow", "Taper une notification", Q_NULLPTR));
        tabWidget->setTabText(tabWidget->indexOf(tab_6), QApplication::translate("MainWindow", "notification", Q_NULLPTR));
        textEdit->setHtml(QApplication::translate("MainWindow", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n"
"<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"
"p, li { white-space: pre-wrap; }\n"
"</style></head><body style=\" font-family:'MS Shell Dlg 2'; font-size:7.8pt; font-weight:400; font-style:normal;\">\n"
"<p align=\"center\" style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-size:12pt; color:#df3d0c;\">Gestion d'\303\251venement</span></p></body></html>", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
