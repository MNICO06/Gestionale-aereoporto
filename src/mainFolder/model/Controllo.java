package mainFolder.model;

import javafx.collections.ObservableList;

public class Controllo extends Thread{
    
    GestioneAerei gestioneAerei = GestioneAerei.getInstance();
    // prendo la lista di tutt gli aerei
    private ObservableList<Aerei> listaAerei = gestioneAerei.getElencoLista();
    
    public Controllo() {
    }

    @Override
    public void run() {
    while (true) {
        // Scorre aerei
        for (Aerei aerei : listaAerei) {
            // Se l'aereo è in volo
            if (aerei.isInVolo()) {
                // Imposto lo stato dell'aereo a "In arrivo"
                aerei.setStato("In arrivo");
            } else if (aerei.isInPartenza()) {
                // Imposto lo stato dell'aereo a "In partenza"
                aerei.setStato("In partenza");
            } else if (aerei.isInAttesa()) {
                // Imposto lo stato dell'aereo a "A terra"
                aerei.setStato("In attesa");
            } else if (aerei.isInManutenzione()) {
                // Imposto lo stato
                aerei.setStato("In manutenzione");
            }
        }
    }
    }
    
}
