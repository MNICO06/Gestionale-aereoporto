package mainFolder.salvataggioDati;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mainFolder.model.Aerei;
import mainFolder.model.Utenti;


public class LeggiDati {

    public void LeggiDati() {

    }

    public ArrayList<Utenti> leggiUtente() {
        try {
            ArrayList<Utenti> listaUtenti = new ArrayList<Utenti>();
            int numLinee = 0;

            //aprtura del file da scrivere
            FileReader apertura = new FileReader("./src/mainFolder/salvataggioDati/utenti.txt");
            BufferedReader fileDaLeggere = new BufferedReader(apertura);
            String linea;

            ArrayList<String> datiArrayList = new ArrayList<>();

            while ((linea = fileDaLeggere.readLine()) != null){
                numLinee++;
                String [] dati = linea.split("\\+");
                datiArrayList.addAll(Arrays.asList(dati));
            }
            
            int temp = 0;
            for (int i = 0; i < numLinee; i++) {
                String nome = "";
                String cognome = "";
                String mail = "";
                String nascita = "";
                String password = "";
                String numCell = "";
                String nazione = "";
                String citta = "";
                String via = "";
                String codice = "";
                String scadenza = "";
                for (int j = 0; j < 11; j++) {
                    
                    if (j == 0) {
                        nome = datiArrayList.get(j+temp);
                    }
                    if (j == 1) {
                        cognome = datiArrayList.get(j+temp);
                    }
                    if (j == 2) {
                        mail = datiArrayList.get(j+temp);
                    }
                    if (j == 3) {
                        nascita = datiArrayList.get(j+temp);
                    }
                    if (j == 4) {
                        password = datiArrayList.get(j+temp);
                    }
                    if (j == 5) {
                        numCell = datiArrayList.get(j+temp);
                    }
                    if (j == 6) {
                        nazione = datiArrayList.get(j+temp);
                    }
                    if (j == 7) {
                        citta = datiArrayList.get(j+temp);
                    }
                    if (j == 8) {
                        via = datiArrayList.get(j+temp);
                    }
                    if (j == 9) {
                        codice = datiArrayList.get(j+temp);
                    }
                    if (j == 10) {
                        scadenza = datiArrayList.get(j+temp);
                    }
                }
                temp+=11;
                Utenti u = new Utenti(nome, cognome, mail, nascita, password, numCell, nazione, citta, via, codice, scadenza);
                listaUtenti.add(u);
            }

            fileDaLeggere.close();
            return listaUtenti;

        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Aerei> leggiAerei() {
        ObservableList<Aerei> aerei = FXCollections.observableArrayList();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        try (BufferedReader reader = new BufferedReader(new FileReader("./src/mainFolder/salvataggioDati/aerei.csv"))) {
            String line;
            reader.readLine();
            while((line = reader.readLine()) != null) {
                String[] linea = line.split(",");
                if(linea.length == 11) {
                    String modello = linea[0];
                    String provenienza = linea[1];
                    String destinazione = linea[2];
                    String compagnia = linea[3];
                    String codice = linea[4];
                    int numMax = Integer.parseInt(linea[5]);
                    LocalDate giornoArrivo = LocalDate.parse(linea[6], dateFormatter);
                    LocalTime oraArrivo = LocalTime.parse(linea[7], timeFormatter);
                    LocalDate giornoPartenza = LocalDate.parse(linea[8], dateFormatter);
                    LocalTime oraPartenza = LocalTime.parse(linea[9], timeFormatter);
                    int intervallo = Integer.parseInt(linea[10]);

                    Aerei aereo = new Aerei(modello, provenienza, destinazione, compagnia, codice, numMax, giornoArrivo, oraArrivo, giornoPartenza, oraPartenza, intervallo);
                    aerei.add(aereo);
                }
            }

            return aerei;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    

    
}
