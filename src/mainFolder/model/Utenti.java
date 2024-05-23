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
    public String nascita;

    public Utenti (String nome,String cognome, String mail, String nascita,  String password, String numCell, String nazione, 
     String citta, String via, String codice, String scadenza) {
        this.mail = mail;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.numeroCellulare = numCell;
        this.nazioneResideza = nazione;
        this.cittaResidenza = citta;
        this.viaResidenza = via;
        this.codiceCarta = codice;
        this.scadenza = scadenza;
        this.nascita = nascita;
    }

    public Utenti (String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail (){
        return mail;
    }

    public String getPassword (){
        return password;
    }

    public String getNome (){
        return nome;
    }

    public String getCognome (){
        return cognome;
    }

    public String getNascita (){
        return nascita;
    }

    public String getNumeroCellulare (){
        return numeroCellulare;
    }

    public String getNazioneResideza (){
        return nazioneResideza;
    }

    public String getCittaResidenza (){
        return cittaResidenza;
    }

    public String getViaResidenza (){
        return viaResidenza;
    }

    public String getCodiceCarta (){
        return codiceCarta;
    }

    public String getScadenza (){
        return scadenza;
    }

    public void setMail (String mail){
        this.mail = mail;
    }

    public void setPassword (String password){
        this.password = password;
    }
}
