package ByteCoders.Model;
import lombok.*;


@Getter
@Setter
@ToString

public class Paiement {

     int id_paiement;
     User id_user;
     Double montant;
    String date;
    Facture id_facture;
    String mode_paiement;


    public Paiement(int id_paiement, User id_user, Double montant, String date, Facture id_facture, String mode_paiement) {
        this.id_paiement = id_paiement;
        this.id_user = id_user;
        this.montant = montant;
        this.date = date;
        this.id_facture = id_facture;
        this.mode_paiement = mode_paiement;

    }
    public Paiement(User id_user, Double montant, String date, Facture id_facture, String mode_paiement) {
        this.id_user = id_user;
        this.montant = montant;
        this.date = date;
        this.id_facture = id_facture;
        this.mode_paiement = mode_paiement;
    }
}
