package ByteCoders.ViewController;

import ByteCoders.Model.Categorie;
import ByteCoders.Model.collect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

import static ByteCoders.Model.collect.collectList;

public class Responsable {
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

    public void initialize(URL url, ResourceBundle resourceBundle) throws SQLException {
        afficher();
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
    /*public void insertRecord() throws SQLException {
        String query = "INSERT INTO collectdechets (nomType, Categorie, PointRamassage, DateRamassage) " +
                "VALUES ('" + tfType.getText() + "', '" + tfCategorie.getText() + "', '" +
                tfPtRamassage.getText() + "', '" + tfDateRamassage.getText() + "')";

        executeQuery(query);
        afficher();
    }*/
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
    }
}

