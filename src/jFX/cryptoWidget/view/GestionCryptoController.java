package jFX.cryptoWidget.view;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import jFX.cryptoWidget.model.CryptoMonnaie;
import jFX.cryptoWidget.util.Connection;
import jFX.cryptoWidget.util.TransformationTxtCryptoObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import jFX.cryptoWidget.MainApp;


/**
 * Controlleur pour la gestion de la liste des cryptos
 * 
 * @author Charlie
 *
 */
public class GestionCryptoController {
	
	private MainApp mainApp;
	
	@FXML
	private ObservableList<CryptoMonnaie> listCrypto;
	
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
		listCrypto = mainApp.getCryptoData();
		
	}
	
	/**
	 * Méthode permettant d'actualiser l'ensemble des cryptos lorsque l'utilisateur clique sur le bouton Actualiser
	 */
	@FXML
	private void handleActualiser() {
		//Connection au site coinMarketCap pour actualiser l'ensemble de la liste
		Connection co = new Connection();
		TransformationTxtCryptoObject tr = new TransformationTxtCryptoObject();
		Iterator<String> i = mainApp.getPosition().iterator();
		while(i.hasNext()) {
			String nom = i.next();
			co.setURL(nom);
			co.creationCryptoFile(nom);
		}
		mainApp.getCryptoData().forEach(cM -> cM = tr.transfo(cM));
		mainApp.raz();
		mainApp.getCryptoData().forEach(cM -> mainApp.showFicheCrypto(cM));
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
        	mainApp.getPosition().add(cMTemp.getNomCrypto());
        	 
        }
        
	}
	
	@FXML
	public void actuFiche() {
		listCrypto.addListener(new ListChangeListener<CryptoMonnaie>() {
			public void onChanged(Change<? extends CryptoMonnaie> c) {
		         while (c.next()) {
		        	 if(c.wasPermutated()) {
		        		 // Gérer les permutations
		                     
	                 } else if (c.wasUpdated()) {
		                   
	                 } else if (c.wasRemoved()){
	                     	mainApp.deleteFicheCrypto(mainApp.getPosition().lastIndexOf(c.getRemoved().get(0).getNomCrypto()));
	                     	mainApp.getPosition().remove(c.getRemoved().get(0).getNomCrypto());
	                 } else if (c.wasAdded()) {
	                    	mainApp.showFicheCrypto(c.getAddedSubList().get(0)); 
	                 }
	                 
				}
			}
		});
	}
	
	
	
	
}
