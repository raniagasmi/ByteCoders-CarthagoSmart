package models;

import javafx.beans.property.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Event {
    private  int id ;
    private  String nom_event ;
    private  String categorie ;
    private  String date;
    private  String heure ;
    private  String lieu ;
    private int capacite ;
    private  Float cout ;

    public Event(int id, String nom_event, String categorie, String date, String heure, String lieu, int capacite, Float cout) {
        this.id = id;
        this.nom_event = nom_event;
        this.categorie = categorie;
        this.date = date;
        this.heure = heure;
        this.lieu = lieu;
        this.capacite = capacite;
        this.cout = cout;
    }

    public Event( String nom_event, String categorie, String date, String heure, String lieu, int capacite, Float cout) {

        this.nom_event = nom_event;
        this.categorie = categorie;
        this.date = date;
        this.heure = heure;
        this.lieu = lieu;
        this.capacite = capacite;
        this.cout = cout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public Float getCout() {
        return cout;
    }

    public void setCout(Float cout) {
        this.cout = cout;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", nom_event='" + nom_event + '\'' +
                ", categorie='" + categorie + '\'' +
                ", date=" + date +
                ", heure=" + heure +
                ", capacite=" + capacite +
                ", cout=" + cout +
                ", lieu='" + lieu + '\'' +
                '}';
    }
}
