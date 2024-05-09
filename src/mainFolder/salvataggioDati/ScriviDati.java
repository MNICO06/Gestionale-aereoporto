package mainFolder.salvataggioDati;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import mainFolder.model.Aerei;
import mainFolder.model.Utenti;

public class ScriviDati {

    // Costruttore
    public ScriviDati() {

    }

    // Metodo per scrivere i dati degli utenti su file
    public void scriviUtenti(ArrayList<Utenti> utenti) {
        try (BufferedWriter fileDaScrivere = new BufferedWriter(new FileWriter("./src/mainFolder/salvataggioDati/utenti.txt"))) {
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

    public void scriviAerei(ObservableList<Aerei> aerei) {
        try (FileWriter writer = new FileWriter("./src/mainFolder/salvataggioDati/aerei.csv")) {
            // Scrivi l'intestazione
            writer.write("Modello,Provenienza,Destinazione,Compagnia,Codice,NumMax,GiornoArrivo,OraArrivo,GiornoPartenza,OraPartenza,Intervallo\n");

            // Scrivi i dati degli aerei
            for (Aerei aereo : aerei) {
                writer.write(String.format("%s,%s,%s,%s,%s,%d,%s,%s,%s,%s,%d\n",
                        aereo.getModello(),
                        aereo.getProvenienzaString(),
                        aereo.getDestinazioneString(),
                        aereo.getCompagnia(),
                        aereo.getCodice(),
                        aereo.getNumeroPostiOccupatiInt(),
                        aereo.getGiornoArrivoString(),
                        aereo.getOraArrivoString(),
                        aereo.getGiornoPartenzaString(),
                        aereo.getOraPartenzaString(),
                        aereo.getIntervallo()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }       

    }

    public void scriviAereiFine(ObservableList<Aerei> aerei) {
        try (FileWriter writer = new FileWriter("./src/mainFolder/salvataggioDati/aerei.csv")) {
            String formatString = "%s,%s,%s,%s,%s,%d,%s,%s,%s,%s,%d,%s,%d,%d";
            // Scrivi l'intestazione
            // ritatdo e posti occupati da salvare

            writer.write(
                    "Modello,Provenienza,Destinazione,Compagnia,Codice,NumMax,GiornoArrivo,OraArrivo,GiornoPartenza,OraPartenza,Intervallo,Stato,PostiOccupati,Ritardo,InizioManutenzione,FineManutenzione,Hangar\n");

            // Scrivi i dati degli aerei
            for (Aerei aereo : aerei) {
                if (aereo.getInizioManutenzione() != null && aereo.getFineManutenzione() != null
                        && aereo.getHangar() != null) {
                    formatString += ",%s,%s,%s";

                    writer.write(String.format(formatString + "\n",
                            aereo.getModello(),
                            aereo.getProvenienzaString(),
                            aereo.getDestinazioneString(),
                            aereo.getCompagnia(),
                            aereo.getCodice(),
                            aereo.getNumeroPostiOccupatiInt(),
                            aereo.getGiornoArrivoString(),
                            aereo.getOraArrivoString(),
                            aereo.getGiornoPartenzaString(),
                            aereo.getOraPartenzaString(),
                            aereo.getIntervallo(),
                            aereo.getStato(),
                            aereo.getNumeroPostiOccupatiInt(),
                            aereo.getRitardoInt(),
                            aereo.getInizioManutenzioneString(),
                            aereo.getFineManutenzioneString(),
                            aereo.getHangar()));
                } else {
                    writer.write(String.format(formatString + "\n",
                            aereo.getModello(),
                            aereo.getProvenienzaString(),
                            aereo.getDestinazioneString(),
                            aereo.getCompagnia(),
                            aereo.getCodice(),
                            aereo.getNumeroPostiOccupatiInt(),
                            aereo.getGiornoArrivoString(),
                            aereo.getOraArrivoString(),
                            aereo.getGiornoPartenzaString(),
                            aereo.getOraPartenzaString(),
                            aereo.getIntervallo(),
                            aereo.getStato(),
                            aereo.getNumeroPostiOccupatiInt(),
                            aereo.getRitardoInt()));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
