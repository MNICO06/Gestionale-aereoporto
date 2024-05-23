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
    private StringProperty stato;
    private StringProperty hangar;

    private int numeroMassimoPasseggeri;
    private IntegerProperty numeroPostiOccupati;
    private IntegerProperty gate;
    private IntegerProperty terminal;

    private IntegerProperty ritardo;
    private ObjectProperty<LocalDate> giornoDiArrivo;
    private ObjectProperty<LocalTime> oraArrivo;
    private ObjectProperty<LocalDate> giornoDiPartenza;
    private ObjectProperty<LocalTime> oraPartenza;
    private int intervalloDiGiorni;

    private ObjectProperty<LocalDate> inizioManutenzione;
    private ObjectProperty<LocalDate> fineManutenzione;


    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatterOre = DateTimeFormatter.ofPattern("HH:mm:ss");


    private boolean scaricoBagagli = false;
    private boolean caricoBagagli = false;
    private boolean imbarco = false;

    private boolean aggiornato = false;


    // questo con lo stato, quindi quello aggiornato quello sopra poi non servirà
    public Aerei(String modello, String provenienza, String destinazione, String compagnia, String codice,
            int numMax, LocalDate giornoDiArrivo, LocalTime oraArrivo,
            LocalDate giornoPartenza, LocalTime oraPartenza, int intervallo, String stato, int ritardo, int numPosti) {

        while (giornoDiArrivo.isBefore(LocalDate.now())) {
            giornoDiArrivo = giornoDiArrivo.plusDays(intervallo);
            giornoPartenza = giornoPartenza.plusDays(intervallo);
            //nel caso in cui cambia il giorno, resetta anche il ritardo e il numero di posti occupati
            ritardo = 0;
            numPosti = 0;
        }

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
        this.gate = new SimpleIntegerProperty(-1); // Inizializzazione di gate
        this.terminal = new SimpleIntegerProperty(-1); // Inizializzazione di terminal
        inizioManutenzione = new SimpleObjectProperty<>(null);
        fineManutenzione = new SimpleObjectProperty<>(null);
        this.stato = new SimpleStringProperty(stato);
        hangar = new SimpleStringProperty(null);
        this.ritardo = new SimpleIntegerProperty(ritardo);
        this.numeroPostiOccupati = new SimpleIntegerProperty(numPosti);
    }

    // versione aggiunta erei nel caso in cui ci sia come stato quello della
    // manutenzione
    public Aerei(String modello, String provenienza, String destinazione, String compagnia, String codice,
            int numMax, LocalDate giornoDiArrivo, LocalTime oraArrivo,
            LocalDate giornoPartenza, LocalTime oraPartenza, int intervallo, String stato, LocalDate inizioManutenzione,
            LocalDate fineManutenzione, String hangar, int ritardo, int numPosti) {

        while (giornoDiArrivo.isBefore(LocalDate.now())) {
            giornoDiArrivo = giornoDiArrivo.plusDays(intervallo);
            giornoPartenza = giornoPartenza.plusDays(intervallo);
        }

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
        this.gate = new SimpleIntegerProperty(-1); // Inizializzazione di gate
        this.terminal = new SimpleIntegerProperty(-1); // Inizializzazione di terminal
        this.inizioManutenzione = new SimpleObjectProperty<>(inizioManutenzione);
        this.fineManutenzione = new SimpleObjectProperty<>(fineManutenzione);
        this.stato = new SimpleStringProperty(stato);
        this.hangar = new SimpleStringProperty(hangar);
        this.ritardo = new SimpleIntegerProperty(ritardo);
        this.numeroPostiOccupati = new SimpleIntegerProperty(numPosti);
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

    public int getPostiMassimi () {
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
    public LocalDate getGiornoArrivoLocalDate() {
        return giornoDiArrivo.get();
    }
    public String getGiornoArrivoString() {
        return giornoDiArrivo.get().format(formatterData);
    }

    public ObjectProperty<LocalTime> getOraArrivoProperty () {
        return oraArrivo;
    }
    public LocalTime getOraArrivoLocalTime() {
        return oraArrivo.get();
    }
    public String getOraArrivoString() {
        return oraArrivo.get().format(formatterOre);
    }

    public ObjectProperty<LocalDate> getGiornoPartenzaProperty() {
        return giornoDiPartenza;
    }
    public LocalDate getGiornoPartenzaLocalDate() {
        return giornoDiPartenza.get();
    }
    public String getGiornoPartenzaString() {
        return giornoDiPartenza.get().format(formatterData);
    }

    public ObjectProperty<LocalTime> getOraPartenzaProperty() {
        return oraPartenza;
    }
    public LocalTime getOraPartenzaLocalTime() {
        return oraPartenza.get();
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

    public int getIntervallo() {
        return intervalloDiGiorni;
    }

    public String getProvenienza() {
        return provenienza.get();
    }

    public String getDestinazione() {
        return destinazione.get();
    }

    public ObjectProperty<LocalDate> getInizioManutenzione() {
        return inizioManutenzione;
    }
    public LocalDate getInizioManutenzioneLocalDate() {
        return inizioManutenzione.get();
    }
    public String getInizioManutenzioneString() {
        return inizioManutenzione.get().format(formatterData);
    }

    public ObjectProperty<LocalDate> getFineManutenzione() {
        return fineManutenzione;
    }
    public LocalDate getFineManutenzioneLocalDate() {
        return fineManutenzione.get();
    }
    public String getFineManutenzioneString() {
        return fineManutenzione.get().format(formatterData);
    }

    public StringProperty getStatoProperty() {
        return stato;
    }
    public String getStatoString() {
        return stato.get();
    }

    public StringProperty getHangarProperty() {
        return hangar;
    }
    public String getHangarString() {
        return hangar.get();
    }

    public String getStato() {
        return stato.get();
    }

    public String getHangar() {
        return hangar.get();
    }

    // getGiornoPartenza restituisce localDate
    public LocalDate getGiornoPartenza() {
        return giornoDiPartenza.get();
    }

    // getGiornoArrivo
    public LocalDate getGiornoArrivo() {
        return giornoDiArrivo.get();
    }

    // getInizioLavori
    public LocalDate getInizioLavori() {
        return inizioManutenzione.get();
    }

    // getFineLavori
    public LocalDate getFineLavori() {
        return fineManutenzione.get();
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

    public void setPostiMassimi(int postiMassimi) {
        this.numeroMassimoPasseggeri = postiMassimi;
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

    public void setGiornoInizioManutenzione(LocalDate inizioM) {
        this.inizioManutenzione.set(inizioM);
    }

    public void setGiornoFineManutenzione(LocalDate fineM) {
        this.fineManutenzione.set(fineM);
    }

    public void setStato(String stato) {
        this.stato.set(stato);
    }

    public void setHangar(String hangar) {
        this.hangar.set(hangar);
    }

    public void setModello(String modello) {
        this.modelloAereo.set(modello);
    }

    public void setCompagnia(String compagnia) {
        this.compagniaAerea.set(compagnia);
    }

    public void setCodice(String codice) {
        this.codiceRegistrazione.set(codice);
    }

    // Getter e setter per lo stato

    public boolean isInVolo() {
        // Se il tempo di arrivo è 5 minuti prima o dopo dell'ora attuale, l'aereo è in volo
        LocalTime oraAttuale = LocalTime.now();
        LocalTime oraArrivo = getOraArrivoLocalTime();
        if (oraAttuale.isAfter(oraArrivo.minusMinutes(5)) && oraAttuale.isBefore(oraArrivo.plusMinutes(5))) {
            if (stato.get().equals("In arrivo")) {
                aggiornato = false;
            }
            else {
                aggiornato = true;
            }
            return true;
        }
        return false;
    }

    public boolean isInPartenza() {
        // Se il tempo di partenza è 5 minuti prima o dopo dell'ora attuale, l'aereo è in partenza
        LocalTime oraAttuale = LocalTime.now();
        LocalTime oraPartenza = getOraPartenzaLocalTime();
        if (oraAttuale.isAfter(oraPartenza.minusMinutes(5)) && oraAttuale.isBefore(oraPartenza.plusMinutes(5))) {
            if (stato.get().equals("In partenza")) {
                aggiornato = false;
            }
            else {
                aggiornato = true;
            }
            return true;
        }
        return false;
    }

    public boolean isInAttesa() {
        // Se l'aereo non è ne in arrivo ne in partenza, allora è a terra in attesa
        if (stato.get().equals("In attesa")) {
            aggiornato = false;
        }
        else {
            aggiornato = true;
        }
        return !isInVolo() && !isInPartenza();
    }

    public boolean isInManutenzione() {
        // Se l'aereo è a terra e se il giorno attuale è compreso tra l'inizio e la fine della manutenzione
        LocalDate oggi = LocalDate.now();
        LocalDate inizio = getInizioManutenzioneLocalDate();
        LocalDate fine = getFineManutenzioneLocalDate();
        if (oggi.isAfter(inizio) && oggi.isBefore(fine)) {
            if (stato.get().equals("In manutenzione")) {
                aggiornato = false;
            }
            else {
                aggiornato = true;
            }
            return true;
        }
        return false;
    }

    public void setAggiornato(boolean aggiornato) {
        this.aggiornato = aggiornato;
    }

    public boolean isAggiornato() {
        return aggiornato;
    }
}
