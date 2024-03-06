package ByteCoders.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
public class UserMolka {
    int id_user;
    private String num_compte;
    private String adresse;

    private List<Facture> factures;

    public UserMolka(int id_user, String num_compte, String adresse) {
        this.id_user = id_user;
        this.num_compte = num_compte;
        this.adresse = adresse;

    }

    public static UserMolka retrieve() {
        return null;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNum_compte() {
        return num_compte;
    }

    public void setNum_compte(String num_compte) {
        this.num_compte = num_compte;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


}
