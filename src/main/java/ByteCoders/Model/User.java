package ByteCoders.Model;
import ByteCoders.Service.Encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;


public class User {

    private int id;
    private int cin;
    private String nom;
    private String prenom;
    private String username;
    private int  numTlfn;
    private String addEmail;
    private String mdp;

    private String urlImage;

    private Roles roleUser ;

//    private Status etatUser;

    private String confirmationCode;
    private Boolean isVerified ;





    public User(int id, int cin, String nom, String prenom, String username, int numTlfn, String addEmail, String mdp, Roles roleUser/*,String confirmationCode*/, String urlImage) throws Exception{
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.numTlfn = numTlfn;
        this.addEmail = addEmail;
        this.mdp = mdp;
        this.roleUser = roleUser;
        this.confirmationCode=VerificationCodeGenerator.generateVerificationCode();
        this.urlImage = urlImage;
        this.isVerified = false;

    }

    public User(String nom, int numTlfn, String mdp, Roles roleUser) throws Exception{
        this.nom = nom;
        this.numTlfn = numTlfn;
        this.mdp = mdp;
        this.confirmationCode=VerificationCodeGenerator.generateVerificationCode();
        this.roleUser = roleUser;
        this.isVerified = false;

    }

    public User(int cin, String nom, String prenom, String username, int numTlfn, String addEmail, String mdp, Roles roleUser/*,String confirmationCode*/, String urlImage) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.numTlfn = numTlfn;
        this.addEmail = addEmail;
        this.mdp = mdp;
        this.roleUser = roleUser;
        this.confirmationCode=VerificationCodeGenerator.generateVerificationCode();
        this.urlImage = urlImage;
       // this.isVerified = false;



    }


    public User(String nom, String prenom, String username, int numTlfn, String addEmail, String mdp, String urlImage) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.numTlfn = numTlfn;
        this.addEmail = addEmail;
        this.mdp = mdp;
        this.urlImage = urlImage;
        this.isVerified = false;


    }

    public User(int id, String username, int numTlfn, String mdp/*, String urlImage*/) {
        this.id = id;
        this.username = username;
        this.numTlfn = numTlfn;
        this.mdp = mdp;
        this.isVerified = false;

        //this.urlImage = urlImage;
    }


    public User(String addEmail,String mdp/*, String confirmationCode*/) {
        this.mdp= mdp;
        this.addEmail = addEmail;
        this.confirmationCode = VerificationCodeGenerator.generateVerificationCode();
        this.isVerified = false;

    }

    public User() {

    }

    public User(int id, String nom, String prenom,Roles roleUser) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.roleUser = roleUser;


    }

    public User(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }


   /* public User(String username , String addEmail, String mdp, int numTlfn) {
        this.username = username;
        this.numTlfn = numTlfn;
        this.mdp = mdp;
        this.addEmail = addEmail;

    }*/

    public User(String username, int numTlfn, String addEmail, String mdp, String urlImage) {
        this.username = username;
        this.numTlfn = numTlfn;
        this.mdp = mdp;
        this.addEmail = addEmail;
        this.urlImage = urlImage;
    }

    public User(String username, int numTlfn, String mdp, String addEmail) {
        this.username = username;
        this.numTlfn = numTlfn;
        this.mdp = mdp;
        this.addEmail = addEmail;
    }

    public User(String nom, String prenom, int numTlfn, String addEmail, Roles roleUser) {
        this.nom = nom;
        this.prenom = prenom;
        this.numTlfn = numTlfn;
        this.addEmail = addEmail;
        this.roleUser = roleUser;

    }



  /* public User(int i, String amal, int i1, String s, String rania123, String image, String organisateur) {
    }*/

    /* public User(int cin, String username, int numTlfn, String addEmail, String mdp, String urlImage) {
            this.cin = cin;
            this.username = username;
            this.numTlfn = numTlfn;
            this.addEmail = addEmail;
            this.mdp = mdp;
            this.urlImage = urlImage;
        }
    */

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        this.isVerified = verified;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

   public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNumTlfn(int numTlfn) {
        this.numTlfn = numTlfn;
    }

    public void setAddEmail(String addEmail) {
        this.addEmail = addEmail;
    }

    public void setMdp(String mdp) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        this.mdp = Encryption.encrypt(mdp);
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void setRoleUser(Roles roleUser) {
          this.roleUser = roleUser;
      }
/*
      public void setEtatUser(Status etatUser) {
          this.etatUser = etatUser;
      }
  */
    public int getId() {
        return id;
    }

    public int getCin() {
        return cin;
    }

    public String getUsername() {
        return username;
    }

    public int getNumTlfn() {
        return numTlfn;
    }

    public String getAddEmail() {
        return addEmail;
    }

    public String getMdp() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return Encryption.decrypt(mdp);
    }

    public String getUrlImage() {
        return urlImage;
    }

    public Roles getRoleUser() {
        return roleUser;
    }

   /* public Status getEtatUser() {
        return etatUser;
    }*/

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", cin=" + cin +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", username='" + username + '\'' +
                ", numTlfn=" + numTlfn +
                ", addEmail='" + addEmail + '\'' +
                ", mdp='" + mdp + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", roleUser=" + roleUser +
                ", confirmationCode='" + confirmationCode + '\'' +
                ", isVerified=" + isVerified +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && cin == user.cin && numTlfn == user.numTlfn && Objects.equals(nom, user.nom) && Objects.equals(prenom, user.prenom) && Objects.equals(username, user.username) && Objects.equals(addEmail, user.addEmail) && Objects.equals(mdp, user.mdp) && Objects.equals(urlImage, user.urlImage) && roleUser == user.roleUser && Objects.equals(confirmationCode, user.confirmationCode);
    }

    /*@Override
    public int hashCode() {
        return Objects.hash(id, cin, nom, prenom, username, numTlfn, addEmail, mdp, urlImage, roleUser, confirmationCode);
    }*/
}
