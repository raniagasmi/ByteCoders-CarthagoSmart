package ByteCoders.ViewController;

import ByteCoders.Model.Categorie;
import ByteCoders.Model.collect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.*;
import java.util.*;

import static ByteCoders.Model.collect.collectList;

public class Responsable implements Initializable {

    @FXML
    private TableView<collect> tableView;

    @FXML
    private TableColumn<collect, String> nomType;

    @FXML
    private TableColumn<collect, Categorie> Categorie;

    @FXML
    private TableColumn<collect, String> PointRamassage;

    @FXML
    private TableColumn<collect, String> DateRamassage;

    @FXML
    private TableColumn<collect, String> PointRecyclage;

    @FXML
    private TextField tfType;

    @FXML
    private TextField tfCategorie;

    @FXML
    private TextField tfPtRamassage;

    @FXML
    private TextField tfDateRamassage;

    @FXML
    private TextField tfPtRecyclage;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;
    private ObservableList<ByteCoders.Model.collect> collectList = FXCollections.observableArrayList();


    public Responsable() throws SQLException {
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        System.out.println("Button Clicked");
        if (event.getSource() == btnAjouter) {
            insertRecord();
        } else if (event.getSource() == btnModifier) {
            updateRecord();
        } else if (event.getSource() == btnSupprimer) {
            deleteRecord();
        }
    }
    //TRI PAR CATEGORIE//

    private FilteredList<collect> filteredData = new FilteredList<>(FXCollections.observableArrayList());

    /*@Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            filteredData = new FilteredList<>(collectList, p -> true);

            // Lier le FilteredList à la TableView
            tableView.setItems(filteredData);

            // Ajouter un écouteur de modification pour le TextField
            search.textProperty().addListener((observable, oldValue, newValue) -> {
                // Mettre à jour le prédicat de filtrage en fonction du texte de recherche
                filteredData.setPredicate(collect -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true; // Afficher tous les éléments si le texte de recherche est vide
                    }

                    // Comparer la catégorie avec le texte de recherche (ignorer la casse)
                    return collect.getCategorie().toString().toLowerCase().equals(newValue.toLowerCase());
                });
            });

            // Afficher les données initiales dans la TableView
            afficher();
        }*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        afficher();

        //addToTableView("");
        search.textProperty().addListener((observable, oldVal, newVal) -> {
            addToTableView(newVal);
        });
    }

    public Connection getCnx() throws SQLException {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carthagosmart", "root", "");
            System.out.println("connection established");
            return con;
        } catch (SQLException ex) {
            System.out.println("Error connecting to the database: " + ex.getMessage());
            throw ex;
        }
    }
    private void afficher() {
        nomType.setCellValueFactory(new PropertyValueFactory<>("nomType"));
        Categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        PointRamassage.setCellValueFactory(new PropertyValueFactory<>("PointRamassage"));
        DateRamassage.setCellValueFactory(new PropertyValueFactory<>("DateRamassage"));
        PointRecyclage.setCellValueFactory(new PropertyValueFactory<>("PointRecyclage"));
        try {
            Connection con = getCnx();
            String query = "SELECT * FROM collectdechets";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            ObservableList<collect> collectList = FXCollections.observableArrayList();
            while (rs.next()) {
                collect collect = createCollectFromResultSet(rs);
                collectList.add(collect);
            }
            System.out.println("===========================");
            System.out.println(collectList);
            System.out.println("===========================");
            tableView.setItems(collectList);
            tableView.refresh();
            // Fermer les ressources de manière appropriée dans un bloc finally
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private collect createCollectFromResultSet(ResultSet rs) throws SQLException {
        int typeId = rs.getInt("typeId");
        String nomType = rs.getString("nomType");
        String categorieString = rs.getString("Categorie").replace(" ", "_").toUpperCase().trim();
        ByteCoders.Model.Categorie categorie = ByteCoders.Model.Categorie.valueOf(categorieString);
        String pointRamassage = rs.getString("PointRamassage");
        String dateRamassage = rs.getString("DateRamassage");
        String pointRecyclage = rs.getString("PointRecyclage");

        System.out.println("Categorie from database: " + categorieString);

        return new collect(typeId, nomType, categorie, pointRamassage, dateRamassage, pointRecyclage);
    }
    public void insertRecord() throws SQLException {
        Connection con = getCnx();
        con.setAutoCommit(false);  // Début de la transaction
        try {
            String query = "INSERT INTO collectdechets (nomType, Categorie, PointRamassage, DateRamassage, PointRecyclage) " +
                    "VALUES ('" + tfType.getText() + "', '" + tfCategorie.getText() + "', '" +
                    tfPtRamassage.getText() + "', '" + tfDateRamassage.getText() + "', '" +
                    tfPtRecyclage.getText() + "')";

            executeQuery(query);
            afficher();
            con.commit();  // Validation de la transaction
        } catch (SQLException ex) {
            con.rollback();  // Annulation de la transaction en cas d'erreur
            ex.printStackTrace();
        } finally {
            con.setAutoCommit(true);  // Rétablissement de l'autocommit par défaut
        }
    }

    public void updateRecord() throws SQLException {
        String query = "UPDATE collectdechets SET Categorie = '" + tfCategorie.getText() + "', PointRamassage = '" +
                tfPtRamassage.getText() + "', DateRamassage = '" + tfDateRamassage.getText() +
                "' WHERE nomType = '" + tfType.getText() + "'";

        executeQuery(query);
        afficher();
    }
    public void deleteRecord() throws SQLException {
        String query = "DELETE FROM collectdechets WHERE nomType = '" + tfType.getText() + "'";

        executeQuery(query);
        afficher();
    }

    //TRI PAR CATEGORIE//
    @FXML
    private void handleTriParCategorie(ActionEvent event) {
        // Créer un Comparator pour trier d'abord par catégorie, puis par nomType en ordre décroissant
        Comparator<collect> comparator = Comparator
                .comparing(collect::getCategorie)
                .thenComparing(collect::getNomType, String.CASE_INSENSITIVE_ORDER.reversed())
                .thenComparing(collect::getPointRamassage, String.CASE_INSENSITIVE_ORDER.reversed())
                .thenComparing(collect::getDateRamassage, String.CASE_INSENSITIVE_ORDER.reversed())
                .thenComparing(collect::getPointRecyclage, String.CASE_INSENSITIVE_ORDER.reversed());

        // Trier la liste observée associée à la TableView
        tableView.getItems().sort(comparator.reversed());
    }

    @FXML
    private TextField search;
    /*public void addToTableView(String newVal) {

        try {
            collectList.clear();
            List<collect> collection = collectList;
            collection.removeIf(collect -> !collect.getCategorie().toString().equals(newVal));
            System.out.println(collection);
            for (collect item : collection) {
                collectList.add(new collect(
                        item.getTypeId(),
                        item.getNomType(),
                        item.getCategorie(),
                        item.getPointRamassage(),
                        item.getDateRamassage(),
                        item.getPointRecyclage()
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }*/

    public void addToTableView(String newVal) {
        try {
            collectList.clear();

            for (collect item : filteredData) {
                if (containsIgnoreCase(item.getNomType(), newVal) ||
                        containsIgnoreCase(item.getCategorie().toString(), newVal) ||
                        containsIgnoreCase(item.getPointRamassage(), newVal) ||
                        containsIgnoreCase(item.getDateRamassage(), newVal) ||
                        containsIgnoreCase(item.getPointRecyclage(), newVal)) {

                    collectList.add(item);
                }
            }

            //tableView.setItems(collectList);
            tableView.refresh();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Méthode utilitaire pour vérifier si une chaîne contient (ignorant la casse) une sous-chaîne
    private boolean containsIgnoreCase(String fullString, String part) {
        return fullString.toLowerCase().contains(part.toLowerCase());
    }



    //mettre à jour la base de données
    private void executeQuery(String query) throws SQLException {
        Connection con = getCnx();
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeUpdate(query); // Use executeUpdate for non-query statements
            System.out.println("Record inserted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }}





/*Connection con = getCnx();
    public List<collect> recuperer() throws SQLException {
        List<collect> collects = new ArrayList<>();
        String req = "SELECT * FROM collectdechets";
        try (

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(req);)
        {while (rs.next()) {
            int typeId = rs.getInt("typeId");
            String nomType = rs.getString("nomType");
            String categorieString = rs.getString("Categorie").replace(" ", "_").toUpperCase().trim();
            ByteCoders.Model.Categorie categorie = ByteCoders.Model.Categorie.valueOf(categorieString);
            String pointRamassage = rs.getString("PointRamassage");
            String dateRamassage = rs.getString("DateRamassage");
            String pointRecyclage = rs.getString("PointRecyclage");



                collect Responsable = new collect(typeId, nomType, categorie, pointRamassage, dateRamassage, pointRecyclage);
                collects.add(Responsable);
            }
        }
        return collects;
}*/

