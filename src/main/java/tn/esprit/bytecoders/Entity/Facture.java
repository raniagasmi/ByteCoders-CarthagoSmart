package tn.esprit.bytecoders.Entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Facture {
    int id_facture;
    private String libelle;
    private Date date;
    private Date date_ech;
    private Double montant;
    private TypeFacture type;
    private boolean estPayee; //true si facture payée
    private int id_user;

    public Facture(String libelle, Date date, Date date_ech, Double montant, TypeFacture type, boolean estPayee, int id_user) {
        this.libelle = libelle;
        this.date = date;
        this.date_ech = date_ech;
        this.montant = montant;
        this.type = type;
        this.estPayee = estPayee;
        this.id_user = id_user;
    }

    public Facture(){

    }

}
