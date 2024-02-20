package mainFolder.model;

import java.time.*;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Aerei {
    private StringProperty modelloAereo;
    private StringProperty provenienza;
    private StringProperty destinazione;
    private StringProperty compagniaAerea;
    private StringProperty codiceRegistrazione;
    private IntegerProperty numeroMassimoPasseggeri;
    private IntegerProperty gate;
    private IntegerProperty terminal;
    private Duration ritardo;
    private LocalDateTime giornoDiArrivo;
    private LocalDateTime giornoDiPartenza;



    private boolean scaricoBagagli = false;
    private boolean caricoBagagli = false;
    private boolean imbarco = false;


    
    
}
