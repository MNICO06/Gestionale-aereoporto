package mainFolder.model;

public class Utenti {

    public String mail;
    public String password;
    public String nome;
    public String cognome;
    public String numeroCellulare;
    public String nazioneResideza;
    public String cittaResidenza;
    public String viaResidenza;
    public String codiceCarta;
    public String scadenza;


    public Utenti(String mail, String password, String nome, String cognome, String numCell, String nazione, 
     String citta, String via, String codice, String scadenza) {
        this.mail = mail;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.numeroCellulare = numCell;
        this.nazioneResideza = nazione;



    }

}
