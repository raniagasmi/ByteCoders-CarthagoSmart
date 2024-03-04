package bytecoders.pidev.controllers;

import bytecoders.pidev.models.Roles;
import bytecoders.pidev.models.User;
import bytecoders.pidev.services.UserServices;
import bytecoders.pidev.utils.MyDB;
import bytecoders.pidev.utils.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class AfficherList {
    private Connection cnx = MyDB.getInstance().getCnx();


    private final UserServices userService; // Déclarez userService en tant que champ de classe final


    public AfficherList() {
        userService = new UserServices(); // Initialisez userService dans le constructeur


    }


    @FXML
    private TextField recherchetf;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<User> tab;


   /* @FXML
    private TableColumn<User, Integer> idc;*/

  /*  @FXML
    private TableColumn<User, Integer> cinc;

        @FXML
    private TableColumn<User, String> usernamec;

      @FXML
    private TableColumn<User, String> mdpc;

       @FXML
    private TableColumn<User, String> confirmationCodec;


    */

    @FXML
    private TableColumn<User, String> nomc;

    @FXML
    private TableColumn<User, String> prenomc;


    @FXML
    private TableColumn<User, Integer> numTlfnc;

    @FXML
    private TableColumn<User, String> addEmailc;



    @FXML
    private TableColumn<User, Roles> roleUserc;



  /*  @FXML
    private TableColumn<User, String> imagec;*/

    @FXML
    private Button logout;

    @FXML
    private Button exit;

    @FXML
    TableColumn<User, Void> actionC = new TableColumn<>("Actions");


    List<User> userList;
    ObservableList<User> dataList;

    @FXML
    void initialize() {
        //assert imagec != null : "fx:id=\"imagec\" was not injected: check your FXML file 'AfficherList.fxml'.";
        assert tab != null : "fx:id=\"tab\" was not injected: check your FXML file 'AfficherList.fxml'.";
        // assert idc != null : "fx:id=\"idc\" was not injected: check your FXML file 'AfficherList.fxml'.";
       // assert cinc != null : "fx:id=\"cinc\" was not injected: check your FXML file 'AfficherList.fxml'.";
        assert nomc != null : "fx:id=\"nomc\" was not injected: check your FXML file 'AfficherList.fxml'.";
        assert prenomc != null : "fx:id=\"prenomc\" was not injected: check your FXML file 'AfficherList.fxml'.";
       // assert usernamec != null : "fx:id=\"usernamec\" was not injected: check your FXML file 'AfficherList.fxml'.";
        assert numTlfnc != null : "fx:id=\"numTlfnc\" was not injected: check your FXML file 'AfficherList.fxml'.";
        assert addEmailc != null : "fx:id=\"addEmailc\" was not injected: check your FXML file 'AfficherList.fxml'.";
        //assert mdpc != null : "fx:id=\"mdpc\" was not injected: check your FXML file 'AfficherList.fxml'.";
        assert roleUserc != null : "fx:id=\"roleUserc\" was not injected: check your FXML file 'AfficherList.fxml'.";
      //  assert confirmationCodec != null : "fx:id=\"confirmationCodec\" was not injected: check your FXML file 'AfficherList.fxml'.";
        assert recherchetf != null : "fx:id=\"recherchetf\" was not injected: check your FXML file 'AfficherList.fxml'.";
        assert exit != null : "fx:id=\"exit\" was not injected: check your FXML file 'AfficherList.fxml'.";
        assert actionC != null : "fx:id=\"actionC\" was not injected: check your FXML file 'AfficherList.fxml'.";



        //TableColumn<User, String> nomc = new TableColumn<>("nom");
        //nomc.setCellValueFactory(new PropertyValueFactory<>("nom"));
/*
        TableColumn<User, String> prenomc = new TableColumn<>("prenom");
        prenomc.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<User, Integer> numTlfnc = new TableColumn<>("numTlfn");
        numTlfnc.setCellValueFactory(new PropertyValueFactory<>("numTlfn"));

        TableColumn<User, String> addEmailc = new TableColumn<>("addEmail");
        addEmailc.setCellValueFactory(new PropertyValueFactory<>("addEmail"));

        TableColumn<User, Roles> roleUserc = new TableColumn<>("roleUser");
        roleUserc.setCellValueFactory(new PropertyValueFactory<>("roleUser"));
*/

        TableColumn<User, Button> actionC = new TableColumn<>("Actions");
        actionC.setCellValueFactory(new PropertyValueFactory<>(""));





        // Define the action for the "View" button
        actionC.setCellFactory(ActionButtonTableCell.forTableColumn("View", (User user) -> {
            // Implement the action here to view the user's profile
            System.out.println("Viewing profile of user: " + user.getNom());
            return user;
        }));

        tab.getColumns().add(actionC);


        UserServices userService = null;
        try {
            userService = new UserServices();
            List<User> users = userService.recuperer();
            ObservableList<User> userList = FXCollections.observableArrayList(users);

            nomc.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenomc.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            numTlfnc.setCellValueFactory(new PropertyValueFactory<>("numTlfn"));
            addEmailc.setCellValueFactory(new PropertyValueFactory<>("addEmail"));
            roleUserc.setCellValueFactory(new PropertyValueFactory<>("roleUser"));


            tab.setItems(userList);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

       /* TableColumn<User, Void> actionColumn = new TableColumn<>("Actions");

        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button viewButton = new Button("Consulter");

            {
                viewButton.setOnAction(event -> {
                    User selectedUser = getTableView().getItems().get(getIndex());
                    if (selectedUser != null) {
                        // Implémentez ici la logique pour consulter le profil de l'utilisateur
                        // Vous pouvez ouvrir une nouvelle fenêtre pour afficher les détails du profil
                        System.out.println("Profil de l'utilisateur sélectionné : " + selectedUser);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(viewButton);
                }
            }
        });

        tab.getColumns().add(actionColumn);
*/

    }


    @FXML
    private void logout(ActionEvent event) throws IOException {
        Session.setLoggedInUser(null);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        Parent root = loader.load();

        // root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();


    }

    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }



    @FXML
    private void filtrer(KeyEvent event) throws SQLException {
       // usernamec.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        numTlfnc.setCellValueFactory(new PropertyValueFactory<User, Integer>("numTlfn"));
        addEmailc.setCellValueFactory(new PropertyValueFactory<User, String>("addEmail"));
        roleUserc.setCellValueFactory(new PropertyValueFactory<User,Roles>("roleUser"));



        UserServices userService = new UserServices();
        userList = userService.recuperer();
        dataList = FXCollections.observableArrayList(userList);
        tab.setItems(dataList);

        FilteredList<User> filteredData = new FilteredList<>(dataList, b -> true);
        recherchetf.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (person.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(person.getNumTlfn()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getAddEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if (person.getRoleUser() != null && person.getRoleUser().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Vérification du critère roleUser
                }

                else {
                    return false;
                }
            });
        });

        SortedList<User> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tab.comparatorProperty());
        tab.setItems(sortedData);
    }




}

   /* public ObservableList<User> getUserList() {
        cnx = MyDB.getInstance().getCnx();

        ObservableList<User> UserList = FXCollections.observableArrayList();
        try {
            String query2="SELECT * FROM  user ";
            PreparedStatement smt = cnx.prepareStatement(query2);
            User user;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               // user=new User(rs.getInt("id"),rs.getInt("cin"),(rs.getString("user_name"),rs.getInt("numero"),rs.getString("email"),rs.getString("adresse"));
              /// user = new User(rs.getInt("id"),rs.getInt("cin"),rs.getString(NomTF));
                //UserList.add(user);
            }
            System.out.println(UserList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return UserList;

    }*/
    /*@FXML
    void ShowUser(ActionEvent event) {

        ObservableList<User> list = getUserList();
        idUser.setCellValueFactory(new PropertyValueFactory<>("id"));
        CinUser.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        Username.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        NumeroUser.setCellValueFactory(new PropertyValueFactory<>("numero"));
        EmailUser.setCellValueFactory(new PropertyValueFactory<>("email"));
        AdresseUser.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        tableviewUser.setItems(list);

    }


*/



