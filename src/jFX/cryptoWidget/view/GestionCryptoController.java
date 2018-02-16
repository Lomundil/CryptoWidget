package jFX.cryptoWidget.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import jFX.cryptoWidget.model.CryptoMonnaie;
import jFX.cryptoWidget.MainApp;


/**
 * Controlleur pour la gestion de la liste des cryptos
 * 
 * @author Charlie
 *
 */
public class GestionCryptoController {
	
	private MainApp mainApp;
	
	/**
	 * Constructeur de la classe GestionCryptoController
	 */
	public GestionCryptoController() {
		
	}
	
	@FXML
	private void initialize() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	/**
	 * Méthode permettant d'actualiser l'ensemble des cryptos lorsque l'utilisateur clique sur le bouton Actualiser
	 */
	@FXML
	private void handleActualiser() {
		//Connection au site coinMarketCap pour actualiser l'ensemble de la liste
		
	}
	
	/**
	 * Méthode permettant d'ajouter une nouvelle crypto lorsque l'utilisateur clique sur le bouton Ajouter
	 */
	@FXML
	private void handleAjouter() {
		//CryptoMonnaie cMTemp = new CryptoMonnaie();
        boolean okClicked = mainApp.showAjouterDialogController();
        
	}
	
	
	
	
}
