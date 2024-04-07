package mainFolder.salvataggioDati;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
                        nome = datiArrayList.get(j);
                    }
                    if (j == 1) {
                        cognome = datiArrayList.get(j);
                    }
                    if (j == 2) {
                        mail = datiArrayList.get(j);
                    }
                    if (j == 3) {
                        nascita = datiArrayList.get(j);
                    }
                    if (j == 4) {
                        password = datiArrayList.get(j);
                    }
                    if (j == 5) {
                        numCell = datiArrayList.get(j);
                    }
                    if (j == 6) {
                        nazione = datiArrayList.get(j);
                    }
                    if (j == 7) {
                        citta = datiArrayList.get(j);
                    }
                    if (j == 8) {
                        via = datiArrayList.get(j);
                    }
                    if (j == 9) {
                        codice = datiArrayList.get(j);
                    }
                    if (j == 10) {
                        scadenza = datiArrayList.get(j);
                    }
                }
                Utenti u = new Utenti(nome, cognome, mail, nascita, password, numCell, nazione, citta, via, codice, scadenza);
                listaUtenti.add(u);
            }

            return listaUtenti;

        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    

    
}
