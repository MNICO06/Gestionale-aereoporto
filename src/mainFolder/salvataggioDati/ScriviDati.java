package mainFolder.salvataggioDati;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import mainFolder.model.Utenti;

public class ScriviDati {

    public void ScriviDati() {

    }

    public void scriviUtenti(ArrayList<Utenti> utenti) {
        try{
            FileWriter writer = new FileWriter ("utenti.txt");
            try (BufferedWriter fileDaScrivere = new BufferedWriter(writer)) {
                for (int i = 0; i < utenti.size(); i++) {
                    fileDaScrivere.write(utenti.get(i).getNome());
                    fileDaScrivere.write("+");
                    fileDaScrivere.write(utenti.get(i).getCognome());
                    fileDaScrivere.write("+");
                    fileDaScrivere.write(utenti.get(i).getMail());
                    fileDaScrivere.write("+");
                    fileDaScrivere.write(utenti.get(i).getNascita());
                    fileDaScrivere.write("+");
                    fileDaScrivere.write(utenti.get(i).getPassword());
                    fileDaScrivere.write("+");
                    fileDaScrivere.write(utenti.get(i).getNumeroCellulare());
                    fileDaScrivere.write("+");
                    fileDaScrivere.write(utenti.get(i).getNazioneResideza());
                    fileDaScrivere.write("+");
                    fileDaScrivere.write(utenti.get(i).getCittaResidenza());
                    fileDaScrivere.write("+");
                    fileDaScrivere.write(utenti.get(i).getViaResidenza());
                    fileDaScrivere.write("+");
                    fileDaScrivere.write(utenti.get(i).getCodiceCarta());
                    fileDaScrivere.write("+");
                    fileDaScrivere.write(utenti.get(i).getScadenza());
                    fileDaScrivere.write("+");

                    fileDaScrivere.newLine();
                }

                fileDaScrivere.close();
            }

            
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    

    


}
