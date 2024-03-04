package ByteCoders.Model;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FactureEauModel {

    private SimpleIntegerProperty id_facture;
    private SimpleStringProperty libelle_facture;
    private SimpleStringProperty date_facture;
    private SimpleStringProperty date_ech_facture;
    private SimpleDoubleProperty montant_facture;
    private SimpleStringProperty type_facture;
    private SimpleBooleanProperty payee_facture;


    public FactureEauModel(int id_facture, String libelle_facture, String date_facture, String date_ech_facture, double montant_facture, String type_facture, boolean payee){
        this.id_facture = new SimpleIntegerProperty(id_facture);
        this.libelle_facture = new SimpleStringProperty(libelle_facture);
        this.date_facture = new SimpleStringProperty(date_facture);
        this.date_ech_facture = new SimpleStringProperty(date_ech_facture);
        this.montant_facture = new SimpleDoubleProperty(montant_facture);
        this.type_facture = new SimpleStringProperty(type_facture);
        this.payee_facture = new SimpleBooleanProperty(payee);
    }

    public FactureEauModel() {
    }

    public int getId_facture() {
        return id_facture.get();
    }

    public SimpleIntegerProperty id_factureProperty() {
        return id_facture;
    }

    public void setId_facture(int id_facture) {
        this.id_facture.set(id_facture);
    }

    public String getLibelle_facture() {
        return libelle_facture.get();
    }

    public SimpleStringProperty libelle_factureProperty() {
        return libelle_facture;
    }

    public void setLibelle_facture(String libelle_facture) {
        this.libelle_facture.set(libelle_facture);
    }

    public String getDate_facture() {
        return date_facture.get();
    }

    public SimpleStringProperty date_factureProperty() {
        return date_facture;
    }

    public void setDate_facture(String date_facture) {
        this.date_facture.set(date_facture);
    }

    public String getDate_ech_facture() {
        return date_ech_facture.get();
    }

    public SimpleStringProperty date_ech_factureProperty() {
        return date_ech_facture;
    }

    public void setDate_ech_facture(String date_ech_facture) {
        this.date_ech_facture.set(date_ech_facture);
    }

    public double getMontant_facture() {
        return montant_facture.get();
    }

    public SimpleDoubleProperty montant_factureProperty() {
        return montant_facture;
    }

    public void setMontant_facture(double montant_facture) {
        this.montant_facture.set(montant_facture);
    }

    public String getType_facture() {
        return type_facture.get();
    }

    public SimpleStringProperty type_factureProperty() {
        return type_facture;
    }

    public void setType_facture(String type_facture) {
        this.type_facture.set(type_facture);
    }

    public boolean isPayee_facture() {
        return payee_facture.get();
    }

    public SimpleBooleanProperty payee_factureProperty() {
        return payee_facture;
    }

    public void setPayee_facture(boolean payee_facture) {
        this.payee_facture.set(payee_facture);
    }
}
