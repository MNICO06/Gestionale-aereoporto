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

    public void leggiUtente() {
        try {
            //aprtura del file da scrivere
            FileReader apertura = new FileReader("utenti.txt");
            BufferedReader fileDaLeggere = new BufferedReader(apertura);
            String linea;

            ArrayList<String> datiArrayList = new ArrayList<>();

            while ((linea = fileDaLeggere.readLine()) != null){
                String [] dati = linea.split("\\+");
                datiArrayList.addAll(Arrays.asList(dati));
            }

            System.out.println(datiArrayList);

            



        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    
}
