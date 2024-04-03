package mainFolder.model;

import java.util.ArrayList;

import mainFolder.salvataggioDati.ScriviDati;

public class GestioneUtenti {

    private ArrayList<Utenti> listaUtenti = new ArrayList<Utenti>();
    ScriviDati scrivi;

    // Chiave segreta per la cifratura AES
    private static final String SECRET_KEY = "0123456789abcdef";

    public GestioneUtenti (){
        scrivi = new ScriviDati();
    }
    
    public void addUtenti(String nome,String cognome, String mail, String nascita,  String password, String numCell,
                            String nazione, String citta, String via, String codice, String scadenza){

        Utenti u = new Utenti(nome, cognome, mail, nascita, password, numCell, nazione, citta, via, codice, scadenza);

        listaUtenti.add(u);

        scrivi.scriviUtenti(listaUtenti);
    }

}
