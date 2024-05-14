package mainFolder.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import mainFolder.model.Aerei;
import mainFolder.model.GestioneAerei;

public class dettagliAereoAdminController {

    // Modificatori
    @FXML private ComboBox<String> modelloAereoCmbx;
    @FXML private TextField provenienzaTxF;
    @FXML private TextField destinazioneTxF;
    @FXML private ComboBox<String> terminalCmbx;
    @FXML private TextField gateTxF;
    @FXML private TextField compagniaAereaTxF;
    @FXML private TextField postiTotaliTxF;
    @FXML private TextField postiOccupatiTxF;
    @FXML private ComboBox<String> statoCmbx;
    @FXML private DatePicker giornoArrivoDp;
    @FXML private DatePicker giornoPartenzaDp;
    @FXML private TextField orarioArrivoTxF;
    @FXML private TextField orarioPartenzaTxF;
    @FXML private TextField ritardoTxF;
    @FXML private TextField codiceTxF;  
    @FXML private TextField intervalloTxF;  
    // Se è in manutenzione
    @FXML private DatePicker inizioLavoriDp;
    @FXML private DatePicker fineLavoriDp;
    @FXML private TextField hangarTxf;

    @FXML private TextField nomeAereo;

    @FXML private Button salvaBtn;
    @FXML private Button modificaBtn;
    
    @FXML private Label orologio;

    // Lista gate occupati
    GestioneAerei gestioneAerei = GestioneAerei.getInstance();
    ArrayList<Boolean> gateOccupati = gestioneAerei.getGate();

    // Aereo da modificare
    private Aerei aereo;

    public void setAereo(Aerei aereo) {
        this.aereo = aereo;
        nomeAereo.setText(aereo.getModello());
        modelloAereoCmbx.setValue(aereo.getModello());
        provenienzaTxF.setText(aereo.getProvenienzaString());
        postiTotaliTxF.setText(String.valueOf(aereo.getPostiMassimi()));
        giornoPartenzaDp.setValue(aereo.getGiornoPartenza());
        ritardoTxF.setText(String.valueOf(aereo.getRitardoInt()));
        compagniaAereaTxF.setText(aereo.getCompagnia());
        destinazioneTxF.setText(aereo.getDestinazioneString());
        gateTxF.setText(String.valueOf(aereo.getGateInt()));
        orarioArrivoTxF.setText(aereo.getOraArrivoString());
        giornoArrivoDp.setValue(aereo.getGiornoArrivo());
        orarioPartenzaTxF.setText(aereo.getOraPartenzaString());
        postiOccupatiTxF.setText(String.valueOf(aereo.getNumeroPostiOccupatiInt()));
        terminalCmbx.setValue(String.valueOf(aereo.getTerminalInt()));
        statoCmbx.setValue(aereo.getStato());
        codiceTxF.setText(aereo.getCodice());
        intervalloTxF.setText(String.valueOf(aereo.getIntervallo()));
        // Se è in manutenzione mostro i campi
        if (aereo.getStato().equals("In manutenzione")) {
            inizioLavoriDp.setDisable(false);
            fineLavoriDp.setDisable(false);
            hangarTxf.setDisable(false);
        } else {
            inizioLavoriDp.setDisable(true);
            fineLavoriDp.setDisable(true);
            hangarTxf.setDisable(true);
        }
    }

    @FXML
    private void initialize() {

        startClockUpdateAnimation();

        // Imposto i combobox
        // Modello aereo
        modelloAereoCmbx.getItems().addAll("Boeing 737", "Boeing 747", "Boeing 757", "Boeing 767", "Boeing 777", "Boeing 787", "Airbus A220", "Airbus A300", "Airbus A310", "Airbus A320", "Airbus A330", "Airbus A340", "Airbus A350", "Airbus A380");
        // Terminal
        terminalCmbx.getItems().addAll("1");
        // Stato
        statoCmbx.getItems().addAll("In arrivo", "In partenza", "In manutenzione", "In attesa");

        // Se è in manutenzione mostro i campi
        statoCmbx.setOnAction(e -> {
            if (statoCmbx.getValue().equals("In manutenzione")) {
                inizioLavoriDp.setDisable(false);
                fineLavoriDp.setDisable(false);
                hangarTxf.setDisable(false);
            } else {
                inizioLavoriDp.setDisable(true);
                fineLavoriDp.setDisable(true);
                hangarTxf.setDisable(true);
            }
        });

    }

    private void startClockUpdateAnimation() {
        // Animazione per l'orologio
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Deve avere questo formato: ore:minuti:secondi
                orologio.setText(String.format("%02d:%02d:%02d", 
                        java.time.LocalTime.now().getHour(),
                        java.time.LocalTime.now().getMinute(), 
                        java.time.LocalTime.now().getSecond()));
            }
        };
        // Avvio l'animazione
        timer.start();
    }

    @FXML
    private void salva() {
        // Salva un nuovo aereo nella lista
        // Chiamo un metodo per controllare che i valori siano corretti
        if (!controllaValori("S")) {
            return;
        }
        String modello = modelloAereoCmbx.getValue();
        String provenienza = provenienzaTxF.getText();
        String destinazione = destinazioneTxF.getText();
        int terminal = Integer.parseInt(terminalCmbx.getValue());
        int gate = Integer.parseInt(gateTxF.getText());
        String compagnia = compagniaAereaTxF.getText();
        int postiTotali = Integer.parseInt(postiTotaliTxF.getText());
        int postiOccupati = Integer.parseInt(postiOccupatiTxF.getText());
        String stato = statoCmbx.getValue();
        LocalDate giornoArrivo = giornoArrivoDp.getValue();
        LocalDate giornoPartenza = giornoPartenzaDp.getValue();
        LocalTime orarioArrivo = LocalTime.parse(orarioArrivoTxF.getText());
        LocalTime orarioPartenza = LocalTime.parse(orarioPartenzaTxF.getText());
        int ritardo = Integer.parseInt(ritardoTxF.getText());
        String codice = codiceTxF.getText();
        // Se è in manutenzione
        LocalDate inizioLavori = inizioLavoriDp.getValue();
        LocalDate fineLavori = fineLavoriDp.getValue();
        String hangar = hangarTxf.getText();
        int intervallo = Integer.parseInt(intervalloTxF.getText());

        Aerei aereoTmp;
        //metodi per salvare nuovo aereo (manca intervallo da aggiungere alla screen)
        if (stato.equals("In manutenzione")) {
            aereoTmp = new Aerei (modello, provenienza, destinazione, compagnia, codice, 
            postiTotali, giornoArrivo, orarioArrivo, giornoPartenza, orarioPartenza, intervallo, stato,
            inizioLavori, fineLavori, hangar);
        }
        else {
            aereoTmp = new Aerei (modello, provenienza, destinazione, compagnia, codice, 
            postiTotali, giornoArrivo, orarioArrivo, giornoPartenza, orarioPartenza, intervallo, stato);
        }

        aereo.setTerminal(terminal);
        aereo.setGate(gate);
        // Modifico la lista dei gate occupati
        gateOccupati.set(gate-1, true);
        aereo.setNumeroPostiOccupati(postiOccupati);
        aereo.setRitardo(ritardo);

        
        // Aggiungo l'aereo alla lista
        gestioneAerei.addAereo(aereoTmp);
        System.out.println("Aereo aggiunto");

        gestioneAerei.setDataArrivo(gestioneAerei.getDateArrivi());
        gestioneAerei.aggiornaArrivoAdmin(gestioneAerei.getDestArrivi(), gestioneAerei.getCompArrivi());

        gestioneAerei.setDataPartenza(gestioneAerei.getDatePartenze());
        gestioneAerei.aggiornaPartenzaAdmin(gestioneAerei.getDestPartenze(), gestioneAerei.getCompPartenze());

        gestioneAerei.setDataManutenzione(gestioneAerei.getDateManutenzioni());
        gestioneAerei.aggiornaManutenzioneAdmin(gestioneAerei.getDestManutenzioni(),gestioneAerei.getCompManutenzioni());

        gestioneAerei.setDataTerra(gestioneAerei.getDateTerra());
        gestioneAerei.aggiornaTerraAdmin(gestioneAerei.getDestTerra(), gestioneAerei.getCompTerra());

    }

    @FXML
    private void modifica() {
        // Chiamo un metodo per controllare che i valori siano corretti
        if (!controllaValori("M")) {
        return;}
        String codice = codiceTxF.getText();
        String modello = modelloAereoCmbx.getValue();
        String provenienza = provenienzaTxF.getText();
        String destinazione = destinazioneTxF.getText();
        int terminal = Integer.parseInt(terminalCmbx.getValue());
        int gate = Integer.parseInt(gateTxF.getText());
        String compagnia = compagniaAereaTxF.getText();
        int postiTotali = Integer.parseInt(postiTotaliTxF.getText());
        int postiOccupati = Integer.parseInt(postiOccupatiTxF.getText());
        String stato = statoCmbx.getValue();
        LocalDate giornoArrivo = giornoArrivoDp.getValue();
        LocalDate giornoPartenza = giornoPartenzaDp.getValue();
        LocalTime orarioArrivo = LocalTime.parse(orarioArrivoTxF.getText());
        LocalTime orarioPartenza = LocalTime.parse(orarioPartenzaTxF.getText());
        int ritardo = Integer.parseInt(ritardoTxF.getText());
        // Se è in manutenzione
        LocalDate inizioLavori = inizioLavoriDp.getValue();
        LocalDate fineLavori = fineLavoriDp.getValue();
        String hangar = hangarTxf.getText();

        // Modifico l'aereo
        aereo.setModello(modello);
        aereo.setProvenienza(provenienza);
        aereo.setDestinazione(destinazione);
        aereo.setTerminal(terminal);
        aereo.setGate(gate);
        // Modifico la lista dei gate occupati
        gateOccupati.set(aereo.getGateInt()-1, false);
        gateOccupati.set(gate-1, true);
        aereo.setCompagnia(compagnia);
        aereo.setPostiMassimi(postiTotali);
        aereo.setNumeroPostiOccupati(postiOccupati);
        aereo.setStato(stato);
        aereo.setGiornoArrivo(giornoArrivo);
        aereo.setGiornoPartenza(giornoPartenza);
        aereo.setOraArrivo(orarioArrivo);
        aereo.setOraPartenza(orarioPartenza);
        aereo.setRitardo(ritardo);
        aereo.setCodice(codice);
        // Se è in manutenzione
        if(stato.equals("In manutenzione")){
            aereo.setGiornoInizioManutenzione(inizioLavori);
            aereo.setGiornoFineManutenzione(fineLavori);
            aereo.setHangar(hangar);
        } else {
            aereo.setGiornoInizioManutenzione(null);
            aereo.setGiornoFineManutenzione(null);
            aereo.setHangar(null);
        }     

        // Scrivo i dati su file
        gestioneAerei.aggiornaLista();

        gestioneAerei.setDataArrivo(gestioneAerei.getDateArrivi());
        gestioneAerei.aggiornaArrivoAdmin(gestioneAerei.getDestArrivi(),gestioneAerei.getCompArrivi());

        gestioneAerei.setDataPartenza(gestioneAerei.getDatePartenze());
        gestioneAerei.aggiornaPartenzaAdmin(gestioneAerei.getDestPartenze(),gestioneAerei.getCompPartenze());

        gestioneAerei.setDataManutenzione(gestioneAerei.getDateManutenzioni());
        gestioneAerei.aggiornaManutenzioneAdmin(gestioneAerei.getDestManutenzioni(),gestioneAerei.getCompManutenzioni());

        gestioneAerei.setDataTerra(gestioneAerei.getDateTerra());
        gestioneAerei.aggiornaTerraAdmin(gestioneAerei.getDestTerra(),gestioneAerei.getCompTerra());

    }

    private boolean controllaValori(String c) {
        // Controllo che i valori siano corretti
        // Controllo il nome
        if (nomeAereo.getText().isEmpty()) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Inserire un nome valido");
            alert.showAndWait();
            return false;
        }
        // Controllo il modello se selezionato
        if (modelloAereoCmbx.getValue() == null) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Selezionare un modello");
            alert.showAndWait();
            return false;
        }
        // Controllo la provenienza se inserita correttamente
        if (provenienzaTxF.getText().isEmpty()) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Inserire una provenienza valida");
            alert.showAndWait();
            return false;
        }

        // Controllo la destinazione se inserita correttamente
        if (destinazioneTxF.getText().isEmpty()) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Inserire una destinazione valida");
            alert.showAndWait();
            return false;
        }

        // Controllo il terminal se è selezionato
        if (terminalCmbx.getValue() == null) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Selezionare un terminal");
            alert.showAndWait();
            return false;
        }

        // Se è in modifica
        try {
            Integer.parseInt(gateTxF.getText());
            if (c=="M"){
                // Controllo che il gate sia un numero

                // Controllo che il gate sia libero dalla lista dei gate occupati o che sia quello dell'aereo
                if (gateOccupati.get(Integer.parseInt(gateTxF.getText())-1) && Integer.parseInt(gateTxF.getText()) != aereo.getGateInt()) {
                    // Alert per errore
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setHeaderText("Errore");
                    alert.setContentText("Il gate selezionato è occupato");
                    alert.showAndWait();
                    return false;
                }
            } else {
                // Controllo che il gate sia libero
                if (gateOccupati.get(Integer.parseInt(gateTxF.getText())-1)) {
                    // Alert per errore
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setHeaderText("Errore");
                    alert.setContentText("Il gate selezionato è occupato");
                    alert.showAndWait();
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Inserire un gate valido (numero da 1 a 70)");
            alert.showAndWait();
            return false;
        }

        // Controllo la compagnia aerea se è vuota
        if (compagniaAereaTxF.getText().isEmpty()) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Inserire una compagnia aerea valida");
            alert.showAndWait();
            return false;
        }

        // Controllo i posti totali se è un numero e se è maggiore di 1
        try {
            int postiTotali = Integer.parseInt(postiTotaliTxF.getText());
            if (postiTotali < 1) {
                // Alert per errore
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setHeaderText("Errore");
                alert.setContentText("Inserire un numero di posti totali valido,\nmaggiore di 1");
                alert.showAndWait();
                return false;
            }
        } catch (NumberFormatException e) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Inserire un numero di posti totali valido,\nmaggiore di 1");
            alert.showAndWait();
            return false;
        }

        // Controllo i posti occupati se è un numero e se è minore di posti totali
        try {
            int postiOccupati = Integer.parseInt(postiOccupatiTxF.getText());
            if (postiOccupati < 0 || postiOccupati > Integer.parseInt(postiTotaliTxF.getText())) {
                // Alert per errore
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setHeaderText("Errore");
                alert.setContentText("Inserire un numero di posti occupati valido,\nmaggiore di 0 e minore di posti totali");
                alert.showAndWait();
                return false;
            }
        } catch (NumberFormatException e) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Inserire un numero di posti occupati valido,\nmaggiore di 0 e minore di posti totali");
            alert.showAndWait();
            return false;
        }

        // Controllo lo stato se è selezionato
        if (statoCmbx.getValue() == null) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Selezionare uno stato");
            alert.showAndWait();
            return false;
        }

        // Controllo che l'intervallo sia un numero e maggiore di 0
        try {
            int intervallo = Integer.parseInt(intervalloTxF.getText());
            if (intervallo < 0) {
                // Alert per errore
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setHeaderText("Errore");
                alert.setContentText("Inserire un intervallo valido,\nnuemro maggiore di 0");
                alert.showAndWait();
                return false;
            }
        } catch (NumberFormatException e) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Inserire un intervallo valido,\nnumero maggiore di 0");
            alert.showAndWait();
            return false;
        }

        // Controllo il giorno di arrivo se è selezionato
        if (giornoArrivoDp.getValue() == null) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Selezionare un giorno di arrivo");
            alert.showAndWait();
            return false;
        }

        // Controllo il giorno di partenza se è selezionato
        if (giornoPartenzaDp.getValue() == null) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Selezionare un giorno di partenza");
            alert.showAndWait();
            return false;
        }

        // Controllo l'orario di arrivo se è scritto nel formato corretto (hh:mm:ss)
        try {
            LocalTime.parse(orarioArrivoTxF.getText());
        } catch (Exception e) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Inserire un orario di arrivo valido,\nformato hh:mm:ss");
            alert.showAndWait();
            return false;
        }

        // Controllo l'orario di partenza se è scritto nel formato corretto (hh:mm:ss)
        try {
            LocalTime.parse(orarioPartenzaTxF.getText());
        } catch (Exception e) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Inserire un orario di partenza valido,\nformato hh:mm:ss");
            alert.showAndWait();
            return false;
        }

        // Controllo il ritardo se è un numero
        try {
            int ritardo = Integer.parseInt(ritardoTxF.getText());
            if (ritardo < 0) {
                // Alert per errore
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setHeaderText("Errore");
                alert.setContentText("Inserire un ritardo valido,\nmaggiore o uguale a 0");
                alert.showAndWait();
                return false;
            }
        } catch (NumberFormatException e) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Inserire un ritardo valido,\nmaggiore o uguale a 0");
            alert.showAndWait();
            return false;
        }

        // Controllo il codice se è vuoto deve essere in formato AB123
        if (codiceTxF.getText().isEmpty() || codiceTxF.getText().length() != 5 || !Character.isLetter(codiceTxF.getText().charAt(0)) || !Character.isLetter(codiceTxF.getText().charAt(1)) || !Character.isDigit(codiceTxF.getText().charAt(2)) || !Character.isDigit(codiceTxF.getText().charAt(3)) || !Character.isDigit(codiceTxF.getText().charAt(4))) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Inserire un codice valido,\nformato AA111");
            alert.showAndWait();
            return false;
        }

        // Controllo l'inizio lavori se è selezionato
        if (inizioLavoriDp.isDisable() == false && inizioLavoriDp.getValue() == null) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Selezionare una data di inizio lavori");
            alert.showAndWait();
            return false;
        }

        // Controllo la fine lavori se è selezionato
        if (fineLavoriDp.isDisable() == false && fineLavoriDp.getValue() == null) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Selezionare una data di fine lavori");
            alert.showAndWait();
            return false;
        }

        // Controllo l'hangar se è vuoto
        if (hangarTxf.isDisable() == false && hangarTxf.getText().isEmpty()) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Inserire un hangar valido");
            alert.showAndWait();
            return false;
        }

        //* Cotnrolli logigi *//
        // Controllo che il giorno di arrivo sia precedente o lo stesso giorno di
        // partenza
        if (giornoArrivoDp.getValue().isAfter(giornoPartenzaDp.getValue())) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Il giorno di arrivo non può essere maggiore del giorno di partenza");
            alert.showAndWait();
            return false;
        } else if (giornoArrivoDp.getValue().isEqual(giornoPartenzaDp.getValue())) {
            LocalTime orarioArrivo = LocalTime.parse(orarioArrivoTxF.getText());
            LocalTime orarioPartenza = LocalTime.parse(orarioPartenzaTxF.getText());

            // Controllo che l'orario di arrivo sia minore di quello di partenza
            // e che la differenza sia almeno di un'ora
            if (orarioPartenza.isAfter(orarioArrivo) && orarioPartenza.minusHours(1).isAfter(orarioArrivo)) {
                // Alert per errore
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setHeaderText("Errore");
                alert.setContentText(
                        "L'orario di partenza non può essere successivo di meno di 1 ora all'orario di arrivo");
                alert.showAndWait();
                return false;
            }
        } else {
            LocalTime orarioArrivo = LocalTime.parse(orarioArrivoTxF.getText());
            LocalTime orarioPartenza = LocalTime.parse(orarioPartenzaTxF.getText());

            // Controllo solo se l'orario di partenza è dopo la mezzanotte
            if (orarioPartenza.isAfter(LocalTime.MIDNIGHT)) {
                // Controllo che l'orario di partenza sia maggiore di 1 ora dal tempo di arrivo
                if (orarioPartenza.minusHours(1).isAfter(orarioArrivo)) {
                    // Alert per errore
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setHeaderText("Errore");
                    alert.setContentText(
                            "L'orario di partenza non può essere successivo di meno di 1 ora all'orario di arrivo");
                    alert.showAndWait();
                    return false;
                }
            }
        }


        // Controllo che la data di inizio lavori sia minore della data di fine lavori
        if (inizioLavoriDp.isDisable() == false && fineLavoriDp.isDisable() == false) {
            if (inizioLavoriDp.getValue().isAfter(fineLavoriDp.getValue())) {
                // Alert per errore
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setHeaderText("Errore");
                alert.setContentText("La data di inizio lavori non può essere maggiore della data di fine lavori");
                alert.showAndWait();
                return false;
            }
        }

        // Controllo che i posti totali siano maggiori dei posti occupati
        if (Integer.parseInt(postiTotaliTxF.getText()) < Integer.parseInt(postiOccupatiTxF.getText())) {
            // Alert per errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore");
            alert.setContentText("Il numero di posti totali non può essere minore del numero di posti occupati");
            alert.showAndWait();
            return false;
        }

        return true;
    }
}
