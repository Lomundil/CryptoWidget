package jFX.cryptoWidget.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import jFX.cryptoWidget.model.CryptoMonnaie;

import java.util.ArrayList;

import jFX.cryptoWidget.MainApp;


/**
 * Controlleur pour la gestion de la liste des cryptos
 * 
 * @author Charlie
 *
 */
public class GestionCryptoController {
	
	private MainApp mainApp;
	private ListView<CryptoMonnaie> listCrypto;
	
	/**
	 * Constructeur de la classe GestionCryptoController
	 */
	public GestionCryptoController() {
		
	}
	
	@FXML
	private void initialize() {
		//listCrypto.getSelectionModel().selectedItemProperty().addListener((ListChangeListener.Change<? extends CryptoMonnaie> change) -> {
			//while(change.next()) {
			
			//}
		
		//});        
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		listCrypto.setItems(mainApp.getCryptoData());
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
		CryptoMonnaie cMTemp = new CryptoMonnaie();
        boolean okClicked = mainApp.showAjouterDialogController(cMTemp);
        if (okClicked) {
        	mainApp.getCryptoData().add(cMTemp);	//On ajoute la crypto à la liste
        	mainApp.showFicheCrypto(mainApp.getCryptoData().size()-1); // On l'affiche
        }
        
	}
	
	
	
	
}
