package sio.gestionpermis;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import sio.gestionpermis.Model.Eleve;
import sio.gestionpermis.Model.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestionPermisController implements Initializable {
    private ArrayList<String> lesEleves;
    Eleve eleveSelectionnee;
    Test testSelectionnee;

    private Eleve eleve;
    @FXML
    private ComboBox cboTests;
    @FXML
    private TextField txtNomEleve;
    @FXML
    private Button btnInscription;
    @FXML
    private ComboBox cboEleves;
    @FXML
    private TableView<Test> tvTests;
    @FXML
    private CheckBox chkTermine;
    @FXML
    private Button btnModifier;
    @FXML
    private TableColumn tcNomTest;
    @FXML
    private TableColumn tcNbFautes;
    @FXML
    private TableColumn tcTermine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lesEleves = new ArrayList<String>();

        initDatas();
        cboTests.getItems().addAll("Test n°1", "Test n°2", "Test n°3", "Test n°4", "Test n°5");
        cboEleves.getSelectionModel().selectFirst();

        // Remplir la ComboBox des élèves
        cboEleves.getItems().addAll("Enzo", "Lilou", "Noa");
        cboEleves.getSelectionModel().selectFirst();

        tcNomTest.setCellValueFactory(new PropertyValueFactory<>("nomTest"));
        tcNbFautes.setCellValueFactory(new PropertyValueFactory<>("nbFautes"));
        tcTermine.setCellValueFactory(new PropertyValueFactory<>("estTermine"));

        tcNomTest.setCellFactory(TextFieldTableCell.<Test>forTableColumn());
        tcNbFautes.setCellFactory(TextFieldTableCell.<Test>forTableColumn());
        tcTermine.setCellFactory(TextFieldTableCell.<Test>forTableColumn());

        tcNomTest.setCellFactory(tc-> new TextFieldTableCell<>(new DoubleStringConverter()));
//        tvTests.setItems(FXCollections.observableArrayList(lesTests));




        tcNomTest.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                // A vous de jouer
                // Modifier le nom du test de l'élève
//                TablePosition tablePosition = cellEditEvent.getTablePosition();
//                testSelectionnee.getNomTest().get(tablePosition.getRow()).setNom(Double.parseDouble(cellEditEvent.getNewValue().toString()));

            }
        });
        tvTests.setEditable(true);

        tcNbFautes.setCellFactory(tc-> new TextFieldTableCell<>(new DoubleStringConverter()));

        tcNbFautes.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                // A vous de jouer
                // Modifier le nombre de fautes pour le bon test de l'élève
//                TablePosition tablePosition = cellEditEvent.getTablePosition();
//                testSelectionnee.getNbFautes().get(tablePosition.getRow()).setFautes(Double.parseDouble(cellEditEvent.getNewValue().toString()));


            }
        });
        tvTests.setEditable(true);



        // A chaque changement dans la CombBox des élèves : partie MODIFICATION
        cboEleves.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                if (newValue != null) {
                    tvTests.setItems(FXCollections.observableArrayList(eleveSelectionnee.getLesTests()));

                    cboTests.getItems().clear();
                    for (Test test : eleveSelectionnee.getLesTests()) {
                        cboTests.getItems().add(test.getNomTest());
                    }

                }
            }
        });
        tvTests.setEditable(true);

    }

    @FXML
    public void btnInscriptionClicked(Event event) {
        // A vous de jouer
        if (txtNomEleve.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Choix de l'élève");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir le nom de l'élève");
            alert.showAndWait();
        } else if (cboTests.getSelectionModel().getSelectedItem().equals(lesEleves) && cboTests.getSelectionModel().getSelectedItem().equals(testSelectionnee.isEstTermine()) )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inscription impossible");
            alert.setHeaderText(null);
            alert.setContentText("Vous ne pouvez pas vous inscrire à ce test car il n'est pas terminée");
            alert.showAndWait();
        } else {

            Eleve nouvelEleve = new Eleve(txtNomEleve.getText());
            lesEleves.add(txtNomEleve.getText());
            cboEleves.getItems().add(txtNomEleve.getText());
            cboEleves.getSelectionModel().selectLast();

        }

    }


    public void initDatas() {
        Eleve eleve1 = new Eleve("Enzo");
        Eleve eleve2 = new Eleve("Noa");
        Eleve eleve3 = new Eleve("Lilou");

        Test test1 = new Test("Test n°1", 0, false);
        Test test2 = new Test("Test n°2", 0, false);
        Test test3 = new Test("Test n°3", 0, false);
        Test test4 = new Test("Test n°4", 0, false);
        Test test5 = new Test("Test n°5", 0, false);

        eleve1.ajouterTest(test1);
        eleve1.ajouterTest(test2);
        eleve2.ajouterTest(test3);
        eleve3.ajouterTest(test4);
        eleve3.ajouterTest(test5);

        lesEleves.add(String.valueOf(eleve1));
        lesEleves.add(String.valueOf(eleve2));
        lesEleves.add(String.valueOf(eleve3));
    }

    // NE PAS MODIFIER CE CODE
    // Cette méthode permet de retrouver dans la liste
    // des élèves, l'élève dont le nom est passé en paramètre.
    // Soit on le trouve et dans ce cas on retourne l'objet
    // Soit on ne le trouve pas et dans ce cas on retourne null;
    public String rechercherEleve(String nomEleve) {
        Eleve unEleve = null;
//        for (String eleve : lesEleves)
//         {
//            if (eleve.getNomEleve().equals(nomEleve)) {
//                return eleve;
//            }
//        }
       return "unEleve";
    }




    @FXML
    public void btnModifierClicked(Event event)
    {
        // A vous de jouer

        if (cboTests.getSelectionModel().getSelectedItem().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Choix du test");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un test");
            alert.showAndWait();
        }else {
            testSelectionnee = new Test(cboTests.getSelectionModel().getSelectedItem().toString(), 0, false);
            testSelectionnee.setEstTermine(chkTermine.isSelected());
        }
        tvTests.refresh();


    }
}
