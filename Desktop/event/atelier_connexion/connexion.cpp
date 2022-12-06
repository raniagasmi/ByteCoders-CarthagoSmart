#include "connexion.h"
#include<QDebug>
//test tutoriel Git
Connection::Connection()
{

}

bool Connection::createconnection()
{bool test=false;
QSqlDatabase db = QSqlDatabase::addDatabase("QODBC");
db.setDatabaseName("source_projet2A");
db.setUserName("cherif");//inserer nom de l'utilisateur
db.setPassword("cherif02");//inserer mot de passe de cet utilisateur

if (db.open()) test=true;
return test;
}
void Connection::closeconnection(){db.close();}







