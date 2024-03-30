package mainFolder.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import mainFolder.salvataggioDati.ScriviDati;

public class GestioneUtenti {

    private ArrayList<Utenti> listaUtenti = new ArrayList<Utenti>();
    private String percorsoFileUtenti= "file:src/mainFolder/documentiTesto/utenti.txt";
    String delimitatore = "+";
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

    public void salvaUtenti() {
        try{

            // Creazione della chiave segreta per la cifratura AES
            SecretKey key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

            
            // Inizializzazione del cifrario con il provider "SunJCE"
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            //aprtura del file da scrivere
            FileWriter apertura = new FileWriter(percorsoFileUtenti);
            BufferedWriter fileDaScrivere = new BufferedWriter(apertura);
            for (int i=0;i<listaUtenti.size();i++){
                String userData = listaUtenti.get(i).getNome() + "+" +
                        listaUtenti.get(i).getCognome() + "+" +
                        listaUtenti.get(i).getMail() + "+" +
                        listaUtenti.get(i).getNascita() + "+" +
                        listaUtenti.get(i).getPassword() + "+" +
                        listaUtenti.get(i).getNumeroCellulare() + "+" +
                        listaUtenti.get(i).getNazioneResideza() + "+" +
                        listaUtenti.get(i).getCittaResidenza() + "+" +
                        listaUtenti.get(i).getViaResidenza() + "+" +
                        listaUtenti.get(i).getCodiceCarta() + "+" +
                        listaUtenti.get(i).getScadenza();

                // Cifratura dei dati utente
                byte[] encryptedData = cipher.doFinal(userData.getBytes());

                // Conversione dei dati cifrati in una stringa Base64 per la scrittura su file
                String encryptedDataString = Base64.getEncoder().encodeToString(encryptedData);

                // Scrittura dei dati cifrati su file
                fileDaScrivere.write(encryptedDataString);
                fileDaScrivere.newLine();
                

            }
            fileDaScrivere.close();
            
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchProviderException e) {
            e.printStackTrace();
        }
    }


}
