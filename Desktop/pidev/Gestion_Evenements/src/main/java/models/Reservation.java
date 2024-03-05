package models;

public class Reservation {
    int id_res;
    String nom_event;
    String email;
    int numero;
    int nbr_place;

    public Reservation(int id_res, String nom_event, String email, int numero, int nbr_place) {
        this.id_res = id_res;
        this.nom_event = nom_event;
        this.email = email;
        this.numero = numero;
        this.nbr_place = nbr_place;
    }

    public Reservation(String nom_event, String email, int numero, int nbr_place) {
        this.nom_event = nom_event;
        this.email = email;
        this.numero = numero;
        this.nbr_place = nbr_place;
    }

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "nom_event='" + nom_event + '\'' +
                ", email='" + email + '\'' +
                ", numero=" + numero +
                ", nbr_place=" + nbr_place +
                '}';
    }
}

