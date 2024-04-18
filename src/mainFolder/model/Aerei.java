package mainFolder.model;

import java.time.*;
import java.time.format.DateTimeFormatter;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Aerei {
    private final StringProperty modelloAereo;
    private StringProperty provenienza;
    private StringProperty destinazione;
    private final StringProperty compagniaAerea;
    private final StringProperty codiceRegistrazione;

    private final int numeroMassimoPasseggeri;
    private IntegerProperty numeroPostiOccupati;
    private IntegerProperty gate;
    private IntegerProperty terminal;



    private IntegerProperty ritardo;
    private ObjectProperty<LocalDate> giornoDiArrivo;
    private ObjectProperty<LocalTime> oraArrivo;
    private ObjectProperty<LocalDate> giornoDiPartenza;
    private ObjectProperty<LocalTime> oraPartenza;
    private int intervalloDiGiorni;
    // .plusDays(intervallo giorni)


    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatterOre = DateTimeFormatter.ofPattern("HH:mm:ss");


    private boolean scaricoBagagli = false;
    private boolean caricoBagagli = false;
    private boolean imbarco = false;



    public Aerei(String modello, String provenienza, String destinazione, String compagnia, String codice,
            int numMax, LocalDate giornoDiArrivo, LocalTime oraArrivo,
            LocalDate giornoPartenza, LocalTime oraPartenza, int intervallo) {

        this.modelloAereo = new SimpleStringProperty(modello);
        this.provenienza = new SimpleStringProperty(provenienza);
        this.destinazione = new SimpleStringProperty(destinazione);
        this.compagniaAerea = new SimpleStringProperty(compagnia);
        this.codiceRegistrazione = new SimpleStringProperty(codice);
        this.numeroMassimoPasseggeri = numMax;
        this.giornoDiArrivo = new SimpleObjectProperty<>(giornoDiArrivo);
        this.oraArrivo = new SimpleObjectProperty<>(oraArrivo);
        this.giornoDiPartenza = new SimpleObjectProperty<>(giornoPartenza);
        this.oraPartenza = new SimpleObjectProperty<>(oraPartenza);
        this.intervalloDiGiorni = intervallo;
        this.ritardo = new SimpleIntegerProperty(0); // Inizializzazione di ritardo
        this.gate = new SimpleIntegerProperty(-1); // Inizializzazione di gate
    }

    //---------------------------getter------------------------------------------------

    public String getModello() {
        return modelloAereo.get();
    }

    public StringProperty getModelloProperty() {
        return modelloAereo;
    }

    public StringProperty getProvenienzaProperty() {
        return provenienza;
    }
    public String getProvenienzaString() {
        return provenienza.get();
    }

    public StringProperty getDestinazioneProperty() {
        return destinazione;
    }
    public String getDestinazioneString() {
        return destinazione.get();
    }

    public StringProperty getCompagniaProperty() {
        return compagniaAerea;
    }

    public String getCompagnia() {
        return compagniaAerea.get();
    }

    public StringProperty getCodiceProperty() {
        return codiceRegistrazione;
    }

    public String getCodice() {
        return codiceRegistrazione.get();
    }

    public int postiMassimi () {
        return numeroMassimoPasseggeri;
    }

    public IntegerProperty getNumeroPostiOccupatiProperty() {
        return numeroPostiOccupati;
    }
    public int getNumeroPostiOccupatiInt() {
        return numeroPostiOccupati.get();
    }

    public IntegerProperty getGateProperty() {
        return gate;
    }
    public int getGateInt() {
        return gate.get();
    }

    public IntegerProperty getTerminalProperty() {
        return terminal;
    }
    public int getTerminalInt() {
        return terminal.get();
    }

    public IntegerProperty getRitardoProperty() {
        return ritardo;
    }
    public int getRitardoInt() {
        return ritardo.get();
    }

    public ObjectProperty<LocalDate> getGiornoArrivoProperty() {
        return giornoDiArrivo;
    }
    public String getGiornoArrivoString() {
        return giornoDiArrivo.get().format(formatterData);
    }

    public ObjectProperty<LocalTime> getOraArrivoProperty () {
        return oraArrivo;
    }
    public String getOraArrivoString() {
        return oraArrivo.get().format(formatterOre);
    }

    public ObjectProperty<LocalDate> getGiornoPartenzaProperty() {
        return giornoDiPartenza;
    }
    public String getGiornoPartenzaString() {
        return giornoDiPartenza.get().format(formatterData);
    }

    public ObjectProperty<LocalTime> getOraPartenzaProperty() {
        return oraPartenza;
    }
    public String getOraPartenzaString() {
        return oraPartenza.get().format(formatterOre);
    }

    public boolean getScaricoBagagli() {
        return scaricoBagagli;
    }

    public boolean getCaricoBagagli() {
        return caricoBagagli;
    }

    public boolean getImbarco() {
        return imbarco;
    }

    //---------------------------setter-----------------------------------------

    public void setProvenienza(String provenienzaString) {
        this.provenienza.set(provenienzaString);
    }

    public void setDestinazione(String destinazioneString) {
        this.destinazione.set(destinazioneString);
    }

    public void setNumeroPostiOccupati(int numeroPostiOccupati) {
        this.numeroPostiOccupati.set(numeroPostiOccupati);
    }

    public void setGate(int gate) {
        this.gate.set(gate);
    }

    public void setTerminal(int terminal) {
        this.terminal.set(terminal);
    }

    public void setRitardo(int ritardo) {
        this.ritardo.set(ritardo);
    }

    public void setGiornoArrivo(LocalDate giornoDiArrivo) {
        this.giornoDiArrivo.set(giornoDiArrivo);
    }

    public void setOraArrivo(LocalTime oraArrivo) {
        this.oraArrivo.set(oraArrivo);
    }

    public void setGiornoPartenza(LocalDate giornoDiPartenza) {
        this.giornoDiPartenza.set(giornoDiPartenza);
    }

    public void setOraPartenza(LocalTime oraPartenza) {
        this.oraPartenza.set(oraPartenza);
    }

    public void setScaricoBagagli(boolean scaricoBagagli) {
        this.scaricoBagagli = scaricoBagagli;
    }

    public void setCaricoBagagli(boolean caricoBagagli) {
        this.caricoBagagli = caricoBagagli;
    }

    public void setImbarco(boolean imbarco) {
        this.imbarco = imbarco;
    }

    public void setIntervalloGiorni(int intervallo) {
        this.intervalloDiGiorni = intervallo;
    }

    
}
