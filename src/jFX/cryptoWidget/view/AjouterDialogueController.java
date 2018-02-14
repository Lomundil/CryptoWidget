package jFX.cryptoWidget.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import jFX.cryptoWidget.model.*;

public class AjouterDialogueController {
	
	@FXML
	private TextField nomCrypto;
	
	private Stage addDialogStage;
	
	private CryptoMonnaie cM;
	
	private boolean okCliqued;
	
	/**
	 * Constructeur du controlleur de la fen�tre g�rant l'ajout de nouvelle crypto
	 */
	public AjouterDialogueController(){
		
	}
	
	/**
	 * Initialise le constructeur. Cette m�thode est appell� automatiquement apr�s que les fichiers FXML ont �t� charg�.
	 */
	@FXML
	private void initialize() {
		
	}
	
	public void setStage(Stage stage) {
		this.addDialogStage = stage;
	}
	
	public void setCrypto(CryptoMonnaie cM) {
		this.cM = cM;
	}
	
	@FXML
	private void handleAccepter() {
		if(inputNameCryptoValid()) {
			//Connection au site coinMarketCap pour r�cup�rer les informations
		}
	}
	
	private boolean inputNameCryptoValid() {
		//V�rification que le nom existe sur le site coinmarketCap
		cM.setNomCrypto(nomCrypto.getText());
		
		return false;
	}

	/**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        addDialogStage.close();
    }
	
	
	
}
