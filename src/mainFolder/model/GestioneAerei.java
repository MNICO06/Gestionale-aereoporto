/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainFolder.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author molte
 */
public class GestioneAerei {
    private static GestioneAerei instance;
    private ObservableList<Aerei> elencoAerei = FXCollections.observableArrayList();

    public GestioneAerei () {
        //poi da fare apertura dati da database
        caricaDati();
    }

    public ObservableList<Aerei> getElencoLista() {
        return elencoAerei;
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

        Aerei a = new Aerei (modello, provenienza, destinazione, compagnia, codice, numMax, giornoArrivo, oraArrivo, giornoPartenza, oraPartenza, intervallo);
        elencoAerei.add(a);
    }

    public void rimuoviAereo(Aerei a) {
        elencoAerei.remove(a);
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

        addAereo("Airbus A319", "MUC", "AMS", "KLM", "KL123",
                150, LocalDate.of(2024, 04, 11), LocalTime.of(10, 0), LocalDate.of(2024, 04, 11), LocalTime.of(12, 45),
                2);

    }

}
