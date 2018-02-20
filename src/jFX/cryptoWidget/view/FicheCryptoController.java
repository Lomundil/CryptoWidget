package jFX.cryptoWidget.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import jFX.cryptoWidget.model.CryptoMonnaie;

import jFX.cryptoWidget.MainApp;

/**
 * Classe controller pour les fiches de cryptos
 * 
 * @author Charlie
 *
 */
public class FicheCryptoController {
	
	private MainApp mainApp;
	private CryptoMonnaie cM;
	
	@FXML
	private Label nomCryptoLabel;
	@FXML
	private Label prixLabel;
	@FXML
	private Label marketCapLabel;
	@FXML
	private Label variationLabel;
	@FXML 
	private Label volumeLabel;
	
	/**
	 * Constructeur de la classe appelé avant l'initilize
	 */
	public FicheCryptoController() {
		
	}
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@FXML
	private void initialize() {
		 
	}
	        
	    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp, CryptoMonnaie cM) {
        this.mainApp = mainApp;
        this.cM = cM;
        showCryptoDetails(cM);
    }
    
    /**
     * Fills all text fields to show details about the person.
     *
     * @param cryptoMonnaie
     */
    private void showCryptoDetails(CryptoMonnaie cM) {
        
        // Fill the labels with info from crypto coin.
        nomCryptoLabel.setText(cM.getNomCrypto());
        prixLabel.setText(cM.getPrix() + "$");
        marketCapLabel.setText(cM.getMarketcap() + "$");
        variationLabel.setText(cM.getVariation() + "%");
        volumeLabel.setText(cM.getVolume() + "$");
                
    }
    
    /**
     * Appelé quand l'utilisateur clique sur supprimer
     * Supprime la crypto
     */
    @FXML
    private void handleSupprimer() {
    	
    }
}
