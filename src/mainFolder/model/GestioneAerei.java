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

/**
 *
 * @author molte
 */
public class GestioneAerei {
    private static GestioneAerei instance;
    private ObservableList<Aerei> elencoAerei = FXCollections.observableArrayList();
    private ObservableList<Aerei> elencoAereiFiltrati = FXCollections.observableArrayList();
    private ArrayList<Boolean> gate = new ArrayList<Boolean>();

    public GestioneAerei () {
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
        return elencoAerei;
    }

    //metodo da chiamare dall'user main controller per la ricerca 
    public ObservableList<Aerei> getElencoListaPartenze(LocalDate giorno, String destinazione) {
        elencoAereiFiltrati.clear();
        for (int i = 0; i < elencoAerei.size(); i++) {
                if (giorno.equals(elencoAerei.get(i).getGiornoPartenzaProperty()) || destinazione.equals(elencoAerei.get(i).getDestinazioneString())) {
                        elencoAereiFiltrati.add(elencoAerei.get(i));
                }
        }
        return elencoAereiFiltrati;
    }
    public ObservableList<Aerei> getElencoListaArrivi(LocalDate giorno, String provenienza) {
        elencoAereiFiltrati.clear();
        for (int i = 0; i < elencoAerei.size(); i++) {
                if (giorno.equals(elencoAerei.get(i).getGiornoArrivoProperty()) || provenienza.equals(elencoAerei.get(i).getProvenienzaString())) {
                        elencoAereiFiltrati.add(elencoAerei.get(i));
                }
        }
        return elencoAereiFiltrati;
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
        elencoAerei.add(a);
    }

    public void rimuoviAereo(Aerei a) {
        elencoAerei.remove(a);
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

    }

}
