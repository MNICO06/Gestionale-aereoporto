/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainFolder.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import mainFolder.model.Aerei;
import mainFolder.model.GestioneAerei;
import mainFolder.model.GestioneUtenti;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author molte
 */
public class mainController {

    @FXML private Label orologio;
    @FXML private Label titolo;
    @FXML private Button accedi;
    @FXML private DatePicker datePicker;

    GestioneUtenti gestioneUtenti = GestioneUtenti.getInstance();
    GestioneAerei gestioneAerei = GestioneAerei.getInstance();

    @FXML private TableView<Aerei> tabellaArrivo;
    @FXML private TableColumn<Aerei, LocalTime> colonnaOrarioArrivo;
    @FXML private TableColumn<Aerei, Integer> colonnaRitardoArrivo;
    @FXML private TableColumn<Aerei, String> colonnaProvenienzaArrivo;
    @FXML private TableColumn<Aerei, String> colonnaNVoloArrivo;
    @FXML private TableColumn<Aerei, Integer> colonnaGateArrivo;
    @FXML private TableColumn<Aerei, String> colonnaCompagniaArrivo;
    @FXML private TableColumn<Aerei, String> colonnaStatoArrivo;

    @FXML private TableView<Aerei> tabellaPartenza;
    @FXML private TableColumn<Aerei, LocalTime> colonnaOrarioPartenza;
    @FXML private TableColumn<Aerei, Integer> colonnaRitardoPartenza;
    @FXML private TableColumn<Aerei, String> colonnaDestinazionePartenza;
    @FXML private TableColumn<Aerei, String> colonnaNVoloPartenza;
    @FXML private TableColumn<Aerei, Integer> colonnaGatePartenza;
    @FXML private TableColumn<Aerei, String> colonnaCompagniaPartenza;
    @FXML private TableColumn<Aerei, String> colonnaStatoPartenza;

    @FXML private TableView<Aerei> tabellaTerra;
    @FXML private TableColumn<Aerei, LocalTime> colonnaOrarioTerra;
    @FXML private TableColumn<Aerei, Integer> colonnaRitardoTerra;
    @FXML private TableColumn<Aerei, String> colonnaDestinazioneTerra;
    @FXML private TableColumn<Aerei, String> colonnaNVoloTerra;
    @FXML private TableColumn<Aerei, Integer> colonnaGateTerra;
    @FXML private TableColumn<Aerei, String> colonnaCompagniaTerra;
    @FXML private TableColumn<Aerei, String> colonnaStatoTerra;

    @FXML private TableView<Aerei> tabellaManutenzione;
    @FXML private TableColumn<Aerei, String> colonnaNVoloManutenzione;
    @FXML private TableColumn<Aerei, String> colonnaCompagniaManutenzione;
    @FXML private TableColumn<Aerei, LocalDate> colonnaInizioLavoriManutenzione;
    @FXML private TableColumn<Aerei, LocalDate> colonnaFineLavoriManutenzione;
    @FXML private TableColumn<Aerei, String> colonnaHangarManutenzione;



    public void initialize() {
        // Creazione di un Timeline per aggiornare l'orologio ogni secondo
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            orologio.setText(dateFormat.format(new Date()));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        accedi.setDisable(true);
        accedi.setOpacity(1);

        if (datePicker.getValue() == null) {
            setData(LocalDate.now());
        }

        initializeTable();

        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            changeData();
        });

    }

    private void setData(LocalDate date) {
        datePicker.setValue(date);
    }


    public void initializeTable() {
        colonnaOrarioArrivo.setCellValueFactory(cellData -> cellData.getValue().getOraArrivoProperty());
        colonnaRitardoArrivo.setCellValueFactory(cellData -> cellData.getValue().getRitardoProperty().asObject());
        colonnaProvenienzaArrivo.setCellValueFactory(cellData -> cellData.getValue().getProvenienzaProperty());
        colonnaNVoloArrivo.setCellValueFactory(cellData -> cellData.getValue().getCodiceProperty());
        colonnaGateArrivo.setCellValueFactory(cellData -> cellData.getValue().getGateProperty().asObject());
        colonnaCompagniaArrivo.setCellValueFactory(cellData -> cellData.getValue().getCompagniaProperty());
        colonnaStatoArrivo.setCellValueFactory(cellData -> cellData.getValue().getStatoProperty());


        colonnaOrarioPartenza.setCellValueFactory(cellData -> cellData.getValue().getOraPartenzaProperty());
        colonnaRitardoPartenza.setCellValueFactory(cellData -> cellData.getValue().getRitardoProperty().asObject());
        colonnaDestinazionePartenza.setCellValueFactory(cellData -> cellData.getValue().getDestinazioneProperty());
        colonnaNVoloPartenza.setCellValueFactory(cellData -> cellData.getValue().getCodiceProperty());
        colonnaGatePartenza.setCellValueFactory(cellData -> cellData.getValue().getGateProperty().asObject());
        colonnaCompagniaPartenza.setCellValueFactory(cellData -> cellData.getValue().getCompagniaProperty());
        colonnaStatoPartenza.setCellValueFactory(cellData -> cellData.getValue().getStatoProperty());


        colonnaOrarioTerra.setCellValueFactory(cellData -> cellData.getValue().getOraArrivoProperty());
        colonnaRitardoTerra.setCellValueFactory(cellData -> cellData.getValue().getRitardoProperty().asObject());
        colonnaDestinazioneTerra.setCellValueFactory(cellData -> cellData.getValue().getDestinazioneProperty());
        colonnaNVoloTerra.setCellValueFactory(cellData -> cellData.getValue().getCodiceProperty());
        colonnaGateTerra.setCellValueFactory(cellData -> cellData.getValue().getGateProperty().asObject());
        colonnaCompagniaTerra.setCellValueFactory(cellData -> cellData.getValue().getCompagniaProperty());
        colonnaStatoTerra.setCellValueFactory(cellData -> cellData.getValue().getStatoProperty());


        colonnaNVoloManutenzione.setCellValueFactory(cellData -> cellData.getValue().getCodiceProperty());
        colonnaCompagniaManutenzione.setCellValueFactory(cellData -> cellData.getValue().getCompagniaProperty());
        colonnaInizioLavoriManutenzione.setCellValueFactory(cellData -> cellData.getValue().getInizioManutenzione());
        colonnaFineLavoriManutenzione.setCellValueFactory(cellData -> cellData.getValue().getFineManutenzione());
        colonnaHangarManutenzione.setCellValueFactory(cellData -> cellData.getValue().getHangarProperty());

    
        tabellaArrivo.setItems(gestioneAerei.getElencoListaArrivi());
        tabellaPartenza.setItems(gestioneAerei.getElencoListaPartenze());
    }

    
    @FXML
    public void changeData() {
        gestioneAerei.setDataArrivo(datePicker.getValue());
        gestioneAerei.setDataPartenza(datePicker.getValue());
    }

    
}
