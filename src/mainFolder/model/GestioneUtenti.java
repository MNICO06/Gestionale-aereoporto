package mainFolder.model;

import java.util.ArrayList;

import mainFolder.salvataggioDati.LeggiDati;
import mainFolder.salvataggioDati.ScriviDati;

public class GestioneUtenti {
    private static GestioneUtenti instance;
    public ArrayList<Utenti> listaUtenti = new ArrayList<Utenti>();
    ScriviDati scrivi;
    LeggiDati leggo;


    public GestioneUtenti (){
        scrivi = new ScriviDati();
        leggo = new LeggiDati();
    }

    public static GestioneUtenti getInstance(){
        if(instance == null){
            instance = new GestioneUtenti();
        }
        return instance;
    }
    
    public void addUtenti(String nome,String cognome, String mail, String nascita,  String password, String numCell,
                            String nazione, String citta, String via, String codice, String scadenza){

        Utenti u = new Utenti(nome, cognome, mail, nascita, password, numCell, nazione, citta, via, codice, scadenza);

        listaUtenti.add(u);

        scrivi.scriviUtenti(listaUtenti);
    }

    public void aggiornaLista() {
        listaUtenti = leggo.leggiUtente();
    }

    public ArrayList<Utenti> getUtenti() {
        return listaUtenti;
    }

}
