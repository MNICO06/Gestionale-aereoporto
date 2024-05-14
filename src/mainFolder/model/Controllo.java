package mainFolder.model;

import javafx.application.Platform;
import javafx.collections.ObservableList;

public class Controllo extends Thread{


    private ObservableList<Aerei> listaAerei;
    private volatile boolean running = true;

    public Controllo(ObservableList<Aerei> listaAerei) {
        this.listaAerei = listaAerei;
    }

    @Override
    public void run() {
            // Il thread deve controllare se ogni aereo è nella lista corretta
            // Se non è nella lista corretta, deve essere spostato
            // il thread deve accedere alla lista in modo sincronizzato con il controller e lo fa con un semafor

        while (running) {
            synchronized (listaAerei) {
                Platform.runLater(() -> {
                    for (Aerei aerei : listaAerei) {
                        System.out.println("CIAO STO ANDANDO");
                        if (aerei.isInVolo()) {
                            aerei.setStato("In arrivo");
                        } else if (aerei.isInPartenza()) {
                            aerei.setStato("In partenza");
                        } else if (aerei.isInAttesa()) {
                            aerei.setStato("In attesa");
                        } else if (aerei.isInManutenzione()) {
                            aerei.setStato("In manutenzione");
                        }
                    }
                });
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void stopRunning() {
        running = false;
    }
    
}
