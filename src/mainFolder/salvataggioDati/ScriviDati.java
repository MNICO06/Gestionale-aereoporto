package mainFolder.salvataggioDati;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import mainFolder.model.Utenti;

public class ScriviDati {

    // Costruttore
    public ScriviDati() {

    }

    // Metodo per scrivere i dati degli utenti su file
    public void scriviUtenti(ArrayList<Utenti> utenti) {
        try (BufferedWriter fileDaScrivere = new BufferedWriter(new FileWriter("utenti.txt"))) {
            for (Utenti utente : utenti) {
                // Concatenazione dei dati con '+' come delimitatore
                fileDaScrivere.write(utente.getNome() + "+");
                fileDaScrivere.write(utente.getCognome() + "+");
                fileDaScrivere.write(utente.getMail() + "+");
                fileDaScrivere.write(utente.getNascita() + "+");
                fileDaScrivere.write(utente.getPassword() + "+");
                fileDaScrivere.write(utente.getNumeroCellulare() + "+");
                fileDaScrivere.write(utente.getNazioneResideza() + "+");
                fileDaScrivere.write(utente.getCittaResidenza() + "+");
                fileDaScrivere.write(utente.getViaResidenza() + "+");
                fileDaScrivere.write(utente.getCodiceCarta() + "+");
                fileDaScrivere.write(utente.getScadenza() + "+");
                fileDaScrivere.newLine();
            }

            fileDaScrivere.flush();

            System.out.println("Dati scritti con successo.");


        } catch (IOException e) {
            System.err.println("Errore nella scrittura dei dati su file: " + e.getMessage());
        }
    }
}
