/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainFolder.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mainFolder.salvataggioDati.LeggiDati;
import mainFolder.salvataggioDati.ScriviDati;

/**
 *
 * @author molte
 */
public class GestioneAerei {
    private static GestioneAerei instance;
    private ObservableList<Aerei> elencoAereiPartenza = FXCollections.observableArrayList();
    private ObservableList<Aerei> elencoAereiArrivo = FXCollections.observableArrayList();
    private ObservableList<Aerei> elencoAereiTutti = FXCollections.observableArrayList();
    private ObservableList<Aerei> elencoAereiDeposito = FXCollections.observableArrayList();
    private ArrayList<Boolean> gate = new ArrayList<Boolean>();

    ScriviDati scrivi;
    LeggiDati leggi;

    public GestioneAerei () {
        scrivi = new ScriviDati();
        leggi = new LeggiDati();
        riempiGate();
        //poi da fare apertura dati
        caricaDati();
    }

    public void riempiGate() {
        for (int i = 0; i < 70; i++) {
                gate.add(false);
        }
    }

    public ObservableList<Aerei> getElencoLista() {
        return elencoAereiTutti;
    }
    public ObservableList<Aerei> getElencoListaArrivi() {
        return elencoAereiArrivo;
    }
    public ObservableList<Aerei> getElencoListaPartenze() {
        return elencoAereiPartenza;
    }


    public static GestioneAerei getInstance() {
        if (instance == null) {
            instance = new GestioneAerei();
        }
        return instance;
    }

    public void addAereo(String modello, String provenienza, String destinazione, String compagnia, String codice, 
     int numMax, LocalDate giornoArrivo, LocalTime oraArrivo, 
      LocalDate giornoPartenza, LocalTime oraPartenza, int intervallo) {
        LocalDate arrivo = giornoArrivo;
        LocalDate partenza = giornoPartenza;
        while (arrivo.isBefore(LocalDate.now())) {
                arrivo = arrivo.plusDays(intervallo);
                partenza = partenza.plusDays(intervallo);
        }

        
        Aerei a = new Aerei (modello, provenienza, destinazione, compagnia, codice, numMax, arrivo, oraArrivo, partenza, oraPartenza, intervallo);
        a.setGate(assegnaGate());
        a.setTerminal(1); // Un unico terminal
        elencoAereiTutti.add(a);
    }

    //anche in questo caso questa è la versione con lo stato
    public void addAereo(String modello, String provenienza, String destinazione, String compagnia, String codice, 
     int numMax, LocalDate giornoArrivo, LocalTime oraArrivo, 
      LocalDate giornoPartenza, LocalTime oraPartenza, int intervallo, String stato) {
        LocalDate arrivo = giornoArrivo;
        LocalDate partenza = giornoPartenza;
        while (arrivo.isBefore(LocalDate.now())) {
                arrivo = arrivo.plusDays(intervallo);
                partenza = partenza.plusDays(intervallo);
        }

        
        Aerei a = new Aerei (modello, provenienza, destinazione, compagnia, codice, numMax, arrivo, oraArrivo, partenza, oraPartenza, intervallo, stato);
        a.setGate(assegnaGate());
        a.setTerminal(1); // Un unico terminal
        elencoAereiTutti.add(a);
    }

    //versione nel caso in cui ci sia anche lo stato manutenzione (inizio, fine e hangar)
    public void addAereo(String modello, String provenienza, String destinazione, String compagnia, String codice, 
     int numMax, LocalDate giornoArrivo, LocalTime oraArrivo, 
      LocalDate giornoPartenza, LocalTime oraPartenza, int intervallo, String stato, LocalDate inizioManutenzione, LocalDate fineManutenzione, String hangar) {
        LocalDate arrivo = giornoArrivo;
        LocalDate partenza = giornoPartenza;
        while (arrivo.isBefore(LocalDate.now())) {
                arrivo = arrivo.plusDays(intervallo);
                partenza = partenza.plusDays(intervallo);
        }

        
        Aerei a = new Aerei (modello, provenienza, destinazione, compagnia, codice, numMax, arrivo, oraArrivo, partenza, oraPartenza, intervallo, stato, inizioManutenzione, fineManutenzione, hangar);
        a.setGate(assegnaGate());
        a.setTerminal(1); // Un unico terminal
        elencoAereiTutti.add(a);
    }

    public void rimuoviAereo(Aerei a) {
        elencoAereiTutti.remove(a);
    }

    public int assegnaGate() {
        boolean correct = false;
        while (correct == false) {
                int index = (int) (Math.random() * 70);
                if (gate.get(index) == false) {
                        correct = true;
                        gate.set(index, true);
                        return index;
                }
        }
        return -1;
    }

    //basta chiamare questo metodo con la date voluta e cambia la lista visualizzata con appunto la data pssata
    public void setDataPartenza(LocalDate data) {
        elencoAereiDeposito.clear();

        for (int i = 0; i < elencoAereiTutti.size(); i++) {
                elencoAereiDeposito.add(elencoAereiTutti.get(i));
        }

        elencoAereiPartenza.clear();

        for (int i = 0; i < elencoAereiDeposito.size(); i++) {
                if (elencoAereiDeposito.get(i).getGiornoArrivoProperty().getValue().isEqual(data)) {
                        elencoAereiPartenza.add(elencoAereiDeposito.get(i));
                }
        }

        bubbleSortByOraPartenza(elencoAereiPartenza);
    }

    //basta chiamare questo metodo con la date voluta e cambia la lista visualizzata con appunto la data pssata
    public void setDataArrivo(LocalDate data) {
        elencoAereiDeposito.clear();

        for (int i = 0; i < elencoAereiTutti.size(); i++) {
                elencoAereiDeposito.add(elencoAereiTutti.get(i));
        }

        elencoAereiArrivo.clear();

        for (int i = 0; i < elencoAereiDeposito.size(); i++) {
                if (elencoAereiDeposito.get(i).getGiornoArrivoProperty().getValue().isEqual(data)) {
                        elencoAereiArrivo.add(elencoAereiDeposito.get(i));
                }
        }

        bubbleSortByOraPartenza(elencoAereiArrivo);
    }

    private void bubbleSortByOraPartenza(ObservableList<Aerei> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Confronto le ore di partenza degli aerei
                if (list.get(j).getOraPartenzaProperty().getValue().isAfter(list.get(j + 1).getOraPartenzaProperty().getValue())) {
                    // Scambio gli elementi se l'ora di partenza del primo aereo è successiva a quella del secondo aereo
                    Aerei temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    private void caricaDati() {

        for (Aerei aereo : leggi.leggiAerei()) {
                addAereo(aereo.getModello(), aereo.getProvenienza(),aereo.getDestinazione(), aereo.getCompagnia(), aereo.getCodice(), aereo.getPostiMassimi(), 
                aereo.getGiornoArrivoLocalDate(), aereo.getOraArrivoLocalTime(), aereo.getGiornoPartenzaLocalDate(), aereo.getOraPartenzaLocalTime(), aereo.getIntervallo());
        }

        scrivi.scriviAerei(elencoAereiTutti);
    }

    public void filterPartenze(String searchTerm, LocalDate searchDate) {
            elencoAereiDeposito.clear();

            for (Aerei aereo : elencoAereiPartenza) {
                    // Check if the modello or provenienza matches the search term and the
                    // giornoPartenza matches the search date
                    if ((aereo.getModello().toLowerCase().contains(searchTerm) ||
                        aereo.getDestinazione().toLowerCase().contains(searchTerm) ||
                        aereo.getProvenienza().toLowerCase().contains(searchTerm) ||
                        aereo.getCompagnia().toLowerCase().contains(searchTerm) ||
                        aereo.getCodice().toLowerCase().contains(searchTerm)) &&
                        aereo.getGiornoPartenzaProperty().getValue().isEqual(searchDate)) {
                                elencoAereiDeposito.add(aereo);
                    }
            }

            elencoAereiPartenza.clear();

            for (int i = 0; i < elencoAereiDeposito.size(); i++) {
                elencoAereiPartenza.add(elencoAereiDeposito.get(i));    
            }

            bubbleSortByOraPartenza(elencoAereiDeposito);
    }

    public void filterArrivi(String searchTerm, LocalDate searchDate) {
            elencoAereiDeposito.clear();

            for (Aerei aereo : elencoAereiArrivo) {
                    // Check if the modello or destinazione matches the search term and the
                    // giornoArrivo matches the search date
                    if ((aereo.getModello().toLowerCase().contains(searchTerm) ||
                        aereo.getDestinazione().toLowerCase().contains(searchTerm) ||
                        aereo.getProvenienza().toLowerCase().contains(searchTerm) ||
                        aereo.getCompagnia().toLowerCase().contains(searchTerm) ||
                        aereo.getCodice().toLowerCase().contains(searchTerm))&& 
                        aereo.getGiornoArrivoProperty().getValue().isEqual(searchDate)) {
                                elencoAereiDeposito.add(aereo);
                    }
            }

            elencoAereiArrivo.clear();

            for (int i = 0; i < elencoAereiDeposito.size(); i++) {
                elencoAereiArrivo.add(elencoAereiDeposito.get(i));
            }

            bubbleSortByOraPartenza(elencoAereiArrivo);
    }

    public ObservableList<Aerei> getFilteredPartenze() {
            return elencoAereiDeposito;
    }

    public ObservableList<Aerei> getFilteredArrivi() {
            return elencoAereiDeposito;
    }

        // Reset partenze
        public void resetPartenze() {
                elencoAereiPartenza.clear();

                for (int i = 0; i < elencoAereiTutti.size(); i++) {
                elencoAereiPartenza.add(elencoAereiTutti.get(i));
                }

                bubbleSortByOraPartenza(elencoAereiPartenza);
        }

        // Reset arrivi
        public void resetArrivi() {
                elencoAereiArrivo.clear();

                for (int i = 0; i < elencoAereiTutti.size(); i++) {
                elencoAereiArrivo.add(elencoAereiTutti.get(i));
                }

                bubbleSortByOraPartenza(elencoAereiArrivo);
        }

        public void resetFilter() {
                resetArrivi();
                resetPartenze();
        }
}
