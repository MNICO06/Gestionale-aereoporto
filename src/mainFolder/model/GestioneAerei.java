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
    private ObservableList<Aerei> elencoAerei = FXCollections.observableArrayList();

    public GestioneAerei () {
        //poi da fare apertura dati da database
    }

    public ObservableList<Aerei> getElencoLista() {
        return elencoAerei;
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

    


}
