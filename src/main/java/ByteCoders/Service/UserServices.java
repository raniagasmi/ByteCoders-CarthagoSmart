package ByteCoders.Service;

import ByteCoders.Model.Roles;
import ByteCoders.Model.User;
import ByteCoders.Service.MyDB;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;




public class UserServices implements IService<User> {

    private final Connection cnx;

    public UserServices() {

        cnx = MyDB.getInstance().getCnx();

    }



   /* public static boolean verif_cred(User u) throws SQLException {
        Connection cnx = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            cnx = MyDB.getInstance().getCnx();
            String query = "SELECT * FROM `user` WHERE username = ? AND mdp = ?"; // Utilisez des requêtes préparées
            statement = cnx.prepareStatement(query);
            statement.setString(1, u.getUsername());
            statement.setString(2, hashPassword(u.getMdp())); // Hash du mot de passe avant la comparaison
            resultSet = statement.executeQuery();
            return resultSet.next();
        } finally {
            // Fermeture des ressources
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) {}
            try { if (statement != null) statement.close(); } catch (Exception e) {}
            try { if (cnx != null) cnx.close(); } catch (Exception e) {}
        }
    }*/


    private static User currentUtilisateur;
    private static String currentAdresse = "";
    public static User getCurrentUtilisateur() {
        return currentUtilisateur;
    }
    public static String getCurrentAdresse(){
        return currentAdresse;
    }

    public static void setCurrentAdresse(String adresse){
        currentAdresse = adresse;
    }




    @Override
    public void addUser(User user) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Connection cnx = null;
        PreparedStatement stm = null;

        //try {
            cnx = MyDB.getInstance().getCnx();
            String req = "INSERT INTO `user`(`cin`,`nom`,`prenom`, `username`, `numTlfn`, `addEmail`, `mdp`, `roleUser`/*,`confirmationCode`*/,`urlImage`) VALUES (?,?,?,?,?,?,?,?,?)";

            stm = cnx.prepareStatement(req);
            stm.setInt(1, user.getCin());
            stm.setString(2, user.getNom());
            stm.setString(3, user.getPrenom());
            stm.setString(4, user.getUsername());
            stm.setInt(5, user.getNumTlfn());
            stm.setString(6, user.getAddEmail());
            stm.setString(7,  Encryption.encrypt(user.getMdp()));
            stm.setString(8, user.getRoleUser().toString());
        //  stm.setString(9, user.getConfirmationCode());
            stm.setString(9, user.getUrlImage());

            stm.executeUpdate();

    }


    @Override
    public void updateUser(User user) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String req = "UPDATE `user` SET username = ?, numTlfn= ?, addEmail=?, mdp=?,urlImage=? /*, urlImage =?*/ /*,`etatUser`=?*/ WHERE id= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        //ps.setInt(1, user.getCin());
        ps.setString(1, user.getUsername());
        ps.setInt(2, user.getNumTlfn());
        ps.setString(3, user.getAddEmail());

        ps.setString(4, Encryption.encrypt(user.getMdp()));
        ps.setString(5,user.getUrlImage());
        // ps.setString(4, user.getUrlImage());
        //ps.setString(6, user.getRoleUser().toString());
        ps.setInt(6, user.getId());

        ps.executeUpdate();
        System.out.println("l'Utlisateur " + user.getUsername() + " est modifié");
    }

   /* @Override
    public boolean updateUserNewPass(User user) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        try {
            Connection conn = MyDB.getInstance().getCnx();
            String query = "UPDATE user SET mdp=? WHERE addEmail=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Encryption.encrypt(user.getMdp()));
            pstmt.setString(2, user.getAddEmail());

            pstmt.executeUpdate();

            System.out.println("Mot de passe modifié avec succès.");
            return true;
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
            return false;
        }
    }*/

    @Override
    public void updateUserByEmail(User user) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String req = "UPDATE `user` SET mdp = ? WHERE addEmail = ?";
        PreparedStatement ps = cnx.prepareStatement(req);


        ps.setString(1, Encryption.encrypt(user.getMdp()));
      //  ps.setString(5, user.getUrlImage());

        ps.setString(2, user.getAddEmail());

        ps.executeUpdate();
        System.out.println("L'utilisateur  " + user.getAddEmail() + " a été mis à jour.");
    }


    @Override
    public void deleteUser(int id) throws SQLException {
        String req = "DELETE FROM `user` WHERE id= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<User> recuperer() throws SQLException {
        List<User> users = new ArrayList<>();
        String req = "SELECT * FROM `user`";
        try (
                Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(req)) {

            while (rs.next()) {
            //    int id = rs.getInt("id");
              //  int cin = rs.getInt("cin");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
            //    String username = rs.getString("username");
                int numTlfn = rs.getInt("numTlfn");
                String addEmail = rs.getString("addEmail");
                //String mdp = rs.getString("mdp");
                //String urlImage = rs.getString("urlImage");
                String roleUserString = rs.getString("roleUser");
                Roles roleUser = Roles.valueOf(roleUserString);
             //   String confirmationCode = rs.getString("confirmationCode");
             //   String urlImage = rs.getString("urlImage");



                User user = new User(/*cin,*/ nom, prenom/*, username*/, numTlfn, addEmail/*, mdp*/, roleUser/*,confirmationCode,urlImage*/);
                users.add(user);
            }
        }
        return users;
    }


    public User getByEmail(String e) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        User user = new User(); // Initialize user as null
        String query = "SELECT * FROM `user`  WHERE addEmail = ?";
        PreparedStatement ps = cnx.prepareStatement(query);
        ps.setString(1, e);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setCin(rs.getInt("cin"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setUsername(rs.getString("username"));
            user.setNumTlfn(rs.getInt("numTlfn"));
            user.setAddEmail(rs.getString("addEmail"));
            user.setMdp(rs.getString("mdp"));
            user.setRoleUser(Roles.valueOf(rs.getString("roleUser")));
            user.setConfirmationCode(rs.getString("confirmationCode"));
            user.setUrlImage(rs.getString("urlImage"));
            user.setVerified(rs.getBoolean("isVerified"));

        }
        return user;
    }

    public boolean userExist(String e) throws SQLException {
        String query = "SELECT COUNT(*) FROM `user` WHERE addEmail = ?";
        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setString(1,e);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }


   /* public static List<User> findUsersByCriteria(String nom, String prenom, String username, int numTlfn, String addEmail, Roles roleUser) {
        List<User> userList = new ArrayList<>();

        try {
            // Création de la requête de recherche
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT * FROM user WHERE 1=1 ");

            // Ajout des critères de recherche optionnels
            if (nom != null && !nom.isEmpty()) {
                queryBuilder.append("AND nom = ? ");
            }
            if (prenom != null && !prenom.isEmpty()) {
                queryBuilder.append("AND prenom = ? ");
            }
            if (username != null && !username.isEmpty()) {
                queryBuilder.append("AND username = ? ");
            }
            if (numTlfn != 0) {
                queryBuilder.append("AND numTlfn = ? ");
            }
            if (addEmail != null && !addEmail.isEmpty()) {
                queryBuilder.append("AND addEmail = ? ");
            }
            if (roleUser != null) {
                queryBuilder.append("AND roleUser = ? ");
            }

            // Préparation de la requête
            PreparedStatement pre = cnx.prepareStatement(queryBuilder.toString());

            // Paramètres des critères de recherche optionnels
            int parameterIndex = 1;
            if (nom != null && !nom.isEmpty()) {
                pre.setString(parameterIndex++, nom);
            }
            if (prenom != null && !prenom.isEmpty()) {
                pre.setString(parameterIndex++, prenom);
            }
            if (username != null && !username.isEmpty()) {
                pre.setString(parameterIndex++, username);
            }
            if (numTlfn != 0) {
                pre.setInt(parameterIndex++, numTlfn);
            }
            if (addEmail != null && !addEmail.isEmpty()) {
                pre.setString(parameterIndex++, addEmail);
            }
            if (roleUser != null) {
                pre.setString(parameterIndex, roleUser.toString());
            }

            // Exécution de la requête
            ResultSet rs = pre.executeQuery();

            // Traitement du résultat
            while (rs.next()) {
                // Extraction des données de l'utilisateur
                int cin = rs.getInt("cin");
                nom = rs.getString("nom");
                prenom = rs.getString("prenom");
                username = rs.getString("username");
                numTlfn = rs.getInt("numTlfn");
                addEmail = rs.getString("addEmail");
                String mdp = rs.getString("mdp");
                String roleUserString = rs.getString("roleUser");
                roleUser = Roles.valueOf(roleUserString);
                String confirmationCode = rs.getString("confirmationCode");

                // Création de l'objet User et ajout à la liste
                User user = new User(cin, nom, prenom, username, numTlfn, addEmail, mdp, roleUser, confirmationCode);
                userList.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return userList;
    }*/




   /* public boolean isEmailExists(String email) throws SQLException {

        Connection cnx = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            cnx = MyDB.getInstance().getCnx();
            String query = "SELECT * FROM `user` WHERE addEmail = ?";
            statement = cnx.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            return resultSet.next(); // Renvoie true si l'e-mail existe déjà, sinon false
        } finally {
            // Fermeture des ressources
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (cnx != null) {
                cnx.close();
            }
        }
    }*/

    @Override
    public User Login(String username, String password) throws SQLException {
        Connection cnx = MyDB.getInstance().getCnx();
        User user = null;
        String req = "SELECT * FROM user WHERE username=? AND mdp=?";
        try (PreparedStatement stm = cnx.prepareStatement(req)) {
            stm.setString(1, username);
            stm.setString(2, Encryption.encrypt(password));
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String roleUserString = rs.getString("roleUser");
               // String mdp = rs.getString("mdp");

                // Convertir la chaîne de rôle en enum
                Roles roleUser = Roles.valueOf(roleUserString);

                // Créer et retourner un objet User
                user = new User(id, nom, prenom, roleUser);
            }
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void EnvoieMail(String addEmail) {
        String codeConfirmation = generateConfirmationCode();

        // Définir les propriétés SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Créer une session SMTP
        javax.mail.Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("rania.gasmi@esprit.tn", "Ranouta2110165");
            }
        });

        try {
            // Créer et configurer le message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rania.gasmi@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addEmail));
            message.setSubject("Réinitialisation de mot de passe");
            message.setText("Votre code de confirmation est : " + codeConfirmation);

            // Envoyer le message
            Transport.send(message);
            System.out.println("Message envoyé avec succès à " + addEmail);
            storeConfirmationCodeInDatabase(addEmail, codeConfirmation);
            System.out.println("Code de confirmation stocké dans la base de données pour " + addEmail);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
        }


    }

    private void storeConfirmationCodeInDatabase(String addEmail, String codeConfirmation) {
        // Obtenez l'instance de connexion à la base de données
        Connection cnx1 = MyDB.getInstance().getCnx();

        // Requête SQL pour insérer le code de confirmation dans la table correspondante
        String sql = "UPDATE `user` SET confirmationCode= ? WHERE addEmail= ?";

        try (
                // Préparer la déclaration SQL
                PreparedStatement statement = cnx1.prepareStatement(sql);
        ) {
            // Définir les valeurs des paramètres de la requête
            statement.setString(1, codeConfirmation);
            statement.setString(2, addEmail);

            // Exécuter la requête d'insertion
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Le code de confirmation a été stocké dans la base de données pour " + addEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'insertion du code de confirmation dans la base de données : " + e.getMessage());
        }
    }

    




    private String generateConfirmationCode() {
        // Longueur du code de confirmation
        int length = 6;

        // Caractères possibles pour le code de confirmation
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Créer un objet StringBuilder pour construire le code
        StringBuilder codeBuilder = new StringBuilder();

        // Créer un objet Random pour générer des indices aléatoires
        Random random = new Random();

        // Générer le code caractère par caractère
        for (int i = 0; i < length; i++) {
            // Sélectionner un caractère aléatoire dans la chaîne de caractères
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            // Ajouter le caractère au code
            codeBuilder.append(randomChar);
        }

        // Retourner le code de confirmation généré
        return codeBuilder.toString();
    }


   /* public boolean isValidConfirmationCode(String confirmationCode, String addEmail) {
        Connection cnx = null;
        PreparedStatement statement = null;
        String userEmail = MDPController.getUserEmail();

        ResultSet resultSet = null;
        try {
            cnx = MyDB.getInstance().getCnx();
            statement = cnx.prepareStatement("SELECT `confirmationCode` FROM user WHERE addEmail = ?");
            statement.setString(1, addEmail);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
               // String storedConfirmationCode = resultSet.getString("confirmationCode");
                // Comparer le code de confirmation stocké avec celui saisi par l'utilisateur
                return confirmationCode.equals(userEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer les ressources
            MyDB.getInstance().closeConnection(cnx, statement, resultSet);
        }
        return false;
    }
*/

    public boolean getUserByConfirmationCode(String confirmationCode) throws SQLException {
        String query = "SELECT * FROM user WHERE confirmationCode = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, confirmationCode);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    // Récupérer d'autres attributs de l'utilisateur si nécessaire

                    return true;
                }
            }
        }
        return false;
    }



        public boolean updatePasswordInDatabase(User user) throws SQLException {
            String query = "UPDATE `user` SET mdp = ? WHERE confirmationCode = ?";
            try (PreparedStatement statement = cnx.prepareStatement(query)) {
                statement.setString(1, user.getMdp());
                statement.setString(2, user.getConfirmationCode());

                int rowsUpdated = statement.executeUpdate();
                return rowsUpdated > 0; // Vérifie si au moins une ligne a été mise à jour
            } catch (NoSuchPaddingException e) {
                throw new RuntimeException(e);
            } catch (IllegalBlockSizeException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (BadPaddingException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            }
        }

    @Override
    public void deleteUserCin(int cin) throws SQLException {

            String req = "DELETE FROM `user` WHERE cin= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, cin);
            ps.executeUpdate();
        }

    public void InvertStatus(String email) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String query = "UPDATE user SET Status = ?  WHERE addEmail = ?";
        PreparedStatement ps = cnx.prepareStatement(query);
        if (getByEmail(email).getStatus()) {
            ps.setBoolean(1, false);
        } else {
            ps.setBoolean(1, true);
        }
        ps.setString(2,email);
        ps.executeUpdate();
    }
}


