/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainFolder.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public GestioneAerei () {
        scrivi = new ScriviDati();
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
         
        addAereo("Airbus A380", "MXP", "FCO", "Quatar Airways", "ABC123", 
            517, LocalDate.of(2024, 04, 11) , LocalTime.of(9,45), LocalDate.of(2024, 04, 11),LocalTime.of(9,0), 3);
        addAereo("Boeing 747", "JFK", "LHR", "British Airways", "BA123",
                450, LocalDate.of(2024, 04, 11), LocalTime.of(12, 30), LocalDate.of(2024, 04, 11), LocalTime.of(13, 45),
                2);

        addAereo("Airbus A320", "LAX", "ORD", "American Airlines", "AA456",
                180, LocalDate.of(2024, 04, 11), LocalTime.of(8, 15), LocalDate.of(2024, 04, 11), LocalTime.of(10, 0),
                2);

        addAereo("Boeing 787 Dreamliner", "HND", "SYD", "Qantas", "QF789",
                250, LocalDate.of(2024, 04, 11), LocalTime.of(14, 0), LocalDate.of(2024, 04, 11), LocalTime.of(23, 30),
                1);

        addAereo("Airbus A350", "CDG", "SIN", "Singapore Airlines", "SQ123",
                300, LocalDate.of(2024, 04, 11), LocalTime.of(10, 45), LocalDate.of(2024, 04, 11), LocalTime.of(19, 15),
                1);

        addAereo("Boeing 737", "ATL", "MCO", "Delta Air Lines", "DL456",
                160, LocalDate.of(2024, 04, 11), LocalTime.of(11, 30), LocalDate.of(2024, 04, 11), LocalTime.of(13, 0),
                2);

        addAereo("Airbus A330", "FRA", "DXB", "Emirates", "EK789",
                280, LocalDate.of(2024, 04, 11), LocalTime.of(9, 0), LocalDate.of(2024, 04, 11), LocalTime.of(16, 45),
                1);

        addAereo("Boeing 777", "SFO", "ICN", "Korean Air", "KE789",
                350, LocalDate.of(2024, 04, 11), LocalTime.of(13, 45), LocalDate.of(2024, 04, 11), LocalTime.of(22, 30),
                1);

        addAereo("Airbus A380", "LHR", "JFK", "Virgin Atlantic", "VS123",
                500, LocalDate.of(2024, 04, 11), LocalTime.of(16, 30), LocalDate.of(2024, 04, 11), LocalTime.of(20, 15),
                3);

        addAereo("Boeing 757", "SEA", "DEN", "United Airlines", "UA456",
                200, LocalDate.of(2024, 04, 11), LocalTime.of(8, 45), LocalDate.of(2024, 04, 11), LocalTime.of(11, 30),
                2);

        addAereo("Boeing 747", "MUC", "AMS", "KLM", "KL123",
                150, LocalDate.of(2024, 04, 11), LocalTime.of(10, 0), LocalDate.of(2024, 04, 11), LocalTime.of(12, 45),
                2);

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
